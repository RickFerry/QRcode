package br.com.fatec.oauth.feignclients;

import br.com.fatec.oauth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Component
@FeignClient(name = "ms-user", path = "/users")
public interface UserFeignClient {

    @GetMapping("/search")
    Optional<User> findByEmail(@RequestParam String email);
}