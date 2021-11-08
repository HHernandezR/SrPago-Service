package app.keepcash.srpago.model.customer;

import java.io.Serializable;
import java.util.List;

public class CustomerCreateResponse implements Serializable {

	private static final long serialVersionUID = 4827123563038892585L;

	private Boolean success;
	private CustomerCreateResult result;
	private List<String> errors;

	public CustomerCreateResponse() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public CustomerCreateResult getResult() {
		return result;
	}

	public void setResult(CustomerCreateResult result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public CustomerCreateResponse(Boolean success, CustomerCreateResult result, List<String> errors) {
		super();
		this.success = success;
		this.result = result;
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "CustomerResponse [success=" + success + ", result=" + result + ", errors=" + errors + "]";
	}

}
