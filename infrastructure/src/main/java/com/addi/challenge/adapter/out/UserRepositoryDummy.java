package com.addi.challenge.adapter.out;

import com.addi.challenge.domain.User;
import com.addi.challenge.domain.UserStateEnum;
import com.addi.challenge.entity.Person;
import com.addi.challenge.port.out.UserRepository;
import com.addi.challenge.vo.IdTypeEnum;
import com.addi.challenge.vo.Identification;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class UserRepositoryDummy implements UserRepository {

    Map<UUID, User> repo = new HashMap<UUID, User>();

    public UserRepositoryDummy() {
        createDummyUsers();
    }

    private void createDummyUsers() {
        IntStream.range(0, 10)
            .forEach(i -> {
                    var id = UUID.randomUUID();
                    repo.put(id, createUser(i, id));
                }
            );
    }

    private User createUser(int i, UUID id) {
        var agentId = UUID.randomUUID();
        return new User(id, createPerson(i), UserStateEnum.LEAD, agentId);
    }

    private Person createPerson(int i) {
        return Person.builder()
            .birthDate("1991-05-31")
            .email("email" + i + "@gl.com")
            .firstName("name " + i)
            .lastName("last" + i)
            .identification(createIdentification(i))
            .build();
    }

    private Identification createIdentification(int i) {
        return Identification.builder()
            .idNumber("10000" + i)
            .type(IdTypeEnum.CC)
            .build();
    }

    @Override
    public Mono<User> getOne(UUID id) {
        return Mono.just(repo.get(id));
    }

    @Override
    public Mono<Void> updateState(User user) {
        repo.put(user.getId(), user);
        return Mono.empty();
    }

    @Override
    public Mono<List<User>> getAll() {
        return Mono.just(
            new ArrayList<>(repo.values())
        );
    }
}
