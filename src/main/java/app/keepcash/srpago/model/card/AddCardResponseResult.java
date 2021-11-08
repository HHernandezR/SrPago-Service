package app.keepcash.srpago.model.card;

import java.io.Serializable;

public class AddCardResponseResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;
	private String holder_name;
	private String type;
	private String number;
	private String cvv;
	private String expiration;
	private String country_code;
	private String bank_name;
	private String funding;
	private String status;

	public AddCardResponseResult() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getFunding() {
		return funding;
	}

	public void setFunding(String funding) {
		this.funding = funding;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddCardResponseResult(String token, String holder_name, String type, String number, String cvv,
			String expiration, String country_code, String bank_name, String funding, String status) {
		super();
		this.token = token;
		this.holder_name = holder_name;
		this.type = type;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.country_code = country_code;
		this.bank_name = bank_name;
		this.funding = funding;
		this.status = status;
	}

	@Override
	public String toString() {
		return "AddCardResponseResult [token=" + token + ", holder_name=" + holder_name + ", type=" + type + ", number="
				+ number + ", cvv=" + cvv + ", expiration=" + expiration + ", country_code=" + country_code
				+ ", bank_name=" + bank_name + ", funding=" + funding + ", status=" + status + "]";
	}

}
