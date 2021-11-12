package app.keepcash.srpago.model.vault;

import java.io.Serializable;

public class Card implements Serializable {

	private static final long serialVersionUID = 652408641220982927L;
	
	private String idCard;
	private String idUser;
	private String pan;
	private String expiredDateMonth;
	private String expiredDateYear;
	private String type;
	private String tokenProcessor;
	private String name;
	private String alias;
	private String cutOffDay;
	private String status;
	private String latitud;
	private String longitud;
	private String createdOn;
	private String modifyOn;
	private String color;
	private String pais;
	private String estado;
	private String ciudad;
	private String colonia;
	private String calle;
	private String noExterior;
	private String noInterior;
	private String cp;
	private String alcaldia;

	public Card() {
		super();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		return "Card [idCard=" + idCard + ", idUser=" + idUser + ", pan=" + pan + ", expiredDateMonth="
				+ expiredDateMonth + ", expiredDateYear=" + expiredDateYear + ", type=" + type + ", tokenProcessor="
				+ tokenProcessor + ", name=" + name + ", alias=" + alias + ", cutOffDay=" + cutOffDay + ", status="
				+ status + ", latitud=" + latitud + ", longitud=" + longitud + ", createdOn=" + createdOn
				+ ", modifyOn=" + modifyOn + ", color=" + color + ", pais=" + pais + ", estado=" + estado + ", ciudad="
				+ ciudad + ", colonia=" + colonia + ", calle=" + calle + ", noExterior=" + noExterior + ", noInterior="
				+ noInterior + ", cp=" + cp + ", alcaldia=" + alcaldia + "]";
	}

}
