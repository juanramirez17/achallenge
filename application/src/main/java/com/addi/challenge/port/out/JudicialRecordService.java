package com.addi.challenge.port.out;

import com.addi.challenge.domain.User;
import reactor.core.publisher.Mono;

public interface JudicialRecordService {

    Mono<Boolean> hasJudicialRecords(User user);
}
