package app.keepcash.srpago.model.customer;

import java.io.Serializable;
import java.util.List;

public class CustomerUpdateResponse implements Serializable {

	private static final long serialVersionUID = -1671227426608789761L;

	private Boolean success;
	private CustomerUpdateResult result;
	private List<String> errors;

	public CustomerUpdateResponse() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public CustomerUpdateResult getResult() {
		return result;
	}

	public void setResult(CustomerUpdateResult result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public CustomerUpdateResponse(Boolean success, CustomerUpdateResult result, List<String> errors) {
		super();
		this.success = success;
		this.result = result;
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "CustomerUpdateResponse [success=" + success + ", result=" + result + ", errors=" + errors + "]";
	}

}
