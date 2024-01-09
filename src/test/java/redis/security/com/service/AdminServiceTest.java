package redis.security.com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import redis.security.com.dao.Authority;
import redis.security.com.dao.User;
import redis.security.com.dto.AuthorityDTO;
import redis.security.com.dto.UserDto;
import redis.security.com.repository.AuthorityRepository;
import redis.security.com.repository.JpaUserRepository;
import redis.security.com.repository.RedisUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    public AdminService adminService;

    @Mock
    JpaUserRepository jpaUserRepository;

    @Mock
    RedisUserRepository redisUserRepository;

    @Mock
    AuthorityRepository authorityRepository;

    @Mock
    PasswordEncoder encoder;

    public User user;
    public Authority authority;

    @BeforeEach
    public void init () {
        user = createUser ();
    }

    @BeforeEach
    public void createAuthority () {
        authority = new Authority();
        authority.setAuthority("ADMIN");
        authority.setId(1);
        authority.setUsername(user);
    }

    public User createUser () {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("testpsw");
        user.setLastActivity("2023-12-12");
        user.setPriority(5);
        user.setIsDeleted(false);
        return user;
    }

    @Test
    public void createUserTest () {
        UserDto userDto = UserDto.parseUser(user);
        Mockito.when(encoder.encode(userDto.getPassword())).thenReturn(user.getPassword());
        User testUser = adminService.createUser(userDto);
        assertEquals(testUser, user);
    }

    @Test
    public void updateUserTest () {
        Optional <User> userOptional = Optional.of(createUser());
        UserDto userDto = UserDto.parseUser(user);
        userDto.setUsername("Test_2");
        Mockito.when(jpaUserRepository.findById(userDto.getId())).thenReturn(userOptional);
        User testUser = adminService.updateUser(userDto);
        user.setUsername("Test_2");
        assertEquals(user, testUser);
    }

    @Test
    public void softDeleteUser () {
        Optional <User> userOptional = Optional.of(createUser());
        UserDto userDto = UserDto.parseUser(user);
        Mockito.when(jpaUserRepository.findById(userDto.getId())).thenReturn(userOptional);
        UserDto testUser = adminService.softDeleteUser(userDto.getId());
        user.setIsDeleted(true);
        assertEquals(UserDto.parseUser(user), testUser);
    }

    @Test
    public void addAuthority () {
        Mockito.when(jpaUserRepository.findUserByUsername(authority.getUsername().getUsername())).thenReturn(user);
        AuthorityDTO newAuthority = new AuthorityDTO();
        newAuthority.setAuthority("ADMIN");
        newAuthority.setUsername("Test");
        AuthorityDTO expectedAuthority = adminService.addAuthority(newAuthority);
        assertEquals(AuthorityDTO.parseAuthorityDto(authority), expectedAuthority);
    }
}
