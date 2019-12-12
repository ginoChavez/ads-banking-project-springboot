package banking.roles.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.roles.application.dto.RoleClaimDto;
import banking.roles.domain.entity.Role;
import banking.roles.domain.entity.RoleClaim;
import banking.roles.domain.repository.RoleClaimRepository;

@Service
public class RoleApplicationService {
	
	@Autowired
	private RoleClaimRepository roleClaimRepository;
	
	@Autowired
	private ModelMapper mapper;
		
}
