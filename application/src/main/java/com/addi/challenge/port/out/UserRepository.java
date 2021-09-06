package com.addi.challenge.port.out;

import com.addi.challenge.domain.User;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    Mono<User> getOne(UUID id);

    Mono<Void> updateState(User user);

    Mono<List<User>> getAll();

}
