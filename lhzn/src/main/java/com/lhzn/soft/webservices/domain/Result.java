package com.lhzn.soft.webservices.domain;

import com.lhzn.soft.utils.DateUtils;

import java.io.Serializable;

/**
 * 返回值信息
 *
 * @author gstarqs
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回时间
     */
    public static final String MESSAGE_TIME = DateUtils.dateTimeNow ("yyyy-MM-dd HH:mm:ss");

    /**
     * 状态
     */
    public String recStatus;

    /**
     * 信息
     */
    public String recMessage;

    public void setRecStatus ( String recStatus ) {
        this.recStatus = recStatus;
    }

    public void setRecMessage ( String recMessage ) {
        this.recMessage = recMessage;
    }

    public static Result success () {
        Result result = new Result ();
        result.setRecStatus ("true");
        result.setRecMessage ("上传成功");
        return result;
    }

    public static Result err ( String recMessage ) {
        Result result = new Result ();
        result.setRecStatus ("false");
        result.setRecMessage (recMessage);
        return result;
    }

    @Override
    public String toString () {
        return "{\n" +
                "\"messageTime\":\"" + MESSAGE_TIME + "\",\n" +
                "\"recStatus\":\"" + recStatus + "\",\n" +
                "\"recMessage\":\"" + recMessage + "\"\n" +
                "}\n";
    }
}
