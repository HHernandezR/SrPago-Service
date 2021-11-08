package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Payment implements Serializable {

	private static final long serialVersionUID = 5565338679135748967L;

	@NotNull(message = "Campo external es requerido")
	private External external;

	@NotNull(message = "Campo origin es requerido")
	private Origin origin;

	@NotNull(message = "Campo reference es requerido")
	private Reference reference;

	@NotNull(message = "Campo tip es requerido")
	private Tip tip;

	@NotNull(message = "Campo total es requerido")
	private Total total;

	public Payment() {
		super();
	}

	public External getExternal() {
		return external;
	}

	public void setExternal(External external) {
		this.external = external;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	public Payment(
			@NotNull(message = "Campo external es requerido") External external,
			@NotNull(message = "Campo origin es requerido") Origin origin,
			@NotNull(message = "Campo reference es requerido") Reference reference,
			@NotNull(message = "Campo tip es requerido") Tip tip,
			@NotNull(message = "Campo total es requerido") Total total) {
		super();
		this.external = external;
		this.origin = origin;
		this.reference = reference;
		this.tip = tip;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Payment [external=" + external + ", origin=" + origin + ", reference="
				+ reference + ", tip=" + tip + ", total=" + total + "]";
	}

}
