package com.lhzn.soft.framework.exception;

public class CustomException extends RuntimeException {
    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

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
