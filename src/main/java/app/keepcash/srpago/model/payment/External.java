package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class External implements Serializable {

	private static final long serialVersionUID = -5538182288594163273L;

	@NotNull(message = "Campo application_key es requerido")
	@Size(min = 1, message = "Campo application_key es requerido")
	private String application_key;

	@NotNull(message = "Campo transaction es requerido")
	@Size(min = 1, message = "Campo transaction es requerido")
	private String transaction;

	public External() {
		super();
	}

	public String getApplication_key() {
		return application_key;
	}

	public void setApplication_key(String application_key) {
		this.application_key = application_key;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public External(
			@NotNull(message = "Campo application_key es requerido") @Size(min = 1, message = "Campo application_key es requerido") String application_key,
			@NotNull(message = "Campo transaction es requerido") @Size(min = 1, message = "Campo transaction es requerido") String transaction) {
		super();
		this.application_key = application_key;
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "External [application_key=" + application_key + ", transaction=" + transaction + "]";
	}

}
