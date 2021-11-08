package app.keepcash.srpago.model.secrets;

import java.io.Serializable;

public class SecretsSrPago implements Serializable {

	private static final long serialVersionUID = 6047377267837814462L;
	
	private String username;
	private String password;
	private String publickey;

	public SecretsSrPago() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public SecretsSrPago(String username, String password, String publickey) {
		super();
		this.username = username;
		this.password = password;
		this.publickey = publickey;
	}

	@Override
	public String toString() {
		return "SecretsSrPago [username=" + username + ", password=" + password + ", publickey=" + publickey + "]";
	}

}
