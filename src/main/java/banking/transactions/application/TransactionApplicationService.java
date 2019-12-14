package banking.transactions.application;

import java.util.Date;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.persons.domain.entity.Person;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.service.TransferDomainService;
import banking.transfers.domain.entity.Transfer;
import banking.transfers.domain.repository.TransferRepository;
import banking.transfers.infrastructure.email.GestionDeCorreo;
import banking.transfers.infrastructure.email.MensajeEmail;
import banking.users.domain.repository.UserRepository;

@Service
public class TransactionApplicationService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private TransferDomainService transferDomainService;
	
	@Autowired
	private TransferRepository transferRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private ModelMapper mapper;

	@Transactional
	public void performTransfer(RequestBankTransferDto requestBankTransferDto) throws Exception {
		Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		/*BankAccount originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getFromAccountNumber());
		BankAccount destinationAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getToAccountNumber());
		this.transferDomainService.performTransfer(originAccount, destinationAccount, requestBankTransferDto.getAmount());
		this.bankAccountRepository.save(originAccount);
		this.bankAccountRepository.save(destinationAccount);*/
		
		Transfer transfer = new Transfer();
		BankAccount bankAccount = null;
		Boolean isAccountDestinityEmpty = requestBankTransferDto.getToAccountNumber()==null
				||Objects.deepEquals(requestBankTransferDto.getToAccountNumber(),"")
				||Objects.deepEquals(requestBankTransferDto.getToAccountNumber(),"null");
		Boolean isAccountOriginEmpty = requestBankTransferDto.getFromAccountNumber()==null
				||Objects.deepEquals(requestBankTransferDto.getFromAccountNumber(),"")
				||Objects.deepEquals(requestBankTransferDto.getFromAccountNumber(),"null");
		if(Objects.equals(requestBankTransferDto.getTransferType(), "D")) {
			
			if(!isAccountDestinityEmpty) {
				bankAccount = bankAccountRepository.findByNumber(requestBankTransferDto.getToAccountNumber());
				if(bankAccount!=null) {
					this.transferDomainService.performTransferByType(bankAccount, requestBankTransferDto.getAmount(), true);
					this.bankAccountRepository.update(bankAccount);
					
					transfer.setNumberAccountDestiny(bankAccount.getNumber());
					transfer.setPerson(bankAccount.getPerson());
					transfer.setTransferType(requestBankTransferDto.getTransferType());
					transfer.setAmount(requestBankTransferDto.getAmount());
					transfer.setDateRegistry(new Date());
					transferRepository.save(transfer);
					GestionDeCorreo.enviarCorreo(
							MensajeEmail.mensajeDeTransferencia(
									transfer.getPerson().getEmail(),
									requestBankTransferDto.getFromAccountNumber(), 
									requestBankTransferDto.getToAccountNumber(),
									transfer.getAmount(),
									transfer.getPerson().getFirstName(),
									transfer.getDateRegistry())
							);
					
				}else {
					throw new IllegalArgumentException("The destinity account number doesn't exist!");
				}
			}else {
				throw new IllegalArgumentException("You must enter the destinity account number!");
			}
		} else if(Objects.equals(requestBankTransferDto.getTransferType(), "R")) {
			if(!isAccountOriginEmpty) {
				bankAccount = bankAccountRepository.findByNumber(requestBankTransferDto.getFromAccountNumber());
				if(bankAccount!=null) {
					this.transferDomainService.performTransferByType(bankAccount, requestBankTransferDto.getAmount(), false);
					this.bankAccountRepository.update(bankAccount);
					
					transfer.setNumberAccountOrigin(bankAccount.getNumber());
					transfer.setPerson(bankAccount.getPerson());
					transfer.setTransferType(requestBankTransferDto.getTransferType());
					transfer.setAmount(requestBankTransferDto.getAmount());
					transfer.setDateRegistry(new Date());
					transferRepository.save(transfer);
				}else {
					throw new IllegalArgumentException("The origin account number doesn't exist!");
				}
			}else {
				throw new IllegalArgumentException("You must enter the origin account number!");
			}
		} else if(Objects.equals(requestBankTransferDto.getTransferType(), "TC")
				||Objects.equals(requestBankTransferDto.getTransferType(), "TT")) {
			if(!isAccountDestinityEmpty&&!isAccountOriginEmpty) {
				//Registro de movimiento de deposito para la cuenta Destino.
				String operationNumber = getRandom(5l);
				bankAccount = bankAccountRepository.findByNumber(requestBankTransferDto.getToAccountNumber());
				if(bankAccount!=null) {
					this.transferDomainService.performTransferByType(bankAccount, requestBankTransferDto.getAmount(), true);
					this.bankAccountRepository.update(bankAccount);
					transfer = new Transfer();
					transfer.setNumberAccountDestiny(bankAccount.getNumber());
					transfer.setPerson(bankAccount.getPerson());
					transfer.setTransferType(requestBankTransferDto.getTransferType());
					transfer.setAmount(requestBankTransferDto.getAmount());
					transfer.setDateRegistry(new Date());
					transfer.setOperationNumber(operationNumber);
					transferRepository.save(transfer);
					GestionDeCorreo.enviarCorreo(
							MensajeEmail.mensajeDeTransferencia(
									transfer.getPerson().getEmail(),
									requestBankTransferDto.getFromAccountNumber(), 
									requestBankTransferDto.getToAccountNumber(),
									transfer.getAmount(),
									transfer.getPerson().getFirstName(),
									transfer.getDateRegistry())
							);
				}else {
					throw new IllegalArgumentException("The destination account number doesn't exist!");
				}
				
				//Registro de movimiento de retiro para la cuenta Origen.
				bankAccount = bankAccountRepository.findByNumber(requestBankTransferDto.getFromAccountNumber());
				if(bankAccount!=null) {
					this.transferDomainService.performTransferByType(bankAccount, requestBankTransferDto.getAmount(), false);
					this.bankAccountRepository.update(bankAccount);
					transfer = new Transfer();
					transfer.setNumberAccountOrigin(bankAccount.getNumber());
					transfer.setPerson(bankAccount.getPerson());
					transfer.setTransferType(requestBankTransferDto.getTransferType());
					transfer.setAmount(requestBankTransferDto.getAmount());
					transfer.setDateRegistry(new Date());
					transfer.setOperationNumber(operationNumber);
					transferRepository.save(transfer);
				}else {
					throw new IllegalArgumentException("The origin account number doesn't exist!");
				}
			}else {
				throw new IllegalArgumentException("You must enter the origin account number and the destinity account number!");
			}
			
		}else {
			throw new IllegalArgumentException("Invalid transaction type!");
		}
	}
	
	private Notification validation(RequestBankTransferDto requestBankTransferDto) {
		Notification notification = new Notification();
		if (requestBankTransferDto == null || requestBankTransferDto.getFromAccountNumber().equals(RequestBodyType.INVALID.toString())) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
	
	public String getRandom(long idTransfer) {
		String a = String.valueOf(Math.random() * 9 + 0);
		String b = String.valueOf(idTransfer)+a.substring(2, a.length());
		return b.substring(0, 8);
	}
}
