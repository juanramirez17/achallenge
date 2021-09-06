package com.addi.challenge.port.in;

import com.addi.challenge.command.QualifyLeadCommand;
import reactor.core.publisher.Mono;

public interface UserLeadQualifyApi {

    Mono<Void> execute(QualifyLeadCommand cmd);

}
