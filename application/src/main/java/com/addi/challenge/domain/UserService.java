package com.addi.challenge.domain;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    Mono<User> getOne(UUID id);

    Mono<Void> updateUserState(UUID id, UserStateEnum stateEnum);

    Mono<Integer> scoreUser(Boolean isRegistryValid, Boolean hasJudicialRecords);

}

