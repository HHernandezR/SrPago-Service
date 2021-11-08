package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Request implements Serializable {

	private static final long serialVersionUID = -672499470528967288L;

	@NotNull(message = "Campo data es requerido")
	@Size(min = 1, message = "Campo data es requerido")
	private String data;

	@NotNull(message = "Campo key es requerido")
	@Size(min = 1, message = "Campo key es requerido")
	private String key;

	public Request() {
		super();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Request(
			@NotNull(message = "Campo data es requerido") @Size(min = 1, message = "Campo data es requerido") String data,
			@NotNull(message = "Campo key es requerido") @Size(min = 1, message = "Campo key es requerido") String key) {
		super();
		this.data = data;
		this.key = key;
	}

	@Override
	public String toString() {
		return "Request [data=" + data + ", key=" + key + "]";
	}

}
