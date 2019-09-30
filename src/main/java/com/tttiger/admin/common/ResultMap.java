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

    public static ResultMap ok(){
        ResultMap resultMap = new ResultMap();
        resultMap.setStatus(ResultMap.SUCCESS);
        return resultMap;
    }

    public static ResultMap fail(){
        ResultMap resultMap = new ResultMap();
        resultMap.setStatus(ResultMap.FAILED);
        return resultMap;
    }

    public ResultMap data(Object data){
        this.data = data;
        return this;
    }

    public ResultMap message(String message){
        this.message = message;
        return this;
    }
}
