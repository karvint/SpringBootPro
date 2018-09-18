package com.ty.show.exception;

import com.ty.show.enums.ReturnEnums;

public class LoginException extends BaseException {
    public LoginException(ReturnEnums returnEnums) {
        super(returnEnums);
    }
}
