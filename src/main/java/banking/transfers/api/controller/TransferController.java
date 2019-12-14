package banking.transfers.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.transfers.application.TransferApplicationService;
import banking.transfers.application.dto.TransferDto;

@RestController
@RequestMapping("api/transactions")
public class TransferController {
	
	@Autowired
	private TransferApplicationService transferApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> get(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) throws Exception {
		try {
			List<TransferDto> transfers = transferApplicationService.getPaginated(page, pageSize);
			return new ResponseEntity<Object>(transfers, HttpStatus.OK);
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) throws Exception {
		try {
			List<TransferDto> transfers = transferApplicationService.getPaginated(page, pageSize);
			return new ResponseEntity<Object>(transfers, HttpStatus.OK);
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

}
