package app.keepcash.srpago.service;

import org.springframework.http.ResponseEntity;

import app.keepcash.srpago.model.card.AddCardRequest;
import app.keepcash.srpago.model.card.AddCardRresponse;
import app.keepcash.srpago.model.customer.CustomerCreateRequest;
import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerUpdateRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;
import app.keepcash.srpago.model.entity.CustomerEntity;
import app.keepcash.srpago.model.payment.PayRequest;
import app.keepcash.srpago.model.payment.PaymentResponse;

public interface ISrPagoService {
	
	public ResponseEntity<CustomerCreateResponse> createCustomer(CustomerCreateRequest request);
	public ResponseEntity<CustomerUpdateResponse> updateCustomer(String idUser,  CustomerUpdateRequest request);
	public ResponseEntity<CustomerUpdateResponse> getCustomerInfo(String idUser);
	public ResponseEntity<AddCardRresponse> saveCard(AddCardRequest request);
	public ResponseEntity<PaymentResponse> doPayment(PayRequest request);
	
	public void saveBitacoraAudit(String idTransaction, String request, String response, String operation);
	public CustomerEntity saveCustomer(String idUser, CustomerCreateResponse response);
	public CustomerEntity updateCustomerEntity(CustomerEntity customerSaved, CustomerUpdateResponse response);

}
