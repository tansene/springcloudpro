package com.tansene.exception;

/**
 * 身份认证异常
 * @author tansene
 * @since 2020/11/20
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
