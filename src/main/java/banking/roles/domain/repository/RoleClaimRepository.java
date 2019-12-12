package banking.roles.domain.repository;

import java.util.List;

import banking.roles.domain.entity.RoleClaim;

public interface RoleClaimRepository {
	public List<RoleClaim> findByRoleId(Long roleId) throws Exception;
}
	