package com.game.service.domain.exceptions;


import com.game.service.domain.exceptions.info.BaseException;
import com.game.service.domain.exceptions.info.ErrorCode;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Resource Not Found");
    }

}
