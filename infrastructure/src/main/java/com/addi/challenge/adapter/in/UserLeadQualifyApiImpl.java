package com.addi.challenge.adapter.in;

import com.addi.challenge.aggregate.UserAggregate;
import com.addi.challenge.command.QualifyLeadCommand;
import com.addi.challenge.port.in.UserLeadQualifyApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserLeadQualifyApiImpl implements UserLeadQualifyApi {

    private final UserAggregate userAggregate;

    public UserLeadQualifyApiImpl(UserAggregate userAggregate) {
        this.userAggregate = userAggregate;
    }

    @Override
    public Mono<Void> execute(QualifyLeadCommand cmd) {
        return userAggregate.qualifyUser(cmd);
    }
}
