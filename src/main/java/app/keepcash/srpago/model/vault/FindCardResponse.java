package app.keepcash.srpago.model.vault;

import java.io.Serializable;
import java.util.List;

public class FindCardResponse implements Serializable {

	private static final long serialVersionUID = -6058577986492194229L;
	
	private Boolean success;
	private Card data;
	private List<String> errors;

	public FindCardResponse() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Card getData() {
		return data;
	}

	public void setData(Card data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
