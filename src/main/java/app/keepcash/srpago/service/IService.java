package app.keepcash.srpago.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import app.keepcash.srpago.model.ErrorsResponse;
import app.keepcash.srpago.model.ServiceInfo;

public interface IService {
	
	public ResponseEntity<ServiceInfo> ping();
	public ErrorsResponse getRequestErrors(BindingResult bindingResult);
	public <T> ResponseEntity<T> validateErrors(ErrorsResponse errorsResponse);

}
