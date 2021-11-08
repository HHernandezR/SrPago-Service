package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PayRequest implements Serializable {

	private static final long serialVersionUID = 2090759141964502085L;

	@NotNull(message = "Campo idCard es requerido")
	@Size(min = 1, message = "Campo idCard es requerido")
	private String idCard;

	@NotNull(message = "Campo idUser es requerido")
	@Size(min = 1, message = "Campo idUser es requerido")
	private String idUser;

	@NotNull(message = "Campo device es requerido")
	@Size(min = 1, message = "Campo device es requerido")
	private String device;

	@NotNull(message = "Campo ip es requerido")
	@Size(min = 1, message = "Campo ip es requerido")
	private String ip;

	@NotNull(message = "Campo latitud es requerido")
	@Size(min = 1, message = "Campo latitud es requerido")
	private String latitud;

	@NotNull(message = "Campo longitud es requerido")
	@Size(min = 1, message = "Campo longitud es requerido")
	private String longitud;

	@NotNull(message = "Campo description es requerido")
	@Size(min = 1, message = "Campo description es requerido")
	private String description;

	@NotNull(message = "Campo amount es requerido")
	@Size(min = 1, message = "Campo amount es requerido")
	private String amount;

	@NotNull(message = "Campo tip es requerido")
	@Size(min = 1, message = "Campo tip es requerido")
	private String tip;

	public PayRequest() {
		super();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "PayRequest [idCard=" + idCard + ", idUser=" + idUser + ", device=" + device + ", ip=" + ip
				+ ", latitud=" + latitud + ", longitud=" + longitud + ", description=" + description + ", amount="
				+ amount + ", tip=" + tip + "]";
	}

}
