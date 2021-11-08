package app.keepcash.srpago.service;

import org.springframework.http.ResponseEntity;

import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;
import app.keepcash.srpago.model.entity.CustomerEntity;

public interface ISrPagoService {
	
	public ResponseEntity<CustomerCreateResponse> createCustomer(CustomerRequest customer);
	public ResponseEntity<CustomerUpdateResponse> updateCustomer(String idCustomer,  CustomerRequest customer);
	public ResponseEntity<CustomerUpdateResponse> getCustomerInfo(String idCustomer);
	
	public void saveBitacoraAudit(String idTransaction, String request, String response, String operation);
	public CustomerEntity saveCustomer(String idUser, CustomerCreateResponse response);

}
