package br.com.fatec.oauth.service;

import br.com.fatec.oauth.feignclients.UserFeignClient;
import br.com.fatec.oauth.model.User;
import br.com.fatec.oauth.model.SystemUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public User findByEmail(String email) throws RuntimeException {
        return userFeignClient.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userFeignClient.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
        return new SystemUser(usuario, usuario.getAuthorities());
    }
}
