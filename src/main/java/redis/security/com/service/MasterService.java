package redis.security.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.security.com.dao.Master;
import redis.security.com.dto.ClientDto;
import redis.security.com.repository.ClientRepository;
import redis.security.com.repository.MasterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MasterService {

    private final MasterRepository masterRepository;
    private final ClientRepository clientRepository;

    public List <ClientDto> getAllClientsByMasterId (Long masterId) {
        Master master = masterRepository.findById(masterId).orElse(null);
        return clientRepository.findClientByMaster(master).stream().map(ClientDto::parseClient).toList();
    }

    public List <ClientDto> getAllClients () {
        return clientRepository.findAll().stream().map(ClientDto::parseClient).toList();
    }
}
