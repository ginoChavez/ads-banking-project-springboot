package banking.transactions.application.dto.deserializer;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import banking.common.application.enumeration.RequestBodyType;
import banking.persons.application.dto.PersonDto;
import banking.persons.domain.entity.Person;
import banking.transactions.application.dto.RequestBankTransferDto;

public class RequestBankTransferDtoDeserializer extends JsonDeserializer<RequestBankTransferDto> {
	@Override
	public RequestBankTransferDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		RequestBankTransferDto requestBankTransferDto = null;
		try {
    		ObjectCodec objectCodec = jsonParser.getCodec();
            JsonNode node = objectCodec.readTree(jsonParser);
            String fromAccountNumber = node.get("fromAccountNumber")==null?null:node.get("fromAccountNumber").asText();
            String toAccountNumber = node.get("toAccountNumber")==null?null:node.get("toAccountNumber").asText();
            String transferType = node.get("transferType").asText();
            BigDecimal amount = new BigDecimal(node.get("amount").asText());
            requestBankTransferDto = new RequestBankTransferDto(fromAccountNumber, toAccountNumber, amount, transferType);
    	} catch(Exception ex) {
    		requestBankTransferDto = new RequestBankTransferDto(RequestBodyType.INVALID.toString(), RequestBodyType.INVALID.toString(), null);
    	}
        return requestBankTransferDto;
	}	
}
