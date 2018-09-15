package com.purchasing.reactive.handler;

import com.purchasing.model.User;
import com.purchasing.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhangwei
 * @createTime 2018/9/14
 */
@Component
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> save(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<Boolean> booleanMono = userMono.map(repository::save);
        return ServerResponse.ok().body(booleanMono,boolean.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        //下来查询
      return  null;
    }
}
