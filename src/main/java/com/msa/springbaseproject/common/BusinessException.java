package com.msa.springbaseproject.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -2734954395640676984L;
    private int code;
    private String message;
    private boolean showStackTrace = false;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, boolean showStackTrace, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
        this.showStackTrace = showStackTrace;
    }
}
