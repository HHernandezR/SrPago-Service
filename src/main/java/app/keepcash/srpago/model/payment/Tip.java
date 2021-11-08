package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Tip implements Serializable {

	private static final long serialVersionUID = 7864019677706738902L;

	@NotNull(message = "Campo amount es requerido")
	@Size(min = 1, message = "Campo amount es requerido")
	private String amount;

	@NotNull(message = "Campo currency es requerido")
	@Size(min = 1, message = "Campo currency es requerido")
	private String currency;

	public Tip() {
		super();
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Tip(
			@NotNull(message = "Campo amount es requerido") @Size(min = 1, message = "Campo amount es requerido") String amount,
			@NotNull(message = "Campo currency es requerido") @Size(min = 1, message = "Campo currency es requerido") String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Tip [amount=" + amount + ", currency=" + currency + "]";
	}

}
