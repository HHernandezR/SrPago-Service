package app.keepcash.srpago.model.vault;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import app.keepcash.srpago.model.card.CardTypeEnum;

public class SaveCardRequest implements Serializable {

	private static final long serialVersionUID = -5483256913399184181L;

	@NotNull(message = "El campo idUser es requerido")
	@Size(min = 1, message = "El campo idUser es requerido")
	private String idUser;

	@NotNull(message = "El campo pan es requerido")
	@Size(min = 1, message = "El campo pan es requerido")
	private String pan;

	@NotNull(message = "El campo expiredDateMonth es requerido")
	@Size(min = 1, message = "El campo expiredDateMonth es requerido")
	private String expiredDateMonth;

	@NotNull(message = "El campo expiredDateYear es requerido")
	@Size(min = 1, message = "El campo expiredDateYear es requerido")
	private String expiredDateYear;

	@NotNull(message = "El campo type es requerido")
	private CardTypeEnum type;

	private String tokenProcessor;

	@NotNull(message = "El campo name es requerido")
	@Size(min = 1, message = "El campo name es requerido")
	private String name;

	@NotNull(message = "El campo alias es requerido")
	@Size(min = 1, message = "El campo alias es requerido")
	private String alias;

	@NotNull(message = "El campo cutOffDay es requerido")
	@Size(min = 1, message = "El campo cutOffDay es requerido")
	private String cutOffDay;

	@NotNull(message = "El campo latitud es requerido")
	@Size(min = 1, message = "El campo latitud es requerido")
	private String latitud;

	@NotNull(message = "El campo longitud es requerido")
	@Size(min = 1, message = "El campo longitud es requerido")
	private String longitud;

	@NotNull(message = "El campo pais es requerido")
	@Size(min = 1, message = "El campo pais es requerido")
	private String pais;

	@NotNull(message = "El campo estado es requerido")
	@Size(min = 1, message = "El campo estado es requerido")
	private String estado;

	@NotNull(message = "El campo ciudad es requerido")
	@Size(min = 1, message = "El campo ciudad es requerido")
	private String ciudad;

	@NotNull(message = "El campo colonia es requerido")
	@Size(min = 1, message = "El campo colonia es requerido")
	private String colonia;

	@NotNull(message = "El campo calle es requerido")
	@Size(min = 1, message = "El campo calle es requerido")
	private String calle;

	@NotNull(message = "El campo no_exterior es requerido")
	@Size(min = 1, message = "El campo no_exterior es requerido")
	private String noExterior;

	private String noInterior;

	@NotNull(message = "El campo cp es requerido")
	@Size(min = 1, message = "El campo cp es requerido")
	private String cp;

	@NotNull(message = "El campo alcaldia es requerido")
	@Size(min = 1, message = "El campo alcaldia es requerido")
	private String alcaldia;

	public SaveCardRequest() {
		super();
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getExpiredDateMonth() {
		return expiredDateMonth;
	}

	public void setExpiredDateMonth(String expiredDateMonth) {
		this.expiredDateMonth = expiredDateMonth;
	}

	public String getExpiredDateYear() {
		return expiredDateYear;
	}

	public void setExpiredDateYear(String expiredDateYear) {
		this.expiredDateYear = expiredDateYear;
	}

	public CardTypeEnum getType() {
		return type;
	}

	public void setType(CardTypeEnum type) {
		this.type = type;
	}

	public String getTokenProcessor() {
		return tokenProcessor;
	}

	public void setTokenProcessor(String tokenProcessor) {
		this.tokenProcessor = tokenProcessor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCutOffDay() {
		return cutOffDay;
	}

	public void setCutOffDay(String cutOffDay) {
		this.cutOffDay = cutOffDay;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getAlcaldia() {
		return alcaldia;
	}

	public void setAlcaldia(String alcaldia) {
		this.alcaldia = alcaldia;
	}

	@Override
	public String toString() {
		return "SaveCardRequest [idUser=" + idUser + ", pan=" + pan + ", expiredDateMonth=" + expiredDateMonth
				+ ", expiredDateYear=" + expiredDateYear + ", type=" + type + ", tokenProcessor=" + tokenProcessor
				+ ", name=" + name + ", alias=" + alias + ", cutOffDay=" + cutOffDay + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", pais=" + pais + ", estado=" + estado + ", ciudad=" + ciudad
				+ ", colonia=" + colonia + ", calle=" + calle + ", noExterior=" + noExterior + ", noInterior="
				+ noInterior + ", cp=" + cp + ", alcaldia=" + alcaldia + "]";
	}

}
