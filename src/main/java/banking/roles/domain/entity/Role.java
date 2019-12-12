package banking.roles.domain.entity;

import java.util.Set;

import banking.users.domain.entity.User;

public class Role {
	private long id;
	private String name;
	private Set<User> users;
	private Set<RoleClaim> claims;
	public long getId() {
		return id;
	}
	
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}
	
	public Role(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<RoleClaim> getClaims() {
		return claims;
	}
	public void setClaims(Set<RoleClaim> claims) {
		this.claims = claims;
	}
	
	
}
