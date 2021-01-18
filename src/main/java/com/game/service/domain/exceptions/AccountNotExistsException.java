package com.game.service.domain.exceptions;

import com.game.service.domain.exceptions.info.BaseException;
import com.game.service.domain.exceptions.info.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountNotExistsException extends BaseException {
    public AccountNotExistsException() {
        super(ErrorCode.ACCOUNT_NOT_EXISTS, "Account not found");
    }
}
