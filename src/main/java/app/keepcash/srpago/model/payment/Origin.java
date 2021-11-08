package app.keepcash.srpago.model.payment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Origin implements Serializable {

	private static final long serialVersionUID = -6372687642200743776L;

	@NotNull(message = "Campo device es requerido")
	@Size(min = 1, message = "Campo device es requerido")
	private String device;

	@NotNull(message = "Campo ip es requerido")
	@Size(min = 1, message = "Campo ip es requerido")
	private String ip;

	@NotNull(message = "Campo location es requerido")
	private Location location;

	public Origin() {
		super();
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Origin(
			@NotNull(message = "Campo device es requerido") @Size(min = 1, message = "Campo device es requerido") String device,
			@NotNull(message = "Campo ip es requerido") @Size(min = 1, message = "Campo ip es requerido") String ip,
			@NotNull(message = "Campo location es requerido") Location location) {
		super();
		this.device = device;
		this.ip = ip;
		this.location = location;
	}

	@Override
	public String toString() {
		return "Origin [device=" + device + ", ip=" + ip + ", location=" + location + "]";
	}

}
