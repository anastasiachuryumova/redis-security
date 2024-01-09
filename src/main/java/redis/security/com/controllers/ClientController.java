package redis.security.com.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.security.com.dto.ClientDto;
import redis.security.com.dto.MasterDto;
import redis.security.com.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping(value = "/get-all-masters")
    public List <MasterDto> getTestData () {
        return service.getAllMaster();
    }

    @PutMapping(value = "/{client_id}/{master_id}/add-master")
    public ClientDto addMaster (@PathVariable("client_id") Long clientId, @PathVariable("master_id") Long masterId) {
        return service.addMaster(clientId, masterId);
    }

    @PutMapping(value = "/{client_id}/remove-master")
    public ClientDto removeMaster (@PathVariable("client_id") Long clientId) {
        return service.removeMaster(clientId);
    }
}
