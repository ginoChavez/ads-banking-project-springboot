package banking.roles.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.roles.domain.entity.Role;
import banking.roles.domain.repository.RoleRepository;

@Transactional
@Repository
public class RoleHibernateRepository extends BaseHibernateRepository<Role> implements RoleRepository {
	public Role get(long roleId) {
		Role role = null;
		role = getSession().get(Role.class, roleId);
		return role;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> get(int page, int pageSize) {
		List<Role> roles = null;
		Criteria criteria = getSession().createCriteria(Role.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		roles = criteria.list();
		return roles;
	}
	
	public Role save(Role role) {
		return super.save(role);
	}
}
