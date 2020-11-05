package com.lhzn.soft.framework.aspectj.lang;

/**
 * 通用校验返回结果类
 */
public class ValidateResult {
    private boolean isValid = true;
    private String message;
    public ValidateResult() {
    }
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
