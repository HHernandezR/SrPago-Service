package app.keepcash.srpago.client.ledger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import app.keepcash.srpago.model.ledger.CreateRequest;
import app.keepcash.srpago.model.ledger.Response;
import app.keepcash.srpago.model.ledger.UpdateRequest;

@FeignClient(contextId = "ledgerContextId", value = "ledgerClient", url = "${ledger.url}", configuration = FeignClientLedgerConfig.class)
public interface LedgerClient {
	
	@PostMapping(value = "${ledger.save}")
	ResponseEntity<Response> saveLedger(CreateRequest request);
	
	@PutMapping(value = "${ledger.update}/{idTransaction}")
	ResponseEntity<Response> updateLedger(@PathVariable String idTransaction, UpdateRequest request);

}
