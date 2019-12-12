package banking.persons.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.persons.application.PersonApplicationService;
import banking.persons.application.dto.PersonDto;

@RestController
@RequestMapping("api/persons")
public class PersonController {
	@Autowired
	PersonApplicationService personApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(method = RequestMethod.POST, path = "", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> create(@RequestBody PersonDto personDto) throws Exception {
        try {
        	personDto = personApplicationService.create(personDto);
            //return new ResponseEntity<Object>(personDto, HttpStatus.OK);
            return this.responseHandler.getResponse("Person Registered!", HttpStatus.CREATED);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getPaginated(
    		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        try {
            List<PersonDto> persons = personApplicationService.get(page, pageSize);
            return this.responseHandler.getDataResponse(persons, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
}
