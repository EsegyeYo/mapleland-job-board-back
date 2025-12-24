package com.ma_recruit.global.exception;

import lombok.Getter;

@Getter
public class ProfileException extends RuntimeException {
    public ProfileException(String msg) {
        super(msg);
    }
}
