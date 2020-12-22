package com.lhzn.soft.framework.exception;

/**
 * 自定义异常
 * @author qishuai
 */
public class CustomException extends RuntimeException {
    /**
     *     异常对应的返回码
     */
    private String retCd ;
    /**
     * 异常对应的描述信息
     */
    private String msgDes;

    public CustomException(String msgDes) {
        super(msgDes);
    }


    public String getRetCd() {
        return retCd;
    }

    public void setRetCd(String retCd) {
        this.retCd = retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }
}
