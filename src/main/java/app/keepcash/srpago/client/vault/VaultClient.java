package app.keepcash.srpago.client.vault;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import app.keepcash.srpago.model.vault.FindCardRequest;
import app.keepcash.srpago.model.vault.FindCardResponse;
import app.keepcash.srpago.model.vault.Response;
import app.keepcash.srpago.model.vault.SaveCardRequest;

@FeignClient(contextId = "vaultContextId", value = "vaultClient", url = "${vault.url}", configuration = FeignClientVaultConfig.class)
public interface VaultClient {
	
	@PostMapping(value = "${vault.save}")
	ResponseEntity<Response> saveCard(SaveCardRequest request);
	
	@PostMapping(value = "${vault.find}")
	ResponseEntity<FindCardResponse> findCard(FindCardRequest request);

}
