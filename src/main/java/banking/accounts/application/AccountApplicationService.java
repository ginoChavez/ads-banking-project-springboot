package banking.accounts.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.application.dto.BankAccountDto;
import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.persons.domain.entity.Person;
import banking.persons.domain.repository.PersonRepository;

@Service
public class AccountApplicationService {
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	private ModelMapper mapper;
    
	public BankAccountDto create(long personId, BankAccountDto bankAccountDto) throws Exception {
		Notification notification = this.createValidation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		BankAccount bankAccount = mapper.map(bankAccountDto, BankAccount.class);
		bankAccount.setIsLocked(false);
		Person person = personRepository.get(personId);
		bankAccount.setPerson(person);
		bankAccount = bankAccountRepository.save(bankAccount);
		bankAccountDto = mapper.map(bankAccount, BankAccountDto.class);
        return bankAccountDto;
    }
	
	private Notification createValidation(BankAccountDto bankAccountDto) throws Exception {
		Notification notification = new Notification();
		BankAccount bankAccount = bankAccountRepository.findByNumber(bankAccountDto.getNumber());
		if (bankAccount != null) {
			notification.addError("BankAccount number is already registered");
		}
		return notification;
	}
	
	public List<BankAccountDto> get(long personId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
	      .setFieldMatchingEnabled(true)
	      .setFieldAccessLevel(AccessLevel.PRIVATE)
	      .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
		List<BankAccount> bankAccounts = this.bankAccountRepository.get(personId);
		List<BankAccountDto> bankAccountsDto = modelMapper.map(bankAccounts, new TypeToken<List<BankAccountDto>>() {}.getType());
        return bankAccountsDto;
    }
}
