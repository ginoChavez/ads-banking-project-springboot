package banking.accounts.domain.repository;

import java.util.List;

import banking.accounts.domain.entity.BankAccount;
import banking.transfers.domain.entity.Transfer;

public interface BankAccountRepository {
	BankAccount findByNumber(String accountNumber) throws Exception;
	BankAccount findByNumberLocked(String accountNumber) throws Exception;
	List<BankAccount> get(long personId);
	List<BankAccount> getPaginated(int page, int pageSize,long personId);
	BankAccount persist(BankAccount bankAccount);
	BankAccount save(BankAccount bankAccount);
	BankAccount update(BankAccount bankAccount);
}
