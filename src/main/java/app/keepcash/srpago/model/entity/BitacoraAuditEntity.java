package app.keepcash.srpago.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bitacora_audit")
public class BitacoraAuditEntity implements Serializable {

	private static final long serialVersionUID = 3576234303422181034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private BigInteger id;

	@NotNull
	@Size(min = 1, max = 128, message = "El campo idTransaction es requerido")
	@Column(name = "id_transaction")
	private String idTransaction;

	@NotNull
	@Size(min = 1, max = 128, message = "El campo paymentProcessor es requerido")
	@Column(name = "payment_processor")
	private String paymentProcessor;

	@NotNull
	@Size(min = 1, message = "El campo request es requerido")
	@Column(name = "request")
	private String request;

	@NotNull
	@Size(min = 1, message = "El campo response es requerido")
	@Column(name = "response")
	private String response;

	@NotNull
	@Size(min = 1, max = 128, message = "El campo operation es requerido")
	@Column(name = "operation")
	private String operation;

	public BitacoraAuditEntity() {
		super();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getPaymentProcessor() {
		return paymentProcessor;
	}

	public void setPaymentProcessor(String paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "BitacoraAuditEntity [id=" + id + ", idTransaction=" + idTransaction + ", paymentProcessor="
				+ paymentProcessor + ", request=" + request + ", response=" + response + ", operation=" + operation
				+ "]";
	}

}
