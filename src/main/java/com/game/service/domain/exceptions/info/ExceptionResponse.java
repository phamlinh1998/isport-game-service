package com.game.service.domain.exceptions.info;

import lombok.Data;

@Data
public class ExceptionResponse {
    private int code;
    private String message;

    public static ExceptionResponse createFrom(BaseException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        response.setCode(e.getCode());
        return response;
    }
}
