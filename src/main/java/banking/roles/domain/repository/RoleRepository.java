package banking.roles.domain.repository;

import java.util.List;

import banking.roles.domain.entity.Role;

public interface RoleRepository {
	public Role save(Role role);
	public List<Role> get(int page, int pageSize);
	public Role get(long roleId);
}
