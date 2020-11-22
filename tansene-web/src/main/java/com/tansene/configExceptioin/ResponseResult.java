package com.tansene.configExceptioin;


import org.springframework.http.HttpStatus;
/**
 * 返回统一异常类
 */
public class ResponseResult {
    public ResponseResult() {
    }
    public static <T> RespModel<T> notFound(String message){
        RespModel respModel = new RespModel();
        respModel.setStatus(HttpStatus.NOT_FOUND.value());
        respModel.setCode(HttpStatus.NOT_FOUND.getReasonPhrase());
        respModel.setMessage(message);
        return respModel;
    }
}
