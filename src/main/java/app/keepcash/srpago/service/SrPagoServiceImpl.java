package app.keepcash.srpago.service;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import app.keepcash.srpago.client.key.KeyClient;
import app.keepcash.srpago.client.ledger.LedgerClient;
import app.keepcash.srpago.client.srpago.SrPagoClient;
import app.keepcash.srpago.client.vault.VaultClient;
import app.keepcash.srpago.config.SecretsInit;
import app.keepcash.srpago.model.card.AddCardRequest;
import app.keepcash.srpago.model.card.AddCardRresponse;
import app.keepcash.srpago.model.card.SaveCardResponse;
import app.keepcash.srpago.model.card.SrPagoAddCardRequest;
import app.keepcash.srpago.model.card.SrPagoAddCardResponse;
import app.keepcash.srpago.model.customer.CustomerCreateRequest;
import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerCreateResult;
import app.keepcash.srpago.model.customer.CustomerUpdateRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;
import app.keepcash.srpago.model.customer.CustomerUpdateResult;
import app.keepcash.srpago.model.entity.BitacoraAuditEntity;
import app.keepcash.srpago.model.entity.CustomerEntity;
import app.keepcash.srpago.model.key.EncryptDecryptRequest;
import app.keepcash.srpago.model.ledger.CreateRequest;
import app.keepcash.srpago.model.ledger.UpdateRequest;
import app.keepcash.srpago.model.payment.External;
import app.keepcash.srpago.model.payment.Location;
import app.keepcash.srpago.model.payment.Origin;
import app.keepcash.srpago.model.payment.PayRequest;
import app.keepcash.srpago.model.payment.Payment;
import app.keepcash.srpago.model.payment.PaymentFullRequest;
import app.keepcash.srpago.model.payment.PaymentResponse;
import app.keepcash.srpago.model.payment.PaymentResponseResult;
import app.keepcash.srpago.model.payment.Reference;
import app.keepcash.srpago.model.payment.Request;
import app.keepcash.srpago.model.payment.Tip;
import app.keepcash.srpago.model.payment.Total;
import app.keepcash.srpago.model.vault.Card;
import app.keepcash.srpago.model.vault.FindCardRequest;
import app.keepcash.srpago.model.vault.FindCardResponse;
import app.keepcash.srpago.model.vault.Response;
import app.keepcash.srpago.model.vault.SaveCardRequest;
import app.keepcash.srpago.repository.BitacoraAuditRepository;
import app.keepcash.srpago.repository.CustomerRepository;
import app.keepcash.utils.Utils;

@Service
public class SrPagoServiceImpl implements ISrPagoService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SrPagoServiceImpl.class);
	
	@Autowired
	private SrPagoClient srPagoClient;
	
	@Autowired
	private VaultClient vaultClient;
	
	@Autowired
	private LedgerClient ledgerClient;
	
	@Autowired
	private KeyClient keyClient;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BitacoraAuditRepository bitacoraAuditRepository;
	
	@Autowired
	private SecretsInit secretsInit;
	
	@Autowired
	private SrPagoCryptoService srPagoCryptoService;
	
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
	public ResponseEntity<CustomerCreateResponse> createCustomer(CustomerCreateRequest request) {
		LOGGER.info("--- STARTING CREATE CUSTOMER ---");
		ResponseEntity<CustomerCreateResponse> responseEntity = null;
		try {
			LOGGER.info("--- Calling Sr Pago Create Cusomer ---");
			LOGGER.info(request.toString());
			ResponseEntity<CustomerCreateResponse> response = this.srPagoClient.customerCreate(request);
			LOGGER.info("--- Sr Pago Response ---");
			LOGGER.info(response.getStatusCode().toString());
			String idTransaction = Utils.createIdTransaction();
			this.saveBitacoraAudit(idTransaction, request.toString(), response.getBody().toString(),this.createCustomerOperation);

			if (HttpStatus.OK.equals(response.getStatusCode())) {
				this.saveCustomer(request.getIdUser(), response.getBody());
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
	public ResponseEntity<CustomerUpdateResponse> updateCustomer(String idUser, CustomerUpdateRequest request) {
		LOGGER.info("--- STARTING UPDATE CUSTOMER ---");
		ResponseEntity<CustomerUpdateResponse> responseEntity = null;
		try {
			Optional<CustomerEntity> optCustomer = this.customerRepository.geCustomerByIdUser(idUser);
			
			if (!optCustomer.isPresent()) {
				String message = "Customer not found on database for idUser: " + idUser;
				CustomerUpdateResponse response = new CustomerUpdateResponse();
				response.setSuccess(false);
				response.setErrors(this.generateErrorResponse(message));
				return new ResponseEntity<CustomerUpdateResponse>(response, HttpStatus.BAD_REQUEST);
			}
			
			LOGGER.info("--- Calling Sr Pago Update Customer ---");
			LOGGER.info(request.toString());
			ResponseEntity<CustomerUpdateResponse> response = this.srPagoClient.customerUpdate(optCustomer.get().getIdCustomer().trim(),
					request);
			LOGGER.info("--- Sr Pago Response ---");
			LOGGER.info(response.getStatusCode().toString());
			String idTransaction = Utils.createIdTransaction();
			this.saveBitacoraAudit(idTransaction, request.toString(), response.getBody().toString(),
					this.updateCustomerOperation);
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				this.updateCustomerEntity(optCustomer.get(), response.getBody());
			}
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
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			
			CustomerUpdateResponse response = new CustomerUpdateResponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<CustomerUpdateResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<CustomerUpdateResponse> getCustomerInfo(String idUser) {
		ResponseEntity<CustomerUpdateResponse> responseEntity = null;
		try {
			Optional<CustomerEntity> optCustomer = this.customerRepository.geCustomerByIdUser(idUser);
			
			if (!optCustomer.isPresent()) {
				String message = "Customer not found on database for idUser: " + idUser;
				CustomerUpdateResponse response = new CustomerUpdateResponse();
				response.setSuccess(false);
				response.setErrors(this.generateErrorResponse(message));
				return new ResponseEntity<CustomerUpdateResponse>(response, HttpStatus.BAD_REQUEST);
			}
			
			LOGGER.info("--- Calling Sr Pago Customer Info ---");
			LOGGER.info(idUser);
			ResponseEntity<CustomerUpdateResponse> response = this.srPagoClient.customerInfo(optCustomer.get().getIdCustomer().trim());
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
			String error = "No hay customer registrados con el idUser " + idUser;
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<CustomerUpdateResponse>(response, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<AddCardRresponse> saveCard(AddCardRequest request) {
		LOGGER.info("--- STARTING SAVE CARD ---");
		ResponseEntity<AddCardRresponse> responseEntity = null;
		try {
			Optional<CustomerEntity> optCustomer = this.customerRepository.geCustomerByIdUser(request.getIdUser().trim());
			
			if (!optCustomer.isPresent()) {
				String message = "Customer not found on database for idUser: " + request.getIdUser();
				AddCardRresponse response = new AddCardRresponse();
				response.setSuccess(false);
				response.setErrors(this.generateErrorResponse(message));
				return new ResponseEntity<AddCardRresponse>(response, HttpStatus.NOT_FOUND);
			}
			
			CustomerEntity customerFound = optCustomer.get();
			SrPagoAddCardRequest srPagoAddCardRequest = new SrPagoAddCardRequest();
			srPagoAddCardRequest.setIdCustomer(customerFound.getIdCustomer().trim());
			srPagoAddCardRequest.setToken(request.getTokenProcessor().trim());
			
			LOGGER.info("--- Calling Sr Pago Add Card ---");
			LOGGER.info(request.toString());
			ResponseEntity<SrPagoAddCardResponse> srPagoResponse = this.srPagoClient.saveCard(srPagoAddCardRequest, customerFound.getIdCustomer().trim());
			LOGGER.info("--- Sr Pago Response ---");
			LOGGER.info(srPagoResponse.getStatusCode().toString());
			String idTransaction = Utils.createIdTransaction();
			this.saveBitacoraAudit(idTransaction, request.toString(), srPagoResponse.getBody().toString(),
					this.addCardOperation);
			
			if (HttpStatus.OK.equals(srPagoResponse.getStatusCode())) {
				
				SaveCardRequest saveCardRequest = this.createVaulCardRequest(request, srPagoResponse.getBody().getResult().getToken());
				ResponseEntity<Response> vaultResponse = this.vaultClient.saveCard(saveCardRequest);
				
				if(HttpStatus.CREATED.equals(vaultResponse.getStatusCode())) {
					Map<String, String> vaultData = vaultResponse.getBody().getData();
					String idCard = vaultData.get("idCard");
					
					SaveCardResponse saveCardResponse = new SaveCardResponse();
					saveCardResponse.setIdUser(request.getIdUser());
					saveCardResponse.setIdCard(idCard);
		
					AddCardRresponse response = new AddCardRresponse();
					response.setSuccess(null);
					response.setResult(saveCardResponse);
					responseEntity = new ResponseEntity<AddCardRresponse>(response, HttpStatus.CREATED);
				}
			}
			
		} catch (UndeclaredThrowableException e) {
			String error = e.getUndeclaredThrowable().getMessage();
			LOGGER.error(error);
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			
			AddCardRresponse response = new AddCardRresponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<AddCardRresponse>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			String error = "Error saving card";
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			
			AddCardRresponse response = new AddCardRresponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<AddCardRresponse>(response, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<PaymentResponse> doPayment(PayRequest request) {
		LOGGER.info("--- STARTING DO PAYMENT ---");
		ResponseEntity<PaymentResponse> responseEntity = null;
		String idTransaction = Utils.createIdTransaction();
		try {
			Optional<CustomerEntity> optCustomer = this.customerRepository.geCustomerByIdUser(request.getIdUser().trim());
			
			if (!optCustomer.isPresent()) {
				String message = "Customer not found on database for idUser: " + request.getIdUser();
				PaymentResponse response = new PaymentResponse();
				response.setSuccess(false);
				response.setErrors(this.generateErrorResponse(message));
				return new ResponseEntity<PaymentResponse>(response, HttpStatus.NOT_FOUND);
			}
			FindCardRequest findCardRequest = new FindCardRequest();
			findCardRequest.setIdUser(request.getIdUser().trim());
			findCardRequest.setIdCard(request.getIdCard().trim());
			
			LOGGER.info("--- Calling Vault Find Card ---");
			ResponseEntity<FindCardResponse> vaultResponse = this.vaultClient.findCard(findCardRequest);
			LOGGER.info("--- Vault Response ---");
			LOGGER.info(vaultResponse.getStatusCode().toString());
			
			if (HttpStatus.OK.equals(vaultResponse.getStatusCode())) {
				Card card = vaultResponse.getBody().getData();
				
				EncryptDecryptRequest encryptDecryptRequest = new EncryptDecryptRequest();
				encryptDecryptRequest.setIdUser(request.getIdUser().trim());
				encryptDecryptRequest.setMessage(card.getTokenProcessor());
				
				LOGGER.info("--- Calling Key Service ---");
				ResponseEntity<app.keepcash.srpago.model.key.Response> keyResponse = this.keyClient.decrypt(encryptDecryptRequest);
				LOGGER.info("--- Key Response ---");
				LOGGER.info(keyResponse.getStatusCode().toString());
				
				if (HttpStatus.OK.equals(keyResponse.getStatusCode())) {
					Map<String, String> data = keyResponse.getBody().getData();
					 String token = data.get("decoded");
					 
					LOGGER.info("--- Crating Request String ---");
					String requestString = this.createPaymentRequest(request, token);
					
					LOGGER.info("--- Crating Crypto Request ---");
					Request requestCrypto = this.srPagoCryptoService.encryptData(requestString);
					
					LOGGER.info("--- Calling Ledger Service ---");
					ResponseEntity<app.keepcash.srpago.model.ledger.Response> ledgerResponseSave = this.saveLedger(this.paymentProcessorSRPago, request, idTransaction);
					LOGGER.info("--- Ledger Response Save ---");
					LOGGER.info(ledgerResponseSave.getStatusCode().toString());

					LOGGER.info("--- Calling Sr Pago Payment ---");
					ResponseEntity<PaymentResponse> responseSrPago = this.srPagoClient.doPayment(requestCrypto);
					LOGGER.info("--- Sr Pago Response ---");
					LOGGER.info(responseSrPago.getStatusCode().toString());
					PaymentResponseResult paymentResponseResult = responseSrPago.getBody().getResult();
					
					LOGGER.info("--- Calling Ledger Service ---");
					ResponseEntity<app.keepcash.srpago.model.ledger.Response> ledgerResponseUpdate = this.updateLedger(idTransaction, paymentResponseResult.toString());
					LOGGER.info("--- Ledger Response Update ---");
					LOGGER.info(ledgerResponseUpdate.getStatusCode().toString());
					
					this.saveBitacoraAudit(idTransaction, request.toString(), responseSrPago.getBody().toString(), this.paymentOperation);
					
					PaymentResponse paymentResponseSrPago = responseSrPago.getBody();
					PaymentResponseResult paymentResponseResultSrPago = paymentResponseSrPago.getResult();
					paymentResponseResultSrPago.setIdTransaction(idTransaction);
					
					PaymentResponse response = new PaymentResponse();
					response.setSuccess(Boolean.TRUE);
					response.setResult(paymentResponseResultSrPago);
					
					responseEntity = new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
				} else {
					String error = "Error getting card information";
					List<String> errors = new ArrayList<String>();
					errors.add("IdTransaction: " + idTransaction);
					errors.add(error);
					PaymentResponse response = new PaymentResponse();
					response.setSuccess(false);
					response.setErrors(errors);
					responseEntity = new ResponseEntity<PaymentResponse>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				String error = "Error getting card from Vault";
				List<String> errors = new ArrayList<String>();
				errors.add("IdTransaction: " + idTransaction);
				errors.add(error);
				PaymentResponse response = new PaymentResponse();
				response.setSuccess(false);
				response.setErrors(errors);
				responseEntity = new ResponseEntity<PaymentResponse>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		} catch (UndeclaredThrowableException e) {
			String error = e.getUndeclaredThrowable().getMessage();
			LOGGER.error(error);
	
			ResponseEntity<app.keepcash.srpago.model.ledger.Response> ledgerResponseUpdate = this.updateLedger(idTransaction, error);
			LOGGER.info("--- Ledger Response Update ---");
			LOGGER.info(ledgerResponseUpdate.getStatusCode().toString());
			
			List<String> errors = new ArrayList<String>();
			errors.add("IdTransaction: " + idTransaction);
			errors.add(error);
			PaymentResponse response = new PaymentResponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<PaymentResponse>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			String errorMessage = "Internal Service Error";
			ResponseEntity<app.keepcash.srpago.model.ledger.Response> ledgerResponseUpdate = this.updateLedger(idTransaction, errorMessage);
			LOGGER.info("--- Ledger Response Update ---");
			LOGGER.info(ledgerResponseUpdate.getStatusCode().toString());
			
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add("IdTransaction: " + idTransaction);
			errors.add(error);
			PaymentResponse response = new PaymentResponse();
			response.setSuccess(false);
			response.setErrors(errors);
			responseEntity = new ResponseEntity<PaymentResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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

	@Override
	@Transactional
	public CustomerEntity updateCustomerEntity(CustomerEntity customerSaved, CustomerUpdateResponse response) {
		CustomerEntity customerEntity = null;
		CustomerEntity saved = null;
		CustomerUpdateResult customerUpdateResult = null;
		try {
			LOGGER.info("--- Guardando en BD CUSTOMER ---");

			customerEntity = customerSaved;

			customerEntity = new CustomerEntity();
			customerUpdateResult = response.getResult();

			customerEntity.setId(customerSaved.getId());
			customerEntity.setIdUser(customerSaved.getIdUser());
			customerEntity.setIdCustomer(customerUpdateResult.getId());
			customerEntity.setName(customerUpdateResult.getName());
			customerEntity.setEmail(customerUpdateResult.getEmail());
			if (customerUpdateResult.getActive()) {
				customerEntity.setActive(1);
			}
			customerEntity.setDateCreate(customerUpdateResult.getDate_create());
			customerEntity.setDeactivatedCards(customerUpdateResult.getDeactivated_cards());

			saved = customerRepository.save(customerEntity);
			LOGGER.info("--- Objeto Actualizado CUSTOMER ---");
			LOGGER.info(saved.getId().toString());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return saved;
	}
	
	private SaveCardRequest createVaulCardRequest(AddCardRequest request, String tokenProcessor) {
		SaveCardRequest saveCardRequest = null;
		try {
			saveCardRequest = new SaveCardRequest();
			saveCardRequest.setIdUser(request.getIdUser());
			saveCardRequest.setPan(request.getPan());
			saveCardRequest.setExpiredDateMonth(request.getExpiredDateMonth());
			saveCardRequest.setExpiredDateYear(request.getExpiredDateYear());
			saveCardRequest.setType(request.getType());
			saveCardRequest.setTokenProcessor(tokenProcessor);
			saveCardRequest.setName(request.getName());
			saveCardRequest.setAlias(request.getAlias());
			saveCardRequest.setCutOffDay(request.getCutOffDay());
			saveCardRequest.setLatitud(request.getLatitud());
			saveCardRequest.setLongitud(request.getLongitud());
			saveCardRequest.setPais(request.getPais());
			saveCardRequest.setEstado(request.getEstado());
			saveCardRequest.setCiudad(request.getCiudad());
			saveCardRequest.setColonia(request.getColonia());
			saveCardRequest.setCalle(request.getCalle());
			saveCardRequest.setNoExterior(request.getNoExterior());
			saveCardRequest.setNoInterior(request.getNoInterior());
			saveCardRequest.setCp(request.getCp());
			saveCardRequest.setAlcaldia(request.getAlcaldia());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return saveCardRequest;
	}

	private ResponseEntity<app.keepcash.srpago.model.ledger.Response> updateLedger(String idTransaction, String response) {
		LOGGER.info("--- Updating on Ledger ---");
		ResponseEntity<app.keepcash.srpago.model.ledger.Response> responseEntity = null;
		try {
			UpdateRequest updateRequest = new UpdateRequest();
			updateRequest.setResponse(response);
			
			responseEntity = this.ledgerClient.updateLedger(idTransaction, updateRequest);
		} catch (Exception e) {
			LOGGER.error("Error creating Payment Request");
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	private String createPaymentRequest(PayRequest request, String token) {
		PaymentFullRequest paymentFullRequest = new PaymentFullRequest();
		String response = "";
		try {
			Gson gson = new Gson();
			String currency = "MXN";

			Location location = new Location();
			Origin origin = new Origin();
			External external = new External();
			Reference reference = new Reference();
			Tip tip = new Tip();
			Total total = new Total();
			Payment payment = new Payment();

			location.setLatitude(request.getLatitud().trim());
			location.setLongitude(request.getLongitud().trim());

			origin.setDevice(request.getDevice().trim());
			origin.setIp(request.getIp().trim());
			origin.setLocation(location);

			external.setApplication_key(this.secretsInit.getSrPagoSecrets().getUsername());
			external.setTransaction("1");

			reference.setNumber("1");
			reference.setDescription(request.getDescription().trim());

			tip.setAmount(request.getTip().trim());
			tip.setCurrency(currency);

			total.setAmount(request.getAmount().trim());
			total.setCurrency(currency);

			payment.setExternal(external);
			payment.setOrigin(origin);
			payment.setReference(reference);
			payment.setTip(tip);
			payment.setTotal(total);

			paymentFullRequest.setPayment(payment);
			paymentFullRequest.setRecurrent(token);
			paymentFullRequest.setTotal(total);

			response = gson.toJson(paymentFullRequest);
		} catch (Exception e) {
			LOGGER.error("Error creating Payment Request");
			e.printStackTrace();
		}
		return response;
	}
	
	private ResponseEntity<app.keepcash.srpago.model.ledger.Response> saveLedger(String paymentProcessor, PayRequest payRequest, String idTransaction) {
		LOGGER.info("--- Saving on Ledger ---");
		ResponseEntity<app.keepcash.srpago.model.ledger.Response> responseEntity = null;
		try {
			CreateRequest request = new CreateRequest();
			
			request.setIdTransaction(String.valueOf(idTransaction.trim()));
			request.setIdUser(String.valueOf(payRequest.getIdUser()));
			request.setPaymentProcessor(this.paymentProcessorSRPago);
			request.setCard(String.valueOf(payRequest.getIdCard()));
			request.setAmount(String.valueOf(payRequest.getAmount()));
			request.setTip(String.valueOf(payRequest.getTip()));
			request.setLatitud(String.valueOf(payRequest.getLatitud()));
			request.setLongitud(String.valueOf(payRequest.getLongitud()));
			request.setRequest(String.valueOf(payRequest.toString()));
			
			responseEntity = this.ledgerClient.saveLedger(request);
		} catch (Exception e) {
			LOGGER.error("Error creating Payment Request");
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	private List<String> generateErrorResponse(String message) {
		List<String> errors = new ArrayList<String>();
		try {
			errors.add(message);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return errors;
	}
}
