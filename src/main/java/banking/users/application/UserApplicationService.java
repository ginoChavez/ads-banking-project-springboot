package banking.users.application;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.common.infrastructure.security.Hashing;
import banking.common.infrastructure.security.JwtTokenProvider;
import banking.persons.domain.entity.Person;
import banking.persons.domain.repository.PersonRepository;
import banking.roles.application.dto.RoleClaimDto;
import banking.roles.domain.entity.RoleClaim;
import banking.roles.domain.repository.RoleClaimRepository;
import banking.roles.domain.repository.RoleRepository;
import banking.users.application.dto.UserAuthDto;
import banking.users.application.dto.UserDto;
import banking.users.domain.entity.User;
import banking.users.domain.repository.UserRepository;

@Service
public class UserApplicationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleClaimRepository roleClaimRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
    
	public UserDto create(UserDto userDto) {
		Notification notification = this.createValidation(userDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		String hashPassword = Hashing.hash(userDto.getPassword());
		userDto.setPassword(hashPassword);
		User user = mapper.map(userDto, User.class);
		user.getPerson().setIsActive(true);
		personRepository.save(user.getPerson());
		user = userRepository.save(user);
		userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
	
	private Notification createValidation(UserDto userDto) {
		Notification notification = new Notification();
		if (userDto == null || userDto.getName().equals(RequestBodyType.INVALID.toString())) {
			notification.addError("Invalid JSON data in request body.");
		}
		User user = userRepository.getByName(userDto.getName().trim());
		if (user != null) {
			notification.addError("User name is already registered");
		}
		return notification;
	}
	
	public UserAuthDto validateUser(UserDto userDto) throws Exception {
		UserAuthDto userAuthDto = new UserAuthDto();
		User authUser = null;
		authUser = this.userRepository.getByName(userDto.getName());
		if (authUser == null) {
			return userAuthDto;
		}		
		if (!Hashing.verifyHash(userDto.getPassword(), authUser.getPassword())) {
			return userAuthDto;
		}
		userAuthDto = this.buildUserAuthDto(authUser);
		return userAuthDto;
	}
	
	private UserAuthDto buildUserAuthDto(User authUser) throws Exception {
		UserAuthDto userAuthDto = new UserAuthDto();
		userAuthDto.setId(authUser.getId());
		userAuthDto.setName(authUser.getName());
		userAuthDto.setAuthenticated(true);
		userAuthDto.setBearerToken(new UUID(0L, 0L).toString());
		List<RoleClaim> claims = roleClaimRepository.findByRoleId(authUser.getRole().getId());
		List<RoleClaimDto> claimsDto = mapper.map(claims, new TypeToken<List<RoleClaimDto>>() {}.getType());
		userAuthDto.setClaims(claimsDto);
		userAuthDto.setBearerToken(jwtTokenProvider.buildJwtToken(userAuthDto));
		return userAuthDto;
	}
	
	public UserDto get(long userId) {
		ModelMapper modelMapper = new ModelMapper();
		User user = this.userRepository.getById(userId);
		UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    
    public List<UserDto> getPaginated(int page, int pageSize) {
		Notification notification = this.getPaginatedValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<User> users = this.userRepository.getPaginated(page, pageSize);
		List<UserDto> usersDto = mapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
        return usersDto;
    }
    
    private Notification getPaginatedValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}
}
