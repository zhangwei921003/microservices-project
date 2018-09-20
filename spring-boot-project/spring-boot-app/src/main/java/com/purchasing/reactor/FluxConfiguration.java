package com.purchasing.reactor;

import com.purchasing.model.User;
import com.purchasing.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author zhangwei
 * @createTime 2018/9/18
 */
@Configuration
public class FluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> list(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.path("/flux/user/list"),
                request -> ServerResponse.ok().body(Flux.fromIterable(userRepository.selectAll()),User.class));
    }

    @Bean
    public RouterFunction<ServerResponse> save(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.path("/flux/user/save"),
                (request) -> {
                    Optional<String> name = request.queryParam("name");
                    User user = new User();
                    user.setName(name.get());
                    userRepository.save(user);
                    Mono<User> userMono =  Mono.just(user);
                    return ServerResponse.ok().body(userMono,User.class);
                });
    }
}
