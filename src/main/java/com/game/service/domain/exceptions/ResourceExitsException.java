package com.game.service.domain.exceptions;


import com.game.service.domain.exceptions.info.BaseException;
import com.game.service.domain.exceptions.info.ErrorCode;

public class ResourceExitsException extends BaseException {
    public ResourceExitsException() {
        super(ErrorCode.RESOURCE_FOUND, "Resource Found");
    }

}
