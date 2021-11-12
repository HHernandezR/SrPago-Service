package app.keepcash.srpago.model.customer;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.json.JSONObject;

public class CustomerUpdateRequest implements Serializable {

	private static final long serialVersionUID = 3095225981901664805L;

	@NotNull(message = "Campo name es requerido")
	@Size(min = 1, message = "Campo name es requerido")
	private String name;

	@NotNull(message = "Campo email es requerido")
	@Size(min = 1, message = "Campo email es requerido")
	private String email;

	private JSONObject metadata;

	public CustomerUpdateRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JSONObject getMetadata() {
		return metadata;
	}

	public void setMetadata(JSONObject metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "CustomerUpdateRequest [name=" + name + ", email=" + email + ", metadata=" + metadata + "]";
	}

}
