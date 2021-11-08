package app.keepcash.srpago.model.customer;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerUpdateResult implements Serializable {

	private static final long serialVersionUID = -3569967141513761502L;

	private String id;
	private String name;
	private String email;
	private Boolean active;
	private String date_create;
	private ArrayList<?> sources;
	private ArrayList<?> cards;
	private Integer deactivated_cards;

	public CustomerUpdateResult() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDate_create() {
		return date_create;
	}

	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}

	public ArrayList<?> getSources() {
		return sources;
	}

	public void setSources(ArrayList<?> sources) {
		this.sources = sources;
	}

	public ArrayList<?> getCards() {
		return cards;
	}

	public void setCards(ArrayList<?> cards) {
		this.cards = cards;
	}

	public Integer getDeactivated_cards() {
		return deactivated_cards;
	}

	public void setDeactivated_cards(Integer deactivated_cards) {
		this.deactivated_cards = deactivated_cards;
	}

	public CustomerUpdateResult(String id, String name, String email, Boolean active, String date_create,
			ArrayList<?> sources, ArrayList<?> cards, Integer deactivated_cards) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.active = active;
		this.date_create = date_create;
		this.sources = sources;
		this.cards = cards;
		this.deactivated_cards = deactivated_cards;
	}

	@Override
	public String toString() {
		return "CustomerUpdateResult [id=" + id + ", name=" + name + ", email=" + email + ", active=" + active
				+ ", date_create=" + date_create + ", sources=" + sources + ", cards=" + cards + ", deactivated_cards="
				+ deactivated_cards + "]";
	}

}
