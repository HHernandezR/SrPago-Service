package app.keepcash.srpago.client.srpago;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;

@FeignClient(contextId = "srPagoContextId", value = "srPagoClient", url = "${srpago.host}", configuration = FeignClientSrPagoConfig.class)
public interface SrPagoClient {
	
	@PostMapping(value = "${srpago.url.customer}")
	ResponseEntity<CustomerCreateResponse> customerCreate(CustomerRequest customer);
	
	@PutMapping(value = "${srpago.url.customer}/{idCustomer}")
	ResponseEntity<CustomerUpdateResponse> customerUpdate(@PathVariable String idCustomer, CustomerRequest customer);
	
	@GetMapping(value = "${srpago.url.customer}/{idCustomer}")
	ResponseEntity<CustomerUpdateResponse> customerInfo(@PathVariable String idCustomer);
	
//	@PostMapping(value = "${srpago.url.card}/{id}/cards")
//	ResponseEntity<AddCardRresponse> cardAdd(AddCardRequest card, @PathVariable String id);
//	
//	@PostMapping(value = "${srpago.url.paymentcard}")
//	ResponseEntity<PaymentResponse> doPayment(Request request);
}
