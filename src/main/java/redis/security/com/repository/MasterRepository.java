package redis.security.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dao.Master;

@Repository
public interface MasterRepository extends JpaRepository <Master, Long> {
}
