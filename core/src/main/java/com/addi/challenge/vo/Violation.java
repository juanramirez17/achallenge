package com.addi.challenge.vo;

import lombok.Value;

@Value
public class Violation {

    String className;
    String fieldName;
    String fieldValue;
    String message;
}
