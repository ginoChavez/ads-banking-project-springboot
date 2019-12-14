package banking.accounts.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.accounts.application.AccountApplicationService;
import banking.accounts.application.dto.BankAccountDto;
import banking.common.api.controller.ResponseHandler;

@RestController
@RequestMapping("api/accounts")
public class AccountsController {
	@Autowired
	AccountApplicationService accountApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(method = RequestMethod.POST, path = "/{personId}/accounts", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> create(@PathVariable("personId") long personId, @RequestBody BankAccountDto bankAccountDto) throws Exception {
        try {
        	bankAccountDto = accountApplicationService.create(personId, bankAccountDto);
        	return this.responseHandler.getResponse("Registered account successfully!", HttpStatus.CREATED);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/bycustomer/{personId}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getByCustomer(@PathVariable("personId") long personId) {
        try {
            List<BankAccountDto> persons = accountApplicationService.get(personId);
            return this.responseHandler.getDataResponse(persons, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getPaginated(
    		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize,
    		@RequestParam(value = "personId", required = false, defaultValue = "") Long personId) {
        try {
            List<BankAccountDto> persons = accountApplicationService.getPaginated(page, pageSize, personId);
            return this.responseHandler.getDataResponse(persons, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "/locked", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> create(@RequestBody BankAccountDto bankAccountDto) throws Exception {
        try {
        	bankAccountDto = accountApplicationService.locked(bankAccountDto);
        	return this.responseHandler.getResponse("Account locked successfully!", HttpStatus.GONE);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
}
