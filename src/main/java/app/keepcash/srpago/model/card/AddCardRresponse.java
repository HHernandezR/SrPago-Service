package app.keepcash.srpago.model.card;

import java.io.Serializable;
import java.util.List;

public class AddCardRresponse implements Serializable {

	private static final long serialVersionUID = 293085845392213446L;

	private Boolean success;
	private AddCardResponseResult result;
	private List<String> errors;

	public AddCardRresponse() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public AddCardResponseResult getResult() {
		return result;
	}

	public void setResult(AddCardResponseResult result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public AddCardRresponse(Boolean success, AddCardResponseResult result, List<String> errors) {
		super();
		this.success = success;
		this.result = result;
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "AddCardRresponse [success=" + success + ", result=" + result + ", errors=" + errors + "]";
	}

}
