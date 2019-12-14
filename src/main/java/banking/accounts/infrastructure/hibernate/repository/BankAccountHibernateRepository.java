package banking.accounts.infrastructure.hibernate.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.persons.domain.entity.Person;

@Transactional
@Repository
public class BankAccountHibernateRepository extends BaseHibernateRepository<BankAccount>
		implements BankAccountRepository {
	@Override
	public BankAccount findByNumber(String accountNumber) throws Exception {
		BankAccount bankAccount = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select a.bank_account_id, a.number, a.balance,  "
				+ "p.person_id, p.first_name, p.last_name, p.id_number, p.address, p.phone, p.email, p.active, a.locked, a.overdraft " + 
				"from bank_account a " + 
				"inner join person p on p.person_id = a.person_id "
				+ "where a.number = ?");
		List<Object[]> rows = query.setString(0, accountNumber).list();
		if(!rows.isEmpty()) {
			Person person = null;
			Object[] row = rows.get(0);
			bankAccount = new BankAccount(Long.valueOf(row[0].toString()), (String) row[1], (BigDecimal) row[2], (Boolean)row[11], (BigDecimal) row[12]);
			person = new Person(Long.valueOf(row[3].toString()), (String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8], (String) row[9], (Boolean)row[10]);
			bankAccount.setPerson(person);
		}
		return bankAccount;
	}

	@Override
	public BankAccount findByNumberLocked(String accountNumber) throws Exception {
		BankAccount bankAccount = null;
		Criteria criteria = getSession().createCriteria(BankAccount.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		bankAccount = (BankAccount) criteria.uniqueResult();
		return bankAccount;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> get(long personId) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select a.bank_account_id, a.number, a.balance,  "
				+ "p.person_id, p.first_name, p.last_name, p.id_number, p.address, p.phone, p.email, p.active, a.locked, a.overdraft " + 
				"from bank_account a " + 
				"inner join person p on p.person_id = a.person_id "
				+ "where p.person_id = ?");
		List<Object[]> rows = query.setLong(0, personId).list();
		BankAccount bankAccount = null;
		if(!rows.isEmpty()) {
			for(Object[] row: rows) {
				Person person = null;
				bankAccount = new BankAccount(Long.valueOf(row[0].toString()), (String) row[1], (BigDecimal) row[2], (Boolean)row[11], (BigDecimal) row[12]);
				person = new Person(Long.valueOf(row[3].toString()), (String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8], (String) row[9], (Boolean)row[10]);
				bankAccount.setPerson(person);
				bankAccounts.add(bankAccount);
			}
		}
		return bankAccounts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> getPaginated(int page, int pageSize,Long personId) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		Session session = sessionFactory.getCurrentSession();
		String q = "select a.bank_account_id, a.number, a.balance,  "
				+ "p.person_id, p.first_name, p.last_name, p.id_number, p.address, p.phone, p.email, p.active, a.locked, a.overdraft " + 
				"from bank_account a " + 
				"inner join person p on p.person_id = a.person_id ";
		if(personId!=null) {
			q+= " where p.person_id = ?";
		}
				
		Query query = session.createSQLQuery(q);
		query.setFirstResult(page);
		query.setMaxResults(pageSize);
		List<Object[]> rows = null;
		if(personId!=null) {
			rows = query.setLong(0, personId).list();
		}else {
			rows = query.list();
		}
		
		BankAccount bankAccount = null;
		if(!rows.isEmpty()) {
			for(Object[] row: rows) {
				Person person = null;
				bankAccount = new BankAccount(Long.valueOf(row[0].toString()), (String) row[1], (BigDecimal) row[2], (Boolean)row[11], (BigDecimal) row[12]);
				person = new Person(Long.valueOf(row[3].toString()), (String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8], (String) row[9], (Boolean)row[10]);
				bankAccount.setPerson(person);
				bankAccounts.add(bankAccount);
			}
		}
		return bankAccounts;
	}
	
	public BankAccount save(BankAccount bankAccount) {
		return super.save(bankAccount);
	}
	
	public BankAccount update(BankAccount bankAccount) {
		return super.update(bankAccount);
	}
}
