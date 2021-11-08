package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Reference implements Serializable {

	private static final long serialVersionUID = -2966458661808707164L;

	@NotNull(message = "Campo description es requerido")
	@Size(min = 1, message = "Campo description es requerido")
	private String description;

	@NotNull(message = "Campo number es requerido")
	@Size(min = 1, message = "Campo number es requerido")
	private String number;

	public Reference() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Reference(
			@NotNull(message = "Campo description es requerido") @Size(min = 1, message = "Campo description es requerido") String description,
			@NotNull(message = "Campo number es requerido") @Size(min = 1, message = "Campo number es requerido") String number) {
		super();
		this.description = description;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Reference [description=" + description + ", number=" + number + "]";
	}

}
