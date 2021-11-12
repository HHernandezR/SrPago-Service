package app.keepcash.srpago.model.card;

import java.io.Serializable;
import java.util.List;

public class AddCardRresponse implements Serializable {

	private static final long serialVersionUID = 293085845392213446L;

	private Boolean success;
	private SaveCardResponse result;
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

	public SaveCardResponse getResult() {
		return result;
	}

	public void setResult(SaveCardResponse result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "AddCardRresponse [success=" + success + ", result=" + result + ", errors=" + errors + "]";
	}

}
