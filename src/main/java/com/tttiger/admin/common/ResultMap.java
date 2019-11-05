package com.tttiger.admin.common;

import lombok.Data;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/28 20:24
 * @Description
 */
@Data
public class ResultMap {

    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    private Integer status;
    private String message;
    private Object data;

    public static ResultMap ok() {
        ResultMap resultMap = new ResultMap();
        resultMap.setStatus(ResultMap.SUCCESS);
        return resultMap;
    }

    public static ResultMap fail() {
        ResultMap resultMap = new ResultMap();
        resultMap.setStatus(ResultMap.FAILED);
        resultMap.setMessage("未知错误");
        return resultMap;
    }

    public static ResultMap predicate(boolean b) {
        return b ? ResultMap.ok() : ResultMap.fail();
    }

    public static ResultMap predicate(boolean b, String failMessage) {
        return b ? ResultMap.ok() : ResultMap.fail().message(failMessage);
    }

    /**
     * 设置响应数据
     *
     * @param data 响应数据
     * @return 封装返回结果
     */
    public ResultMap data(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 响应是否成功
     *
     * @return 是否成功
     */
    public boolean isOk() {
        return this.status == ResultMap.SUCCESS;
    }

    /**
     * 响应是否失败
     *
     * @return 是否失败
     */
    public boolean isFail() {
        return this.status == ResultMap.FAILED;
    }

    /**
     * 响应数据是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return this.data == null;
    }

    /**
     * 设置响应消息
     * @param message 响应消息
     * @return 封装返回结果
     */
    public ResultMap message(String message) {
        this.message = message;
        return this;
    }

}
