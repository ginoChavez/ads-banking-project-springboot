package banking.transfers.domain.repository;

import java.util.List;

import banking.accounts.domain.entity.BankAccount;
import banking.transfers.domain.entity.Transfer;

public interface TransferRepository {

	public Transfer findById(long id);
	public List<Transfer> getPaginated(int page, int pageSize);
	public Transfer save(Transfer transfer);
}
