package com.sherlock.example.core.rest;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Sherlock
 */
@Data
public class ResultEntity<T> implements Serializable {

    private static final long serialVersionUID = -8276264968757808344L;

    private static final int SUCCESS = 0;

    public static final int FAIL = -1;

    private String msg = "操作成功";
    private int code = SUCCESS;
    private T data;

    private ResultEntity() {
        super();
    }

    private ResultEntity(String msg, T data, int code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> ResultEntity<T> success() {
        return success("操作成功");
    }

    public static <T> ResultEntity<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> ResultEntity<T> successData(T data) {
        return success("操作成功", data);
    }

    public static <T> ResultEntity<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> ResultEntity<T> success(String msg, T data) {
        return new ResultEntity<>(msg, data, SUCCESS);
    }

    public static <T> ResultEntity<T> error(String msg) {
        ResultEntity<T> resultBean = new ResultEntity<>();
        resultBean.setCode(FAIL);
        resultBean.setMsg(msg);
        return resultBean;
    }


}
