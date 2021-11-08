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
@Table(name = "srpago_customers")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = -1789721180767060809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private BigInteger id;

	@NotNull
	@Size(min = 1, max = 256, message = "El campo id_user es requerido")
	@Column(name = "id_user")
	private String idUser;

	@NotNull
	@Size(min = 1, max = 256, message = "El campo id_customer es requerido")
	@Column(name = "id_customer")
	private String idCustomer;

	@NotNull
	@Size(min = 1, max = 512, message = "El campo name es requerido")
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 1, max = 512, message = "El campo email es requerido")
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "active")
	private Integer active;

	@NotNull
	@Size(min = 1, max = 128, message = "El campo date_create es requerido")
	@Column(name = "date_create")
	private String dateCreate;

	@NotNull
	@Column(name = "deactivated_cards")
	private Integer deactivatedCards;

	public CustomerEntity() {
		super();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Integer getDeactivatedCards() {
		return deactivatedCards;
	}

	public void setDeactivatedCards(Integer deactivatedCards) {
		this.deactivatedCards = deactivatedCards;
	}

	public CustomerEntity(BigInteger id,
			@NotNull @Size(min = 1, max = 128, message = "El campo id_user es requerido") String idUser,
			@NotNull @Size(min = 1, max = 128, message = "El campo id_customer es requerido") String idCustomer,
			@NotNull @Size(min = 1, max = 512, message = "El campo name es requerido") String name,
			@NotNull @Size(min = 1, max = 512, message = "El campo email es requerido") String email,
			@NotNull Integer active,
			@NotNull @Size(min = 1, max = 128, message = "El campo date_create es requerido") String dateCreate,
			@NotNull Integer deactivatedCards) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idCustomer = idCustomer;
		this.name = name;
		this.email = email;
		this.active = active;
		this.dateCreate = dateCreate;
		this.deactivatedCards = deactivatedCards;
	}

	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", idUser=" + idUser + ", idCustomer=" + idCustomer + ", name=" + name
				+ ", email=" + email + ", active=" + active + ", dateCreate=" + dateCreate + ", deactivatedCards="
				+ deactivatedCards + "]";
	}

}
