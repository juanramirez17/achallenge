package com.addi.challenge.aggregate;

import com.addi.challenge.command.QualifyLeadCommand;
import com.addi.challenge.domain.User;
import com.addi.challenge.domain.UserService;
import com.addi.challenge.domain.UserStateEnum;
import com.addi.challenge.port.out.JudicialRecordService;
import com.addi.challenge.port.out.RegistryIdentificationService;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UserAggregate {

    final UserService userService;
    final JudicialRecordService judicialRecordService;
    final RegistryIdentificationService registryIdentificationService;

    public UserAggregate(UserService userService,
                         JudicialRecordService judicialRecordService,
                         RegistryIdentificationService registryIdentificationService) {
        this.userService = userService;
        this.judicialRecordService = judicialRecordService;
        this.registryIdentificationService = registryIdentificationService;
    }

    public Mono<Void> qualifyUser(QualifyLeadCommand cmd) {
        final UUID id = UUID.fromString(cmd.getIdInOurRepo() /* TODO this id should be the identification number*/);
        return userService.getOne(id)
            .flatMap(user -> qualify(user))
            .flatMap(score -> {
                if (score > 60)
                    return userService.updateUserState(id, UserStateEnum.PROSPECT);
                else return Mono.empty();
            });

    }

    private Mono<Integer> qualify(User user) {
        return validRegistry(user)
            .zipWith(judicialRecordService.hasJudicialRecords(user))
            .flatMap(t -> userService.scoreUser(t.getT1(), t.getT2()));
    }

    private Mono<Boolean> validRegistry(User user) {
        return registryIdentificationService.getRegistry(user)
            .map(userReturned -> userReturned.equals(user));
    }

}
