package banking.users.infrastructure.hibernate.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.persons.domain.entity.Person;
import banking.roles.domain.entity.Role;
import banking.users.domain.entity.User;
import banking.users.domain.repository.UserRepository;

@Transactional
@Repository
public class UserHibernateRepository extends BaseHibernateRepository<User> implements UserRepository {
	public User getById(long userId) {
		
		User user = new User();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select u.user_id, u.user_name, r.role_id, r.role_name, p.person_id, p.first_name, p.last_name, "
				+ " p.id_number, p.address, p.phone, p.email, p.active " + 
				"from user u " + 
				"left join person p on u.person_id = p.person_id " + 
				"inner join role r on u.role_id=r.role_id "
				+ "where u.user_id = ?");
		List<Object[]> rows = query.setLong(0, userId).list();
		//User user = null;
		if(!rows.isEmpty()) {
			Role role = null;
			Person person = null;
			Object[] row = rows.get(0);
			user = new User(Long.valueOf(row[0].toString()), (String) row[1]);
			role = new Role(Long.valueOf(row[2].toString()), (String) row[3]);
			if(row[4]!=null) {
				person = new Person(Long.valueOf(row[4].toString()), (String) row[5], (String) row[6], (String) row[7], (String) row[8], (String) row[9], (String) row[10], (Boolean)row[11]);
			}
			user.setPerson(person);
			user.setRole(role);
		}
		return user;
	}
	
	public User getByName(String name) {
		User user = null;
		Criteria criteria = getSession().createCriteria(User.class, "u");
		criteria.createAlias("u.role","r",JoinType.INNER_JOIN);
		criteria.createAlias("r.claims", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("u.name", name));
		user = (User) criteria.uniqueResult();
		return user;
	}
	
	public User save(User user) {
		return super.save(user);
	}

	@Override
	public List<User> getPaginated(int page, int pageSize) {
		List<User> users = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select u.user_id, u.user_name, r.role_id, r.role_name, p.person_id, p.first_name, p.last_name, "
				+ " p.id_number, p.address, p.phone, p.email, p.active " + 
				"from user u " + 
				"left join person p on u.person_id = p.person_id " + 
				"inner join role r on u.role_id=r.role_id");
		query.setFirstResult(page);
		query.setMaxResults(pageSize);
		List<Object[]> rows = query.list();
		User user = null;
		Role role = null;
		Person person = null;
		users = new ArrayList<User>();
		if(!rows.isEmpty()) {
			for (Object[] row : rows) {
				user = new User(Long.valueOf(row[0].toString()), (String) row[1]);
				role = new Role(Long.valueOf(row[2].toString()), (String) row[3]);
				if(row[4]!=null) {
					person = new Person(Long.valueOf(row[4].toString()), (String) row[5], (String) row[6], (String) row[7], (String) row[8], (String) row[9], (String) row[10], (Boolean)row[11]);
				}
				user.setPerson(person);
				user.setRole(role);
				users.add(user);
			}
		}
		return users;
	}
}
