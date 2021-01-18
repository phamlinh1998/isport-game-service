package com.game.service.domain.exceptions;

import com.game.service.domain.exceptions.info.BaseException;
import com.game.service.domain.exceptions.info.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongAccountOrPasswordException extends BaseException {
    public WrongAccountOrPasswordException() {
        super(ErrorCode.WRONG_ACCOUNT_OR_PASSWORD, "Wrong username or password");
    }
}
