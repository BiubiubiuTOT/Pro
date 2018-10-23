package cdsp.android.http;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：http请求回调抽象类
 * 修订历史：
 * ================================================
 */

public abstract class AbstractCallback extends AbsCallback<String> {

    private StringConvert convert = new StringConvert();

    /**
     * 请求网络开始前，UI线程
     */
    @Override
    public final void onStart(Request<String, ? extends Request> request) {
        onStart();
    }

    /**
     * 请求网络开始前，UI线程
     */
    public void onStart() {
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    @Override
    public final void onSuccess(com.lzy.okgo.model.Response<String> response) {

//        Logger.i(HttpUtils.class.getSimpleName(), "响应参数列表 ：" + response.body());
        onSuccess(response.body());
    }

    /**
     * 缓存成功的回调,UI线程
     */
    @Override
    public final void onCacheSuccess(com.lzy.okgo.model.Response<String> response) {
    }

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    @Override
    public final void onError(com.lzy.okgo.model.Response<String> response) {


        Throwable throwable = response.getException();

        //网络连接错误
        if (throwable instanceof UnknownHostException || throwable instanceof ConnectException || throwable instanceof NoRouteToHostException) {
            onError(resolveErrcode(throwable), "网络链接失败");
            throwable.printStackTrace();
        }

        //网络请求超时
        else if (throwable instanceof SocketTimeoutException) {
            onError(resolveErrcode(throwable), "网络请求超时");
            throwable.printStackTrace();
        } else if (throwable instanceof HttpException) {
            //服务端响应出错

            //404
            if (response.code() == 404) {
                onError(resolveErrcode(throwable), "请求地址找不到");
            }

            //>=500
            else if (response.code() >= 500) {
                onError(resolveErrcode(throwable), "服务端响应出错");
            }
            throwable.printStackTrace();
        }

        //存储权限
        else if (throwable instanceof StorageException) {
            onError(resolveErrcode(throwable), "缓存权限失败");
            throwable.printStackTrace();
        }

        //其他错误
        else if (throwable instanceof IllegalStateException) {
            throwable.printStackTrace();
        }
    }


    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = this.convert.convertResponse(response);
        response.close();
        return s;
    }

    /**
     * 通过解析错误码
     *
     * @param exception
     * @return
     */
    protected int resolveErrcode(Throwable exception) {
        return ResponseBody.DEFAULT_ERROR_CODE;
    }

    /**
     * 请求网络结束后，UI线程
     */
    @Override
    public void onFinish() {
    }

    /**
     * 上传过程中的进度回调，get请求不回调，UI线程
     */
    @Override
    public final void uploadProgress(Progress progress) {
    }

    /**
     * 下载过程中的进度回调，UI线程
     */
    @Override
    public final void downloadProgress(Progress progress) {

    }

    /**
     * 网络请求成功时回调
     *
     * @param result 返回结果
     */
    public abstract void onSuccess(String result);


    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     *
     * @param errcode 错误码
     * @param errmsg  错误信息
     */
    protected abstract void onError(int errcode, String errmsg);
}
