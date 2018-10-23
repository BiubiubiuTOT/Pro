package com.ljkj.qxn.wisdomsitepro.data;

import android.os.Bundle;

/**
 * 类描述：异步加载结果
 * 创建人：lxx
 * 创建时间：2018/4/17 15:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AsyncResult<T> {
    private int result = 0;
    private T data;
    private Bundle bundle = new Bundle();

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getString(String key, String defaultValue) {
        return bundle.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        bundle.putString(key, value);
    }

    public int getInt(String key, int defaultValue) {
        return bundle.getInt(key, defaultValue);
    }

    public void putInt(String key, int value) {
        bundle.putInt(key, value);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
