package com.game.service.domain.exceptions;

import com.game.service.domain.exceptions.info.BaseException;
import com.game.service.domain.exceptions.info.ErrorCode;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED, "Account not found");
    }
}
