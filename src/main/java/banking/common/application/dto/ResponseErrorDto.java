package banking.common.application.dto;

import java.util.List;

public class ResponseErrorDto {
	private List<ErrorDto> errors;
	
	public ResponseErrorDto() {
	}
	
	public ResponseErrorDto(List<ErrorDto> errors) {
		this.errors = errors;
	}

	public List<ErrorDto> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDto> errors) {
		this.errors = errors;
	}
}
