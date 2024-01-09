package redis.security.com.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dto.UserDto;

@Repository("redisUserRepository")
public interface RedisUserRepository extends KeyValueRepository <UserDto, Integer> {
}
