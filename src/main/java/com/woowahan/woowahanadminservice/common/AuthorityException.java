package com.woowahan.woowahanadminservice.common;

public class AuthorityException extends RuntimeException {
    public AuthorityException() {
    }

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityException(Throwable cause) {
        super(cause);
    }

    protected AuthorityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
