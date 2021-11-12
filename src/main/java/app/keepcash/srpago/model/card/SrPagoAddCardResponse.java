package app.keepcash.srpago.model.card;

import java.io.Serializable;

public class SrPagoAddCardResponse implements Serializable {

	private static final long serialVersionUID = 5282052610610041023L;

	private Boolean success;
	private AddCardResponseResult result;

	public SrPagoAddCardResponse() {
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

	@Override
	public String toString() {
		return "SrPagoAddCardResponse [success=" + success + ", result=" + result + "]";
	}

}
