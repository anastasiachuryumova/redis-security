package redis.security.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redis.security.com.dao.Client;
import redis.security.com.dao.Master;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findClientByMaster (Master master);
}
