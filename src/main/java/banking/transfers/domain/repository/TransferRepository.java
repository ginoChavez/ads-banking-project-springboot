package banking.transfers.domain.repository;

import java.util.Date;
import java.util.List;

import banking.accounts.domain.entity.BankAccount;
import banking.transfers.domain.entity.Transfer;

public interface TransferRepository {

	public Transfer findById(long id);
	public List<Transfer> getPaginated(int page, int pageSize);
	public List<Transfer> getAll(int page, int pageSize, Long personaId, String account, Date initDate, Date endDate);
	public Transfer save(Transfer transfer);
	public Transfer update(Transfer transfer);
}
