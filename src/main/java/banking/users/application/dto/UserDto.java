package banking.users.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import banking.persons.application.dto.PersonDto;
import banking.roles.application.dto.RoleDto;
import banking.users.application.dto.deserializer.UserDtoDeserializer;

@JsonDeserialize(using = UserDtoDeserializer.class)
public class UserDto {
	private long id;
	private String name;
	private String password;
	private PersonDto person;
	private RoleDto role;
	
	public UserDto() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public UserDto(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	

	public UserDto(String name, String password, PersonDto person, RoleDto role) {
		super();
		this.name = name;
		this.password = password;
		this.person = person;
		this.role = role;
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

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

}
