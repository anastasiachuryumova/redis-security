package redis.security.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.security.com.dao.Authority;
import redis.security.com.dao.User;
import redis.security.com.dto.AuthorityDTO;
import redis.security.com.dto.UserDto;
import redis.security.com.repository.AuthorityRepository;
import redis.security.com.repository.JpaUserRepository;
import redis.security.com.repository.RedisUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final JpaUserRepository jpaUserRepository;
    private final AuthorityRepository authorityRepository;
    private final RedisUserRepository redisUserRepository;
    private final PasswordEncoder encoder;

    public List <UserDto> getAllUsers () {
        return jpaUserRepository.findAll().stream().map(UserDto::parseUser).toList();
    }

    public UserDto getUserById (Integer id) {
        try {
            UserDto cashedUser = redisUserRepository.findById(id).orElse(null);
            if (cashedUser != null)
                return cashedUser;
            Thread.sleep(5 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserDto.parseUser(jpaUserRepository.findById(id).orElse(null));
    }

    public User createUser (UserDto userDto) {
        User user = UserDto.parseUserDto(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        jpaUserRepository.save(user);
        redisUserRepository.save(UserDto.parseUser(user));
        return user;
    }

    public User updateUser (UserDto userDto) {
        User user = jpaUserRepository.findById(userDto.getId()).orElse(null);
        if (user != null) {
            user = UserDto.parseUserDto(userDto);
            jpaUserRepository.save(user);
        }
        return user;
    }

    public UserDto softDeleteUser (Integer id) {
        User user = jpaUserRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsDeleted(user.getIsDeleted() == null || !user.getIsDeleted());
            jpaUserRepository.save(user);
        }
        return UserDto.parseUser(user);
    }

    public void deleteUser (Integer id) {
        jpaUserRepository.findById(id).ifPresent(user -> jpaUserRepository.deleteById(id));
    }

    public AuthorityDTO addAuthority (AuthorityDTO authorityDTO) {
        User user = jpaUserRepository.findUserByUsername(authorityDTO.getUsername());
        Authority authority = new Authority();
        authority.setAuthority(authorityDTO.getAuthority());
        authority.setUsername(user);
        authorityRepository.save(authority);
        return AuthorityDTO.parseAuthorityDto(authority);
    }

    public List <AuthorityDTO> getAuthority () {
        return authorityRepository.findAll().stream().map(AuthorityDTO::parseAuthorityDto).toList();
    }
}
