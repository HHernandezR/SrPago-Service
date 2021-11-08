package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Location implements Serializable {

	private static final long serialVersionUID = 1689452733453376753L;

	@NotNull(message = "Campo latitude es requerido")
	@Size(min = 1, message = "Campo latitude es requerido")
	private String latitude;

	@NotNull(message = "Campo longitude es requerido")
	@Size(min = 1, message = "Campo longitude es requerido")
	private String longitude;

	public Location() {
		super();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Location(
			@NotNull(message = "Campo latitude es requerido") @Size(min = 1, message = "Campo latitude es requerido") String latitude,
			@NotNull(message = "Campo longitude es requerido") @Size(min = 1, message = "Campo longitude es requerido") String longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
