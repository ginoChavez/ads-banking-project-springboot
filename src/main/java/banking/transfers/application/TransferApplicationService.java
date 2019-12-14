package banking.transfers.application;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.common.application.Notification;
import banking.transfers.application.dto.TransferDto;
import banking.transfers.domain.entity.Transfer;
import banking.transfers.domain.repository.TransferRepository;

@Service
public class TransferApplicationService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TransferRepository transferRepository;
	
	//@Autowired
	//private PersonRepository personRepository;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
	
	public TransferDto create(TransferDto transferDto) throws Exception {
		Transfer transfer = mapper.map(transferDto, Transfer.class);
		transfer = transferRepository.save(transfer);
		
		
		
		transferDto = mapper.map(transfer, TransferDto.class);
		return transferDto;
	}
	
	public List<TransferDto> getPaginated(int page, int pageSize) {
		Notification notification = this.getPaginatedValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<Transfer> transfers = this.transferRepository.getPaginated(page, pageSize);
		List<TransferDto> transfersDto = mapper.map(transfers, new TypeToken<List<TransferDto>>() {}.getType());
        return transfersDto;
    }
	
	public List<TransferDto> getAll(int page, int pageSize, Long personaId, String account, Date initDate, Date endDate) {
		Notification notification = this.getPaginatedValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<Transfer> transfers = this.transferRepository.getAll(page, pageSize, personaId, account, initDate, endDate);
		List<TransferDto> transfersDto = mapper.map(transfers, new TypeToken<List<TransferDto>>() {}.getType());
        return transfersDto;
    }
	
	
	private Notification getPaginatedValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}

}
