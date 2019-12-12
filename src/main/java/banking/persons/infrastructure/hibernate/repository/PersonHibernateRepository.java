package banking.persons.infrastructure.hibernate.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.persons.domain.entity.Person;
import banking.persons.domain.repository.PersonRepository;

@Transactional
@Repository
public class PersonHibernateRepository extends BaseHibernateRepository<Person> implements PersonRepository {
	public Person get(long personId) {
		Person person = null;
		person = getSession().get(Person.class, personId);
		return person;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> get(int page, int pageSize) {
		List<Person> persons = new ArrayList<Person>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.person_id, p.first_name, p.last_name, "
				+ " p.id_number, p.address, p.phone, p.email, p.active " + 
				"from person p ");
		query.setFirstResult(page);
		query.setMaxResults(pageSize);
		List<Object[]> rows = query.list();
		Person person = null;
		if(!rows.isEmpty()) {
			for (Object[] row : rows) {
			    person = new Person(Long.valueOf(row[0].toString()), (String) row[1], (String) row[2], (String) row[3], (String) row[4], (String) row[5], (String) row[6], (Boolean)row[7]);
				persons.add(person);
			}
		}
		return persons;
		/*List<Person> persons = null;
		Criteria criteria = getSession().createCriteria(Person.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		persons = criteria.list();
		return persons;*/
	}
	
	public Person save(Person person) {
		return super.save(person);
	}
}
