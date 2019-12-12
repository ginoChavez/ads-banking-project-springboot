package banking.roles.application.dto;

import java.util.Set;

import banking.users.application.dto.UserDto;

public class RoleDto {
	private long id;
	private String name;
	private Set<UserDto> users;
	private Set<RoleClaimDto> claims;
	
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
	public Set<UserDto> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}
	public Set<RoleClaimDto> getClaims() {
		return claims;
	}
	public void setClaims(Set<RoleClaimDto> claims) {
		this.claims = claims;
	}
	
	
}
