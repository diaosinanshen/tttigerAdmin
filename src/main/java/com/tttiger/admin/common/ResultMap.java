package com.tttiger.admin.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author 秦浩桐
 * @version 1.0
 */
@Data
public class ResultMap<T> {

    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    /**
     * 响应状态码
     */
    private Integer status;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 将ResultMap置为成功状态 使用默认提示信息
     *
     * @return this
     */
    public ResultMap<T> success() {
        this.setStatus(ResultMap.SUCCESS);
        this.setMessage("操作成功");
        return this;
    }

    /**
     * 将ResultMap置为成功状态 使用默认提示信息
     *
     * @param message 成功提示信息
     * @return this
     */
    public ResultMap<T> success(String message) {
        this.setStatus(ResultMap.SUCCESS);
        this.setMessage(message);
        return this;
    }


    /**
     * 将ResultMap置为错误状态 使用默认提示信息
     *
     * @return this
     */
    public ResultMap<T> fail() {
        this.setStatus(ResultMap.FAILED);
        this.setMessage("未知错误");
        return this;
    }

    /**
     * 将ResultMap置为错误状态
     *
     * @param message 错误提示信息
     * @return this
     */
    public ResultMap<T> fail(String message) {
        this.setStatus(ResultMap.FAILED);
        this.setMessage(message);
        return this;
    }

    /**
     * 设置响应消息
     *
     * @param message 响应消息
     * @return 封装返回结果
     */
    public ResultMap<T> message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 断言条件返回成功或失败
     *
     * @param b 断言
     * @return 成功或失败实体
     */
    public ResultMap<T> predicate(boolean b) {
        return b ? this.success() : this.fail();
    }

    /**
     * 断言条件返回成功或失败
     *
     * @param b           断言
     * @param failMessage 断言失败提示信息
     * @return 成功或失败实体
     */
    public ResultMap<T> predictFail(boolean b, String failMessage) {
        return b ? this.success() : this.fail().message(failMessage);
    }

    /**
     * 断言条件返回成功或失败
     *
     * @param b           断言
     * @param successMessage 断言成功提示信息
     * @return 成功或失败实体
     */
    public ResultMap<T> predictSuccess(boolean b, String successMessage) {
        return b ? this.success(successMessage) : this.fail();
    }

    /**
     * 断言条件返回成功或失败
     *
     * @param b              断言
     * @return 成功或失败实体
     */
    public ResultMap<T> predict(boolean b) {
        return b ? this.success() : this.fail();
    }

    /**
     * 断言条件返回成功或失败
     *
     * @param b              断言
     * @param successMessage 断言成功提示信息
     * @param failMessage    断言失败提示信息
     * @return 成功或失败实体
     */
    public ResultMap<T> predict(boolean b, String successMessage, String failMessage) {
        return b ? this.success(successMessage) : this.fail().message(failMessage);
    }

    /**
     * 生成带泛型的响应实体
     *
     * @param data 响应数据
     * @return 封装返回结果
     */
    public static <T> ResultMap<T> data(T data) {
        ResultMap<T> resultMap = new ResultMap<>();
        resultMap.data = data;
        return resultMap;
    }

    public static ResultMap<Object> data() {
        return new ResultMap<Object>();
    }

    /**
     * 响应是否成功
     *
     * @return 是否成功
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResultMap.SUCCESS;
    }

    /**
     * 响应是否失败
     *
     * @return 是否失败
     */
    @JsonIgnore
    public boolean isFail() {
        return this.status == ResultMap.FAILED;
    }

    /**
     * 响应数据是否为空
     *
     * @return 是否为空
     */
    @JsonIgnore
    public boolean isEmpty() {
        return this.data == null;
    }


    private ResultMap() {
    }


}
