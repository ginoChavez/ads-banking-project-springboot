package banking.persons.domain.repository;

import java.util.List;

import banking.persons.domain.entity.Person;

public interface PersonRepository {
	public Person save(Person person);
	public List<Person> get(int page, int pageSize);
	public Person get(long personId);
}
