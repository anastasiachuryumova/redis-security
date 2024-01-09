package redis.security.com.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dto.AccessToken;

@Repository
public interface DemoRedisRepository extends KeyValueRepository <AccessToken, String> {
}
