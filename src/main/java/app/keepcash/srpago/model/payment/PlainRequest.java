package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class PlainRequest implements Serializable {

	private static final long serialVersionUID = -1158322278343975346L;

	@Size(min = 1, message = "Campo payment es requerido")
	private Payment payment;

	public PlainRequest() {
		super();
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public PlainRequest(@Size(min = 1, message = "Campo payment es requerido") Payment payment) {
		super();
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "PlainRequest [payment=" + payment + "]";
	}

}
