package com.example.spring_boot_rest1.config;

import com.example.spring_boot_rest1.repository.UserRepository;
import com.example.spring_boot_rest1.service.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class JConfig {
    @Bean
    public UserRepository postRepository() {
        return new UserRepository();
    }
    @Bean
    public AuthorizationService postService(UserRepository repository) {
        return new AuthorizationService(repository);
    }

    @Bean
    public DispatcherServlet dispatcherServlet () {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
//    @Bean
//    public AuthorizationController authorizationController(AuthorizationService service) {
//        return new AuthorizationController(service);
//    }
}
