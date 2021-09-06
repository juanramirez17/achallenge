package com.addi.challenge.adapter.out;

import com.addi.challenge.domain.User;
import com.addi.challenge.port.out.RegistryIdentificationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RegistryIdentificationServiceImpl implements RegistryIdentificationService {

    @Override
    public Mono<User> getRegistry(User user) {

        // TODO this call should be an http with respective request and response
        return Mono.delay(Duration.ofMillis(ThreadLocalRandom.current().nextInt(70, 100))).map(l ->
            new Random().nextBoolean() ? // simulating some bad registers
                user.withPerson(user.getPerson().withFirstName("fake")) :
                user
        );
    }
}
