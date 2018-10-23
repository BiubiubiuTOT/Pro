package cdsp.android.http;

/**
 * 类描述：JSON 解析实体:对应 JsonAbstractCallback 使用
 * 创建人：mhl
 * 创建时间：2017/9/16  上午 10:38
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ResponseData<T> {


    public static final int DEFAULT_SUCCESS_CODE = 0;
    public static final int DEFAULT_ERROR_CODE = 1;

    /**
     * 错误编码 ， 0：成功， 其他为失败（参考错误编码表:10020 token失效）
     */
    private int errcode = DEFAULT_ERROR_CODE;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 返回结果
     */
    private T result;

    /**
     * 返回结果是否成功
     *
     * @return 返回 true：成功， 否则失败
     * @since 1.0.0
     */
    public boolean isSuccess() {

        //token 失效,退出登录转到登录界面
        if (errcode == 10020) {
//            BaseApplication.getApplication().tokenExpire();
            return false;
        }
        return DEFAULT_SUCCESS_CODE == errcode || 200 == errcode;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", result=" + result +
                '}';
    }
}
