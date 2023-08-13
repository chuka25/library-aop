package com.chuka.springaop.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CustomStatus {

    SUCCESS(0, "Success"),
    NOT_FOUND(1, "Not found"),
    EXCEPTION(2, "Exception"),

    ALREADY_EXIST(3, "Already exists");

    private final int code;
    private final String message;
}
