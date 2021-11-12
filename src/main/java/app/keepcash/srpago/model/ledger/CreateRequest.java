package app.keepcash.srpago.model.ledger;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateRequest implements Serializable {

	private static final long serialVersionUID = 6097822164128775147L;
	
	@NotNull(message = "Campo idTransaction es requerido")
	@Size(min = 1, message = "Campo idTransaction es requerido")
	private String idTransaction;

	@NotNull(message = "Campo idUser es requerido")
	@Size(min = 1, message = "Campo idUser es requerido")
	private String idUser;

	@NotNull(message = "Campo paymentProcessor es requerido")
	@Size(min = 1, message = "Campo paymentProcessor es requerido")
	private String paymentProcessor;

	@NotNull(message = "Campo card es requerido")
	@Size(min = 1, message = "Campo card es requerido")
	private String card;

	@NotNull(message = "Campo amount es requerido")
	@Size(min = 1, message = "Campo amount es requerido")
	private String amount;

	@NotNull(message = "Campo tip es requerido")
	@Size(min = 1, message = "Campo tip es requerido")
	private String tip;

	@NotNull(message = "Campo latitud es requerido")
	@Size(min = 1, message = "Campo latitud es requerido")
	private String latitud;

	@NotNull(message = "Campo longitud es requerido")
	@Size(min = 1, message = "Campo longitud es requerido")
	private String longitud;

	@NotNull(message = "Campo request es requerido")
	@Size(min = 1, message = "Campo request es requerido")
	private String request;

	public CreateRequest() {
		super();
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getPaymentProcessor() {
		return paymentProcessor;
	}

	public void setPaymentProcessor(String paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public CreateRequest(
			@NotNull(message = "Campo idTransaction es requerido") @Size(min = 1, message = "Campo idTransaction es requerido") String idTransaction,
			@NotNull(message = "Campo idUser es requerido") @Size(min = 1, message = "Campo idUser es requerido") String idUser,
			@NotNull(message = "Campo paymentProcessor es requerido") @Size(min = 1, message = "Campo paymentProcessor es requerido") String paymentProcessor,
			@NotNull(message = "Campo card es requerido") @Size(min = 1, message = "Campo card es requerido") String card,
			@NotNull(message = "Campo amount es requerido") @Size(min = 1, message = "Campo amount es requerido") String amount,
			@NotNull(message = "Campo tip es requerido") @Size(min = 1, message = "Campo tip es requerido") String tip,
			@NotNull(message = "Campo latitud es requerido") @Size(min = 1, message = "Campo latitud es requerido") String latitud,
			@NotNull(message = "Campo longitud es requerido") @Size(min = 1, message = "Campo longitud es requerido") String longitud,
			@NotNull(message = "Campo request es requerido") @Size(min = 1, message = "Campo request es requerido") String request) {
		super();
		this.idTransaction = idTransaction;
		this.idUser = idUser;
		this.paymentProcessor = paymentProcessor;
		this.card = card;
		this.amount = amount;
		this.tip = tip;
		this.latitud = latitud;
		this.longitud = longitud;
		this.request = request;
	}

	@Override
	public String toString() {
		return "CreateRequest [idTransaction=" + idTransaction + ", idUser=" + idUser + ", paymentProcessor="
				+ paymentProcessor + ", card=" + card + ", amount=" + amount + ", tip=" + tip + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", request=" + request + "]";
	}

}
