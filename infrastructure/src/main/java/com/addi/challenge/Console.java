package com.addi.challenge;

import com.addi.challenge.command.QualifyLeadCommand;
import com.addi.challenge.port.in.UserLeadQualifyApi;
import com.addi.challenge.port.out.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
            .block()
            .stream()
            .forEach(u -> {
                    System.out.println("id: " + u.getId() + " name: " + u.getPerson().getFirstName() +
                        " state :" + u.getState().toString());
                    userLeadQualifyApi.execute(QualifyLeadCommand.builder()
                        .idInOurRepo(u.getId().toString()) // TODO this value should be the id identification number (CC)
                        .build()).block();
                }
            );

        System.out.println("LIST OF USER SOME NOW ARE PROSPECTS");
        userRepository.getAll()
            .block()
            .stream()
            .forEach(u -> {
                    System.out.println("id: " + u.getId() + " name: " + u.getPerson().getFirstName() +
                        " state :" + u.getState().toString());
                }
            );
    }
}
