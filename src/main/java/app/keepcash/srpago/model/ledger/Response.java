package app.keepcash.srpago.model.ledger;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = -2220865016268946167L;

	private String documentId;

	public Response() {
		super();
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	@Override
	public String toString() {
		return "Response [documentId=" + documentId + "]";
	}
	
}
