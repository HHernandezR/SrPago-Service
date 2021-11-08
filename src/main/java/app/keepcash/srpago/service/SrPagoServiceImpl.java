package app.keepcash.srpago.service;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.keepcash.srpago.client.srpago.SrPagoClient;
import app.keepcash.srpago.model.card.AddCardRequest;
import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerCreateResult;
import app.keepcash.srpago.model.customer.CustomerRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;
import app.keepcash.srpago.model.entity.BitacoraAuditEntity;
import app.keepcash.srpago.model.entity.CustomerEntity;
import app.keepcash.srpago.repository.BitacoraAuditRepository;
import app.keepcash.srpago.repository.CustomerRepository;
import app.keepcash.utils.Utils;

@Service
public class SrPagoServiceImpl implements ISrPagoService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SrPagoServiceImpl.class);
	
	@Autowired
	private SrPagoClient srPagoClient;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BitacoraAuditRepository bitacoraAuditRepository;
	
	@Value("${srpago.operation.createcustomer}")
	private String createCustomerOperation;

	@Value("${srpago.operation.updatecustomer}")
	private String updateCustomerOperation;

	@Value("${srpago.operation.addcard}")
	private String addCardOperation;

	@Value("${srpago.operation.payment}")
	private String paymentOperation;
	
	@Value("${payment.processor.srpago}")
	private String paymentProcessorSRPago;

	@Override
	public ResponseEntity<CustomerCreateResponse> createCustomer(CustomerRequest customer) {
		ResponseEntity<CustomerCreateResponse> responseEntity = null;
		try {
			CustomerRequest customerRequest = this.trimParamsCustomer(customer);
			LOGGER.info("--- Calling Sr Pago Create Cusomer ---");
			LOGGER.info(customerRequest.toString());
			ResponseEntity<CustomerCreateResponse> response = this.srPagoClient.customerCreate(customerRequest);
			LOGGER.info("--- Sr Pago Response ---");
			LOGGER.info(response.getStatusCode().toString());
			String idTransaction = Utils.createIdTransaction();
			this.saveBitacoraAudit(idTransaction, customer.toString(), response.getBody().toString(),this.createCustomerOperation);

			if (HttpStatus.OK.equals(response.getStatusCode())) {
				this.saveCustomer(customerRequest.getIdUser(), response.getBody());
			}

			responseEntity = new ResponseEntity<CustomerCreateResponse>(response.getBody(), HttpStatus.CREATED);
		} catch (UndeclaredThrowableException e) {
			String error = e.getUndeclaredThrowable().getMessage();
			LOGGER.error(error);
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			
			CustomerCreateResponse response = new CustomerCreateResponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<CustomerCreateResponse>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			String error = "Internal Error";
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			
			CustomerCreateResponse response = new CustomerCreateResponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<CustomerCreateResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<CustomerUpdateResponse> updateCustomer(String idCustomer, CustomerRequest customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseEntity<CustomerUpdateResponse> getCustomerInfo(String idCustomer) {
		ResponseEntity<CustomerUpdateResponse> responseEntity = null;
		try {
			LOGGER.info("--- Calling Sr Pago Customer Info ---");
			LOGGER.info(idCustomer);
			ResponseEntity<CustomerUpdateResponse> response = this.srPagoClient.customerInfo(idCustomer.trim());
			LOGGER.info("--- Sr Pago Response ---");
			LOGGER.info(response.getStatusCode().toString());
			responseEntity = new ResponseEntity<CustomerUpdateResponse>(response.getBody(), HttpStatus.OK);

		} catch (UndeclaredThrowableException e) {
			String error = e.getUndeclaredThrowable().getMessage();
			LOGGER.error(error);
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			CustomerUpdateResponse response = new CustomerUpdateResponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<CustomerUpdateResponse>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			CustomerUpdateResponse response = new CustomerUpdateResponse();
			String error = "No hay clientes registrados con el token " + idCustomer;
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<CustomerUpdateResponse>(response, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@Override
	@Transactional
	public void saveBitacoraAudit(String idTransaction, String request, String response, String operation) {
		try {
			BitacoraAuditEntity bitacoraAudit = new BitacoraAuditEntity();
			bitacoraAudit.setIdTransaction(idTransaction);
			bitacoraAudit.setPaymentProcessor(this.paymentProcessorSRPago);
			bitacoraAudit.setRequest(request);
			bitacoraAudit.setResponse(response);
			bitacoraAudit.setOperation(operation);

			this.bitacoraAuditRepository.save(bitacoraAudit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public CustomerEntity saveCustomer(String idUser, CustomerCreateResponse response) {
		CustomerEntity customerEntity = null;
		CustomerEntity saved = null;
		CustomerCreateResult customerCreateResult = null;
		try {
			LOGGER.info("--- Guardando en BD CUSTOMER ---");
			customerEntity = new CustomerEntity();
			customerCreateResult = response.getResult();

			customerEntity.setIdUser(idUser);
			customerEntity.setIdCustomer(customerCreateResult.getId());
			customerEntity.setName(customerCreateResult.getName());
			customerEntity.setEmail(customerCreateResult.getEmail());
			customerEntity.setActive(customerCreateResult.getActive());
			customerEntity.setDateCreate(customerCreateResult.getDate_create());
			customerEntity.setDeactivatedCards(customerCreateResult.getDeactivated_cards());

			saved = this.customerRepository.save(customerEntity);
			LOGGER.info("--- Objeto Guardado CUSTOMER ---");
			LOGGER.info(saved.getId().toString());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return saved;
	}
	
	private CustomerRequest trimParamsCustomer(CustomerRequest customer) {
		CustomerRequest customerRequest = null;
		try {
			customerRequest = new CustomerRequest();
			customerRequest.setIdUser(customer.getIdUser().trim());
			customerRequest.setName(customer.getName().trim());
			customerRequest.setEmail(customer.getEmail().trim());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return customerRequest;
	}

	private AddCardRequest trimParamsCard(AddCardRequest card) {
		AddCardRequest addCardRequest = null;
		try {
			addCardRequest = new AddCardRequest();
			addCardRequest.setIdCustomer(card.getIdCustomer().trim());
			addCardRequest.setToken(card.getToken().trim());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return addCardRequest;
	}

}
