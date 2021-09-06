package com.addi.challenge.domain;

import com.addi.challenge.port.out.UserRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> getOne(UUID id) {
        return userRepository.getOne(id);
    }

    @Override
    public Mono<Void> updateUserState(UUID id, UserStateEnum state) {
        System.out.println("User Updated");
        return userRepository.getOne(id)
            .flatMap(user -> userRepository.updateState(user.withState(state)));
    }

    @Override
    public Mono<Integer> scoreUser(Boolean isRegistryValid, Boolean hasJudicialRecords) {
        return Mono.just(ThreadLocalRandom.current().nextInt(0, 100));
    }
}
