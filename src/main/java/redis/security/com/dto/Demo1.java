package redis.security.com.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "demo")
public interface Demo1 {

    @PostMapping("/auth")
    public String generateAuthToken (@RequestBody DemoCredentials credentials);

    @GetMapping("/test/get-test-message")
    public String getMessage (@RequestHeader("Authorization") String accessToken);
}
