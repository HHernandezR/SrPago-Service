package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PaymentFullRequest implements Serializable {

	private static final long serialVersionUID = -633729570329856594L;

	@NotNull(message = "Campo payment es requerido")
	private Payment payment;

	@NotNull(message = "Campo recurrent es requerido")
	@Size(min = 1, message = "Campo recurrent es requerido")
	private String recurrent;

	@NotNull(message = "Campo total es requerido")
	private Total total;

	public PaymentFullRequest() {
		super();
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getRecurrent() {
		return recurrent;
	}

	public void setRecurrent(String recurrent) {
		this.recurrent = recurrent;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	public PaymentFullRequest(@NotNull(message = "Campo payment es requerido") Payment payment,
			@NotNull(message = "Campo recurrent es requerido") @Size(min = 1, message = "Campo recurrent es requerido") String recurrent,
			@NotNull(message = "Campo total es requerido") Total total) {
		super();
		this.payment = payment;
		this.recurrent = recurrent;
		this.total = total;
	}

	@Override
	public String toString() {
		return "PaymentFullRequest [payment=" + payment + ", recurrent=" + recurrent + ", total=" + total + "]";
	}

}
