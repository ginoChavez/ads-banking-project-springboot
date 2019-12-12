package banking.persons.application.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import banking.accounts.application.dto.BankAccountDto;
import banking.transfers.application.dto.TransferDto;

public class PersonDto {
	private long id;
	private String firstName;
	private String lastName;
	private String idNumber;
    private String address;
    private String phone;
    private String email;
	private Boolean isActive;
	private Set<BankAccountDto> bankAccounts;
	private Set<TransferDto> transfers;
	
	public PersonDto() {
    }
	
	public PersonDto(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public PersonDto(String firstName, String lastName, String idNumber, String address, String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonProperty(value="isActive")
	public Boolean isActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<BankAccountDto> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankAccountDto> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public Set<TransferDto> getTransfers() {
		return transfers;
	}

	public void setTransfers(Set<TransferDto> transfers) {
		this.transfers = transfers;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}
	
}
