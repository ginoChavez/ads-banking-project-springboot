package banking.roles.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.roles.application.dto.RoleDto;
import banking.roles.domain.entity.Role;
import banking.roles.domain.repository.RoleRepository;

@Service
public class RoleApplicationService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public RoleDto create(RoleDto roleDto) {
		Role role = mapper.map(roleDto, Role.class);
		role = roleRepository.save(role);
		roleDto = mapper.map(role, RoleDto.class);
        return roleDto;
    }
	
	public RoleDto get(long roleId) {
		ModelMapper modelMapper = new ModelMapper();
		Role role = this.roleRepository.getById(roleId);
		RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        return roleDto;
    }
    
    public List<RoleDto> getPaginated(int page, int pageSize) {
		List<Role> roles = this.roleRepository.getPaginated(page, pageSize);
		List<RoleDto> rolesDto = mapper.map(roles, new TypeToken<List<RoleDto>>() {}.getType());
        return rolesDto;
    }
    
    public List<RoleDto> getAll() {
		List<Role> roles = this.roleRepository.getAll();
		List<RoleDto> rolesDto = mapper.map(roles, new TypeToken<List<RoleDto>>() {}.getType());
        return rolesDto;
    }
		
}
