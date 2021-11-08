package app.keepcash.srpago.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.keepcash.srpago.model.ErrorsResponse;
import app.keepcash.srpago.model.ServiceInfo;
import app.keepcash.srpago.model.customer.CustomerCreateResponse;
import app.keepcash.srpago.model.customer.CustomerRequest;
import app.keepcash.srpago.model.customer.CustomerUpdateResponse;
import app.keepcash.srpago.service.IService;
import app.keepcash.srpago.service.ISrPagoService;

@RestController
@RequestMapping(path = "/srpago")
public class ApplicationController {
	
	@Autowired
	private IService service;
	
	@Autowired
	private ISrPagoService srPagoService;
	
	@GetMapping(path = "/")
	public ResponseEntity<ServiceInfo> ping() {
		return this.service.ping();
	}
	
	@PostMapping(path = "/customer/create")
	public ResponseEntity<CustomerCreateResponse> createCustomer(@Valid @RequestBody CustomerRequest customer, BindingResult bindingResult) {
		ResponseEntity<CustomerCreateResponse> responseEntity = null;
		if (bindingResult.hasErrors()) {
			ErrorsResponse errorsResponse = this.service.getRequestErrors(bindingResult);
			responseEntity = this.service.validateErrors(errorsResponse);
		} else {
			responseEntity = this.srPagoService.createCustomer(customer);
		}
		return responseEntity;
	}
	
	@PutMapping(path = "/customer/update/{idCustomer}")
	public ResponseEntity<CustomerUpdateResponse> updateCustomer(@Valid @PathVariable String idCustomer, @RequestBody CustomerRequest customer, BindingResult bindingResult) {
		ResponseEntity<CustomerUpdateResponse> responseEntity = null;
		if (bindingResult.hasErrors()) {
			ErrorsResponse errorsResponse = this.service.getRequestErrors(bindingResult);
			responseEntity = this.service.validateErrors(errorsResponse);
		} else {
			responseEntity = this.srPagoService.updateCustomer(idCustomer, customer);
		}
		return responseEntity;
	}
	
	@GetMapping(path = "/customer/{idCustomer}")
	public ResponseEntity<CustomerUpdateResponse> getCustomerInfo(@PathVariable String idCustomer) {
		ResponseEntity<CustomerUpdateResponse> 	responseEntity = this.srPagoService.getCustomerInfo(idCustomer);
		return responseEntity;
	}

}
