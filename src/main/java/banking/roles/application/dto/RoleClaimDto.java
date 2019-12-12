package banking.roles.application.dto;

public class RoleClaimDto {
	private long id;
    private String type;
    private String value;
    private RoleDto role;
	
    public long getId() {
		return id;
	}
	
    public void setId(long id) {
		this.id = id;
	}
	
    public String getType() {
		return type;
	}
	
    public void setType(String type) {
		this.type = type;
	}
	
    public String getValue() {
		return value;
	}
	
    public void setValue(String value) {
		this.value = value;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}
	
    
}
