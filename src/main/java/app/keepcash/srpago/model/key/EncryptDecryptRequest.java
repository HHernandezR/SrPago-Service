package app.keepcash.srpago.model.key;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EncryptDecryptRequest implements Serializable {

	private static final long serialVersionUID = 7733037934434356698L;

	@NotNull(message = "Campo idUser es requerido")
	@Size(min = 1, message = "Campo idUser es requerido")
	private String idUser;

	@NotNull(message = "Campo message es requerido")
	@Size(min = 1, message = "Campo message es requerido")
	private String message;

	public EncryptDecryptRequest() {
		super();
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EncryptRequest [idUser=" + idUser + ", message=" + message + "]";
	}

}
