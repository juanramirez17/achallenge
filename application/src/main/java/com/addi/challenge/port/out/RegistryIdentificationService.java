package com.addi.challenge.port.out;

import com.addi.challenge.domain.User;
import reactor.core.publisher.Mono;

public interface RegistryIdentificationService {

    Mono<User> getRegistry(User user);
}
