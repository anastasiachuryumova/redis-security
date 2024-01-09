package redis.security.com.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.security.com.dao.User;
import redis.security.com.dto.AuthorityDTO;
import redis.security.com.dto.UserDto;
import redis.security.com.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @GetMapping(value = "/get-all-users")
    public List <UserDto> getAllUsers () {
        return service.getAllUsers();
    }

    @GetMapping(value = "/get-user/{id}")
    public UserDto getUserById (@PathVariable(value = "id") Integer id) {
        return service.getUserById(id);
    }

    @PostMapping(value = "/update-user")
    public User updateUser (@RequestBody UserDto user) {
        return service.updateUser(user);
    }

    @PostMapping("/soft-delete-user/{id}")
    public UserDto softDeleteUser (@PathVariable("id") Integer id) {
        return service.softDeleteUser(id);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public void deleteUser (@PathVariable("id") Integer id) {
        service.deleteUser(id);
    }

    @PostMapping(value = "/add_authority")
    public AuthorityDTO addAuthority (@RequestBody AuthorityDTO authorityDTO) {
        return service.addAuthority(authorityDTO);
    }

    @GetMapping(value = "/get_authority")
    public List <AuthorityDTO> getAuthority () {
        return service.getAuthority();
    }
}
