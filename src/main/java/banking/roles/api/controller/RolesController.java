package banking.roles.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.roles.application.RoleApplicationService;
import banking.roles.application.dto.RoleDto;

@RestController
@RequestMapping("api/roles")
public class RolesController {
	@Autowired
	RoleApplicationService roleApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(method = RequestMethod.POST, path = "", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> create(@RequestBody RoleDto roleDto) throws Exception {
        try {
        	roleDto = roleApplicationService.create(roleDto);
        	return this.responseHandler.getResponse("Role Registered!", HttpStatus.CREATED);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> get() throws Exception {
		try {
			List<RoleDto> roles = roleApplicationService.getAll();
			return new ResponseEntity<Object>(roles, HttpStatus.OK);
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> get(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) throws Exception {
		try {
			List<RoleDto> roles = roleApplicationService.getPaginated(page, pageSize);
			return new ResponseEntity<Object>(roles, HttpStatus.OK);
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{roleId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> get(@PathVariable("roleId") long roleId) throws Exception {
		try {
			RoleDto roleDto = roleApplicationService.get(roleId);
			return new ResponseEntity<Object>(roleDto, HttpStatus.OK);
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
}
