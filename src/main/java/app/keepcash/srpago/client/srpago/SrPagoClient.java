package app.keepcash.srpago.client.srpago;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import app.keepcash.srpago.model.card.SrPagoAddCardRequest;
import app.keepcash.srpago.model.card.SrPagoAddCardResponse;
import app.keepcash.srpago.model.customer.CustomerCreateRequest;
import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerUpdateRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;
import app.keepcash.srpago.model.payment.PaymentResponse;
import app.keepcash.srpago.model.payment.Request;

@FeignClient(contextId = "srPagoContextId", value = "srPagoClient", url = "${srpago.host}", configuration = FeignClientSrPagoConfig.class)
public interface SrPagoClient {
	
	@PostMapping(value = "${srpago.url.customer}")
	ResponseEntity<CustomerCreateResponse> customerCreate(CustomerCreateRequest request);
	
	@PutMapping(value = "${srpago.url.customer}/{idCustomer}")
	ResponseEntity<CustomerUpdateResponse> customerUpdate(@PathVariable String idCustomer, CustomerUpdateRequest request);
	
	@GetMapping(value = "${srpago.url.customer}/{idCustomer}")
	ResponseEntity<CustomerUpdateResponse> customerInfo(@PathVariable String idCustomer);
	
	@PostMapping(value = "${srpago.url.card}/{id}/cards")
	ResponseEntity<SrPagoAddCardResponse> saveCard(SrPagoAddCardRequest request, @PathVariable String id);
	
	@PostMapping(value = "${srpago.url.paymentcard}")
	ResponseEntity<PaymentResponse> doPayment(Request request);
}
