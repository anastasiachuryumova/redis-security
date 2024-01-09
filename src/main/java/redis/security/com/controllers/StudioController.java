package redis.security.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.security.com.dao.Studio;
import redis.security.com.dao.StudioId;
import redis.security.com.dto.StudioDto;
import redis.security.com.service.StudioService;

import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    StudioService service;

    @GetMapping(value = "/get-all")
    public List <StudioDto> getTestData () {
        return service.getAll();
    }

    @PostMapping(value = "/add-studio")
    public Studio addStudio (@RequestBody StudioId id) {
        return service.addStudio(id);
    }

    @PutMapping(value = "/{master_id}/add-master")
    public StudioDto addMaster (@RequestBody StudioId id, @PathVariable("master_id") Long masterId) {
        return service.addMaster(id, masterId);
    }
}
