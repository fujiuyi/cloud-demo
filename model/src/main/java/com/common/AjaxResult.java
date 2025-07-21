package com.common;

import lombok.Data;

@Data
public class AjaxResult {
    private Integer code;
    private String msg;
    private Object data;

    public static AjaxResult success(Object data) {
        AjaxResult result = success();
        result.setData(data);
        return result;
    }

    public static AjaxResult success() {
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static AjaxResult success(Integer code, Object data, String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static AjaxResult error(String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }
}
