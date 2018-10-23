package cdsp.android.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cdsp.android.base.BaseApplication;
import cdsp.android.constant.Consts;
import cdsp.android.logging.Logger;
import cdsp.android.util.DeviceUtils;
import cdsp.android.util.JSONUtils;
import cdsp.android.util.SpUtils;
import okhttp3.OkHttpClient;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：http请求工具类
 * 修订历史：
 * ================================================
 */

public class HttpUtils {

    private static BaseApplication mContext = null;

    private HttpUtils() {
    }

    public static void init(BaseApplication app) {
        mContext = app;

        //初始化OkGo
        initOkGo();
    }

    /**
     * 文件上传
     *
     * @param url
     * @param params
     * @param filePaths
     * @param tag
     * @param callback
     */
    public static void uploadFiles(final String url, RequestParams params, List<String> filePaths, Object tag, AbstractCallback callback) {

        HttpParams httpParams = new HttpParams();

        // 添加协议参数
        addProtocolParams(httpParams);

        // 添加业务参数
        addBizParams(httpParams, params);

        //文件添加
        for (int i = 0; i < filePaths.size(); i++) {
            httpParams.put("file" + i, new File(filePaths.get(i)));
        }
        String jsonString = JSONUtils.toJSONString(httpParams.urlParamsMap);
        Logger.i(HttpUtils.class.getSimpleName(), "请求参数列表 ：" + jsonString);
        OkGo.<String>post(url).tag(tag).params(httpParams).execute(callback);

    }


    /**
     * get 请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param tag      请求标签
     * @param callback 请求回调
     */
    public static void get(final String url, RequestParams params, Object tag, AbstractCallback callback) {

        HttpParams httpParams = new HttpParams();

        // 添加协议参数
        addProtocolParams(httpParams);

        // 添加业务参数
        addBizParams(httpParams, params);

        String jsonString = JSONUtils.toJSONString(httpParams.urlParamsMap);
        Logger.i(HttpUtils.class.getSimpleName(), "请求参数列表 ：" + jsonString);
        OkGo.<String>get(url).tag(tag).params(httpParams).execute(callback);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param tag
     * @param callback
     */
    public static void post(final String url, RequestParams params, Object tag, AbstractCallback callback) {

        HttpParams httpParams = new HttpParams();

        // 添加协议参数
        addProtocolParams(httpParams);

        // 添加业务参数
        addBizParams(httpParams, params);

        String jsonString = JSONUtils.toJSONString(httpParams.urlParamsMap);
        Logger.i(HttpUtils.class.getSimpleName(), "请求参数列表 ：" + jsonString);
        OkGo.<String>post(url).tag(tag).params(httpParams).execute(callback);
    }

    /**
     * 添加协议参数
     *
     * @param httpParams
     */
    private static void addProtocolParams(HttpParams httpParams) {

        httpParams.put(Consts.REQUEST_PARAM_APP_ID, mContext.getAppId());
        httpParams.put(Consts.REQUEST_PARAM_DEVICE_ID, DeviceUtils.getDeviceId(mContext));
        httpParams.put(Consts.REQUEST_PARAM_USER_TOKEN, SpUtils.getUserToken());

    }

    /**
     * 添加业务参数
     *
     * @param httpParams
     * @param bizParams
     */
    private static void addBizParams(HttpParams httpParams, RequestParams bizParams) {
        if (bizParams == null) {
            return;
        }
        httpParams.put(Consts.REQUEST_BIZ_PARAMS, bizParams.toJSONString());
    }

    private static void initOkGo() {


        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        // headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        // headers.put("commonHeaderKey2", "commonHeaderValue2");

        HttpParams params = new HttpParams();
        // params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        // params.put("commonParamsKey2", "这里支持中文参数");
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志


        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        OkGo.getInstance().init(mContext)                        //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }
}
