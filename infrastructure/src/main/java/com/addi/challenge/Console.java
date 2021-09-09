package com.addi.challenge;

import com.addi.challenge.command.QualifyLeadCommand;
import com.addi.challenge.port.in.UserLeadQualifyApi;
import com.addi.challenge.port.out.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class Console implements ApplicationRunner {

    private final UserRepository userRepository;
    private final UserLeadQualifyApi userLeadQualifyApi;

    public Console(UserRepository userRepository, UserLeadQualifyApi userLeadQualifyApi) {
        this.userRepository = userRepository;
        this.userLeadQualifyApi = userLeadQualifyApi;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("LIST OF LEAD");
        userRepository.getAll()
            .flatMapMany(Flux::fromIterable)
            .doOnNext(u -> System.out.println("id: " + u.getId() + " name: " + u.getPerson().getFirstName() +
                " state :" + u.getState().toString()))
            .map(u -> userLeadQualifyApi.execute(QualifyLeadCommand.builder()
                .idInOurRepo(u.getId().toString()) // TODO this value should be the id identification number (CC)
                .build())
                .subscribe())
            .subscribe();

        Thread.sleep(500);
        userRepository.getAll()
            .doOnNext(users -> {
                users.stream().forEach(u ->
                    System.out.println("id: " + u.getId() + " name: " + u.getPerson().getFirstName() +
                        " state :" + u.getState().toString()));
            })
            .subscribe();
    }
}
