package com.addi.challenge.domain;

import com.addi.challenge.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@With
@AllArgsConstructor
public class User {
    UUID id;
    Person person;
    UserStateEnum state;
    UUID agentId;
}
