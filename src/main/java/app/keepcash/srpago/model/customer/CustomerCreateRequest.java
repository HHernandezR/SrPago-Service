package app.keepcash.srpago.model.customer;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.json.JSONObject;

public class CustomerCreateRequest implements Serializable {

	private static final long serialVersionUID = 570176494527395921L;

	@NotNull(message = "Campo name es requerido")
	@Size(min = 1, message = "Campo name es requerido")
	private String name;

	@NotNull(message = "Campo email es requerido")
	@Size(min = 1, message = "Campo email es requerido")
	private String email;

	@NotNull(message = "Campo idUser es requerido")
	@Size(min = 1, message = "Campo idUser es requerido")
	private String idUser;

	private JSONObject metadata;

	public CustomerCreateRequest() {
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

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public JSONObject getMetadata() {
		return metadata;
	}

	public void setMetadata(JSONObject metadata) {
		this.metadata = metadata;
	}

	public CustomerCreateRequest(
			@NotNull(message = "Campo name es requerido") @Size(min = 1, message = "Campo name es requerido") String name,
			@NotNull(message = "Campo email es requerido") @Size(min = 1, message = "Campo email es requerido") String email,
			@NotNull(message = "Campo userId es requerido") @Size(min = 1, message = "Campo idUser es requerido") String idUser,
			JSONObject metadata) {
		super();
		this.name = name;
		this.email = email;
		this.idUser = idUser;
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "CustomerRequest [name=" + name + ", email=" + email + ", idUser=" + idUser + ", metadata=" + metadata
				+ "]";
	}

}
