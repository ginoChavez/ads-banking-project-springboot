package banking.users.application.dto.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import banking.common.application.enumeration.RequestBodyType;
import banking.persons.application.dto.PersonDto;
import banking.persons.domain.entity.Person;
import banking.roles.application.dto.RoleDto;
import banking.roles.domain.entity.Role;
import banking.users.application.dto.UserDto;

public class UserDtoDeserializer extends JsonDeserializer<UserDto> {
	@Override
	public UserDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		UserDto userDto = null;
		try {
    		ObjectCodec objectCodec = jsonParser.getCodec();
    		PersonDto person = null;
            JsonNode node = objectCodec.readTree(jsonParser);
            JsonNode nodePerson = node.get("person");
            if(nodePerson!=null) {
            	person = new PersonDto(nodePerson.get("firstName").asText(), 
            			nodePerson.get("lastName").asText(),
            			nodePerson.get("idNumber").asText(),
            			nodePerson.get("address").asText(),
            			nodePerson.get("phone").asText(),
            			nodePerson.get("email")==null?"":nodePerson.get("email").asText());
            }
            
            JsonNode nodeRole = node.get("role");
            RoleDto role = null;
            if(nodeRole!=null) {
            	role = new RoleDto();
            	role.setId(nodeRole.get("id").asLong());
            	role.setName(nodeRole.get("name").asText());
            }
            String name = node.get("name").asText();
            String password = node.get("password").asText();
            userDto = new UserDto(name, password, person, role);
    	} catch(Exception ex) {
    		userDto = new UserDto(RequestBodyType.INVALID.toString(), RequestBodyType.INVALID.toString());
    	}
        return userDto;
	}	
}
