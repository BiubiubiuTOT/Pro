package cdsp.android.http;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：http请求相应结构体
 * 修订历史：
 * ================================================
 */

public class ResponseBody implements Serializable {

    public static final int DEFAULT_SUCCESS_CODE = 0;
    public static final int DEFAULT_ERROR_CODE = 1;


    /**
     * 错误编码 ， 0：成功， 其他为失败（参考错误编码表）
     */
    private int errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 返回结果是否成功
     *
     * @return 返回 true：成功， 否则失败
     * @since     1.0.0
     */
    public boolean isSuccess() {
        return DEFAULT_SUCCESS_CODE == errcode;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
