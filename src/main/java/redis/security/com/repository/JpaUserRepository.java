package redis.security.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dao.User;

@Repository("jpaUserRepository")
public interface JpaUserRepository extends JpaRepository <User, Integer> {

    User findUserByUsername (String username);
}
