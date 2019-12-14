package banking.transfers.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import banking.persons.application.dto.PersonDto;

public class TransferDto {

	private long id;
	private String numberAccountOrigin;
	private String numberAccountDestiny;
	private Date dateRegistry;
	private BigDecimal amount;
	private String transferType;
	private String operationNumber;
	private PersonDto person;
	
	
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
	public PersonDto getPerson() {
		return person;
	}
	public void setPerson(PersonDto person) {
		this.person = person;
	}
	public String getOperationNumber() {
		return operationNumber;
	}
	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}
	
	
}
