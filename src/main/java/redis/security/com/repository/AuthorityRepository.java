package redis.security.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dao.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
