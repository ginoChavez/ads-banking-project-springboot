package banking.roles.infrastructure.hibernate.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.persons.domain.entity.Person;
import banking.roles.domain.entity.Role;
import banking.roles.domain.repository.RoleRepository;
import banking.roles.domain.entity.Role;

@Transactional
@Repository
public class RoleHibernateRepository extends BaseHibernateRepository<Role> implements RoleRepository {
	public Role getById(long roleId) {
		Role role = new Role();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select r.role_id, r.role_name from role r "
				+ "where r.role_id = ?");
		List<Object[]> rows = query.setLong(0, roleId).list();
		if(!rows.isEmpty()) {
			Object[] row = rows.get(0);
			role = new Role(Long.valueOf(row[0].toString()), (String) row[1]);
		}
		return role;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getPaginated(int page, int pageSize) {
		List<Role> roles = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select r.role_id, r.role_name from role r ");
		query.setFirstResult(page);
		query.setMaxResults(pageSize);
		List<Object[]> rows = query.list();
		Role role = null;
		roles = new ArrayList<Role>();
		if(!rows.isEmpty()) {
			for (Object[] row : rows) {
				role = new Role(Long.valueOf(row[0].toString()), (String) row[1]);
				roles.add(role);
			}
		}
		return roles;
	}
	
	public Role save(Role role) {
		return super.save(role);
	}

	@Override
	public List<Role> getAll() {
		List<Role> roles = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select r.role_id, r.role_name from role r ");
		List<Object[]> rows = query.list();
		Role role = null;
		roles = new ArrayList<Role>();
		if(!rows.isEmpty()) {
			for (Object[] row : rows) {
				role = new Role(Long.valueOf(row[0].toString()), (String) row[1]);
				roles.add(role);
			}
		}
		return roles;
	}
}
