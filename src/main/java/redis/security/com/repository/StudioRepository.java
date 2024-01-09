package redis.security.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dao.Studio;
import redis.security.com.dao.StudioId;

@Repository
public interface StudioRepository extends JpaRepository <Studio, StudioId> {
}
