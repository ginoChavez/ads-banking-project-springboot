package banking.users.domain.entity;

import banking.persons.domain.entity.Person;
import banking.roles.domain.entity.Role;

public class User {
	private long id;
    private String name;
    private String password;
    private Person person;
    private Role role;
    
    
    public User(String name, Person person, Role role) {
		super();
		this.name = name;
		this.person = person;
		this.role = role;
	}
    
	public User(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public User(String name, String password, Person person, Role role) {
		super();
		this.name = name;
		this.password = password;
		this.person = person;
		this.role = role;
	}
	
	

	public User(long id, String name, Person person, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.person = person;
		this.role = role;
	}

	public User() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
