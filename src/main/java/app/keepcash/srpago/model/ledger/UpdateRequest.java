package app.keepcash.srpago.model.ledger;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateRequest implements Serializable {

	private static final long serialVersionUID = 6700285781192898991L;

	@NotNull(message = "Campo response es requerido")
	@Size(min = 1, message = "Campo response es requerido")
	private String response;

	public UpdateRequest() {
		super();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "UpdateRequest [response=" + response + "]";
	}
}
