package redis.security.com.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.security.com.dto.ClientDto;
import redis.security.com.service.MasterService;

import java.util.List;

@RestController
@RequestMapping("/master")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService service;

    @GetMapping(value = "/{id}/get-clients")
    public List <ClientDto> getClientsOfMaster (@PathVariable("id") Long id) {
        return service.getAllClientsByMasterId(id);
    }

    @GetMapping(value = "/get-all-clients")
    public List <ClientDto> getAllClients () {
        return service.getAllClients();
    }
}
