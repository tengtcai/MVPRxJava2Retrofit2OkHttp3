package com.android.aiziran.baicaoyuan.base;

/**
 * Created by fg on 2017/7/25.
 * 解析实体基类
 */

public class BaseResult<T> {
    private static int SUCCESS_CODE=000000;//成功的code
    private int code;
    private String message;
    private T results;
    private boolean error;

    public boolean isSuccess(){
        return getCode()==SUCCESS_CODE;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
