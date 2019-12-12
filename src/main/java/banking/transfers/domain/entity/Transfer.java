package banking.transfers.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import banking.persons.domain.entity.Person;

public class Transfer {
	
	private long id;
	private String numberAccountOrigin;
	private String numberAccountDestiny;
	private Date dateRegistry;
	private BigDecimal amount;
	private String transferType;
	private Person person;
	
	public Transfer() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumberAccountOrigin() {
		return numberAccountOrigin;
	}

	public void setNumberAccountOrigin(String numberAccountOrigin) {
		this.numberAccountOrigin = numberAccountOrigin;
	}

	public String getNumberAccountDestiny() {
		return numberAccountDestiny;
	}

	public void setNumberAccountDestiny(String numberAccountDestiny) {
		this.numberAccountDestiny = numberAccountDestiny;
	}

	public Date getDateRegistry() {
		return dateRegistry;
	}

	public void setDateRegistry(Date dateRegistry) {
		this.dateRegistry = dateRegistry;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
}
