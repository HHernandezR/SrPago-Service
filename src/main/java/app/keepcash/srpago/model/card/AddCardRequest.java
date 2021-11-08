package app.keepcash.srpago.model.card;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddCardRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Campo idCustomer es requerido")
	@Size(min = 1, message = "Campo idCustomer es requerido")
	private String idCustomer;

	@NotNull(message = "Campo token es requerido")
	@Size(min = 1, message = "Campo token es requerido")
	private String token;

	public AddCardRequest() {
		super();
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AddCardRequest [idCustomer=" + idCustomer + ", token=" + token + "]";
	}

}
