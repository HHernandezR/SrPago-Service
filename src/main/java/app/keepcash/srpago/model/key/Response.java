package app.keepcash.srpago.model.key;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Response implements Serializable {

	private static final long serialVersionUID = 282533138198603564L;
	
	private Boolean success;
	private Map<String, String> data;
	private List<String> errors;

	public Response() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Response(Boolean success, Map<String, String> data, List<String> errors) {
		super();
		this.success = success;
		this.data = data;
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Response [success=" + success + ", data=" + data + ", errors=" + errors + "]";
	}

}
