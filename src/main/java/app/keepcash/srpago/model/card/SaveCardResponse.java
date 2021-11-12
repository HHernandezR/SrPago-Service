package app.keepcash.srpago.model.card;

import java.io.Serializable;

public class SaveCardResponse implements Serializable {

	private static final long serialVersionUID = -3648141446103856228L;

	private String idUser;
	private String idCard;

	public SaveCardResponse() {
		super();
	}

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
		return "SaveCardResponse [idUser=" + idUser + ", idCard=" + idCard + "]";
	}

}
