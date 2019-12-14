package banking.transfers.infrastructure.hibernate.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.persons.domain.entity.Person;
import banking.transfers.domain.entity.Transfer;
import banking.transfers.domain.repository.TransferRepository;

@Transactional
@Repository
public class TransferHibernateRepository extends BaseHibernateRepository<Transfer> implements TransferRepository{
	
	@Override
	public Transfer findById(long id){
		Transfer transfer = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select t.transfer_id, t.number_account_origin, t.number_account_destiny, t.date_registry, t.amount, t.operation_number from Transfer t "
				+ "where t.transfer_id = ?");
		List<Object[]> rows = query.setLong(0, id).list();
		for (Object[] row : rows) {
			transfer = new Transfer();
			transfer.setId((long) row[0]);
			transfer.setNumberAccountOrigin((String) row[1]);
			transfer.setNumberAccountDestiny((String) row[2]);
			transfer.setDateRegistry((Date) row[3]);
			transfer.setAmount((BigDecimal) row[4]);
			transfer.setOperationNumber((String) row[5]);
			break;
		}
		return transfer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transfer> getPaginated(int page, int pageSize) {
		
		List<Transfer> transfers = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select CONVERT(t.transfer_id, CHAR), t.number_account_origin, t.number_account_destiny, t.date_registry, t.amount, t.transfer_type, "
				+ "p.person_id, p.first_name, p.last_name, p.id_number, p.address, p.phone, p.email, p.active, t.operation_number "
				+ "from Transfer t inner join person p on t.person_id = p.person_id ");
		query.setFirstResult(page);
		query.setMaxResults(pageSize);
		List<Object[]> rows = query.list();
		Transfer transfer = null;
		Person person = null;
		transfers = new ArrayList<Transfer>();
		for (Object[] row : rows) {
			transfer = new Transfer();
			transfer.setId(Long.valueOf(row[0].toString()));
			transfer.setNumberAccountOrigin((String) row[1]);
			transfer.setNumberAccountDestiny((String) row[2]);
			transfer.setDateRegistry((Date) row[3]);
			transfer.setAmount((BigDecimal) row[4]);
			transfer.setTransferType((String) row[5]);
			transfer.setOperationNumber((String) row[14]);
			person = new Person(Long.valueOf(row[6].toString()), (String) row[7], (String) row[8], (String) row[9], (String) row[10], (String) row[11], (String) row[12], (Boolean)row[13]);
			transfer.setPerson(person);
			transfers.add(transfer);
		}
		return transfers;
	}
	
	@Override
	public Transfer save(Transfer transfer) {
		return super.save(transfer);
	}
	
	@Override
	public Transfer update(Transfer transfer) {
		return super.save(transfer);
	}
}
