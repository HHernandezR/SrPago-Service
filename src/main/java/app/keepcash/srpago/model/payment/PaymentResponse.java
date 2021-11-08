package app.keepcash.srpago.model.payment;

import java.io.Serializable;
import java.util.List;

public class PaymentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean success;
	private PaymentResponseResult result;
	private List<String> errors;

	public PaymentResponse() {
		super();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public PaymentResponseResult getResult() {
		return result;
	}

	public void setResult(PaymentResponseResult result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public PaymentResponse(Boolean success, PaymentResponseResult result, List<String> errors) {
		super();
		this.success = success;
		this.result = result;
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "PaymentResponse [success=" + success + ", result=" + result + ", errors=" + errors + "]";
	}

}
