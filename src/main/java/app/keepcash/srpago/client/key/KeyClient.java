package app.keepcash.srpago.client.key;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import app.keepcash.srpago.model.key.EncryptDecryptRequest;
import app.keepcash.srpago.model.key.Response;

@FeignClient(contextId = "keyContextId", value = "keyClient", url = "${key.url}", configuration = FeignClientKeyConfig.class)
public interface KeyClient {
	
	@PostMapping(value = "${key.decrypt}")
	ResponseEntity<Response> decrypt(EncryptDecryptRequest request);

}
