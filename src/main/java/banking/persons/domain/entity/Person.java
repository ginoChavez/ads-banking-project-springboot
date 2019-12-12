package banking.persons.domain.entity;

import java.util.Set;

import banking.accounts.domain.entity.BankAccount;
import banking.users.domain.entity.User;

public class Person {
	private long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
    private String phone;
    private String email;
    private Boolean isActive;
    private Set<BankAccount> bankAccounts;
    private Set<User> users;
	public Person() {
    }
	
    public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(long id, String firstName, String lastName, Boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isActive = isActive;
	}


	public Person(String firstName, String lastName, Boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.isActive = isActive;
	}
	
	public Person(long id, String firstName, String lastName, String idNumber, String address, String phone, String email,
			Boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.isActive = isActive;
	}

	public String getFullName() {
        return String.format("%s, %s", this.lastName, this.firstName);
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
    
    
}
