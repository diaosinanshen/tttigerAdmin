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

    public ResultMap data(Object data) {
        this.data = data;
        return this;
    }

    public ResultMap message(String message) {
        this.message = message;
        return this;
    }

    public static ResultMap notNull(Object obj) {
        if (obj == null) {
            return ResultMap.fail().message("未找到匹配数据");
        }
        return ResultMap.ok().data(obj);
    }

    public static ResultMap notNull(Object obj, String ifNullMessage) {
        if (obj == null) {
            return ResultMap.fail().message(ifNullMessage);
        }
        return ResultMap.ok().data(obj);
    }
}
