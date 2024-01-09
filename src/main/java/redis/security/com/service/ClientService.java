package redis.security.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.security.com.dao.Client;
import redis.security.com.dao.Master;
import redis.security.com.dto.ClientDto;
import redis.security.com.dto.MasterDto;
import redis.security.com.repository.ClientRepository;
import redis.security.com.repository.MasterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final MasterRepository masterRepository;

    public List <MasterDto> getAllMaster () {
        return masterRepository.findAll().stream().map(MasterDto::parseMaster).toList();
    }

    @Cacheable(cacheNames = "masterId", key = "#masterId")
    public ClientDto addMaster (Long clientId, Long masterId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Master master = masterRepository.findById(masterId).orElse(null);
        if (client != null && master != null) {
            client.setMaster(master);
            clientRepository.save(client);
        }
        return ClientDto.parseClient(client);
    }

    @Cacheable(value = "client", key = "#clientId")
    public ClientDto removeMaster (Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            client.setMaster(null);
            clientRepository.save(client);
        }
        return ClientDto.parseClient(client);
    }
}
