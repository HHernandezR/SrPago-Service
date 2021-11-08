package app.keepcash.srpago.model.payment;

import java.io.Serializable;

public class PaymentResponseResult implements Serializable {

	private static final long serialVersionUID = 2769485605165050164L;

	private String idTransaction;
	private String token;
	private Boolean status;
	private String authorization_code;
	private String card;
	private String card_type;
	private String emv_response;
	private String issuer_script_data;
	private String tag8a;
	private Long operation_id;

	public PaymentResponseResult() {
		super();
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAuthorization_code() {
		return authorization_code;
	}

	public void setAuthorization_code(String authorization_code) {
		this.authorization_code = authorization_code;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getEmv_response() {
		return emv_response;
	}

	public void setEmv_response(String emv_response) {
		this.emv_response = emv_response;
	}

	public String getIssuer_script_data() {
		return issuer_script_data;
	}

	public void setIssuer_script_data(String issuer_script_data) {
		this.issuer_script_data = issuer_script_data;
	}

	public String getTag8a() {
		return tag8a;
	}

	public void setTag8a(String tag8a) {
		this.tag8a = tag8a;
	}

	public Long getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(Long operation_id) {
		this.operation_id = operation_id;
	}

	@Override
	public String toString() {
		return "PaymentResponseResult [idTransaction=" + idTransaction + ", token=" + token + ", status=" + status
				+ ", authorization_code=" + authorization_code + ", card=" + card + ", card_type=" + card_type
				+ ", emv_response=" + emv_response + ", issuer_script_data=" + issuer_script_data + ", tag8a=" + tag8a
				+ ", operation_id=" + operation_id + "]";
	}

}
