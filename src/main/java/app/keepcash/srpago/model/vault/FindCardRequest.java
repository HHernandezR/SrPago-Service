package app.keepcash.srpago.model.vault;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FindCardRequest implements Serializable {

	private static final long serialVersionUID = 1191937697668804727L;

	@NotNull(message = "El campo idUser es requerido")
	@Size(min = 1, message = "El campo idUser es requerido")
	private String idUser;

	@NotNull(message = "El campo idCard es requerido")
	@Size(min = 1, message = "El campo idCard es requerido")
	private String idCard;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "FindCardRequest [idUser=" + idUser + ", idCard=" + idCard + "]";
	}

}
