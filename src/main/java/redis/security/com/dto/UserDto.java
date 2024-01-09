package redis.security.com.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import redis.security.com.dao.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash(value = "user", timeToLive = 60L)
public class UserDto {

    @Id
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private String email;
    private String lastActivity;
    private Integer priority;
    private Boolean isDeleted;

    public static UserDto parseUser (User user) {
        UserDto userDto = new UserDto();
        if (user != null) {
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setEnabled(user.getEnabled());
            userDto.setEmail(user.getEmail());
            userDto.setLastActivity(user.getLastActivity());
            userDto.setPriority(user.getPriority());
            userDto.setIsDeleted(user.getIsDeleted());
        }
        return userDto;
    }
    public static User parseUserDto (UserDto userDto) {
        User user = new User();
        if (userDto != null) {
            user.setId(userDto.getId());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setEnabled(userDto.getEnabled());
            user.setEmail(userDto.getEmail());
            user.setLastActivity(userDto.getLastActivity());
            user.setPriority(userDto.getPriority());
            user.setIsDeleted(userDto.getIsDeleted());
        }
        return user;
    }
}
