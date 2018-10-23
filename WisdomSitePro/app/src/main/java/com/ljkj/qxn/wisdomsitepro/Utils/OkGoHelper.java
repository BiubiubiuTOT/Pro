package com.ljkj.qxn.wisdomsitepro.Utils;

import android.graphics.Bitmap;

import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cdsp.android.http.AbstractCallback;
import cdsp.android.logging.Logger;
import cdsp.android.util.DeviceUtils;
import okhttp3.OkHttpClient;

/**
 * 类描述：OkGo帮助类
 * 创建人：lxx
 * 创建时间：2018/5/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class OkGoHelper {
    private static final String REQUEST_PARAM_APP_ID = "appId"; //请求协议参数，应用ID
    private static final String REQUEST_PARAM_DEVICE_ID = "deviceId"; //请求协议参数，设备id
    private static final String REQUEST_PARAM_USER_TOKEN = "userToken"; //请求协议参数，用户token
    private static final String REQUEST_PARAM_VERSION_CODE = "versionCode";
    private static final String REQUEST_BIZ_PARAMS = "params"; //业务参数

    private static OkGoHelper instance;

    private OkGoHelper() {
        init();
    }

    public static OkGoHelper getInstance() {
        if (instance == null) {
            synchronized (OkGoHelper.class) {
                if (instance == null) {
                    instance = new OkGoHelper();
                }
            }
        }
        return instance;
    }

    /**
     * get请求
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param tag           请求tag
     * @param callback      回调
     */
    public void get(String url, HashMap<String, Object> requestParams, Object tag, AbstractCallback callback) {
        HttpParams httpParams = getHttpParams(requestParams);
        OkGo.<String>get(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * get请求(V2.0版本)
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param tag           请求tag
     * @param callback      回调
     */
    public void get2(String url, HashMap<String, Object> requestParams, Object tag, AbstractCallback callback) {
        HttpParams httpParams = new HttpParams();
        for (Map.Entry<String, Object> entry : requestParams.entrySet()) {
            httpParams.put(entry.getKey(), String.valueOf(entry.getValue()), true);
        }
        OkGo.<String>get(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * 请求Bitmap
     *
     * @param url      请求图片地址
     * @param tag      请求tag
     * @param callback 回调
     */
    public void getBitmap(String url, Object tag, BitmapCallback callback) {
        OkGo.<Bitmap>get(url)
                .tag(tag)
                .execute(callback);
    }


    /**
     * post请求:参数无文件 application/x-www-form-urlencoded
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param tag           请求tag
     * @param callback      回调
     */
    public void post(String url, HashMap<String, Object> requestParams, Object tag, AbstractCallback callback) {
        HttpParams httpParams = getHttpParams(requestParams);
        OkGo.<String>post(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * post请求:参数无文件 application/x-www-form-urlencoded
     *
     * @param url        请求地址
     * @param httpParams 请求参数
     * @param tag        请求tag
     * @param callback   回调
     */
    public void postV2(String url, HttpParams httpParams, Object tag, AbstractCallback callback) {
        OkGo.<String>post(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * post请求(上传JSON类型的文本)
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param tag           请求tag
     * @param callback      回调
     */
    public void postJson(String url, Map<String, Object> requestParams, Object tag, AbstractCallback callback) {
        JSONObject jsonObject = new JSONObject(requestParams);
        OkGo.<String>post(url)
                .tag(tag)
                .upJson(jsonObject)
                .execute(callback);
    }


    /**
     * post请求(上传JSON数组)
     *
     * @param url       请求地址
     * @param jsonArray 请求参数
     * @param tag       请求tag
     * @param callback  回调
     */
    public void postJsonArray(String url, JSONArray jsonArray, Object tag, AbstractCallback callback) {
        OkGo.<String>post(url)
                .tag(tag)
                .upJson(jsonArray.toString())
                .execute(callback);
    }

    /**
     * post请求:参数有文件 multipart/form-data
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param fileParams    文件参数
     * @param tag           请求tag
     * @param callback      回调
     */
    public void post(String url, HashMap<String, Object> requestParams, HashMap<String, File> fileParams, Object tag, AbstractCallback callback) {
        HttpParams httpParams = getHttpParams(requestParams);
        putFileParams(httpParams, fileParams);
        OkGo.<String>post(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * 文件下载
     *
     * @param url      请求地址
     * @param tag      请求tag
     * @param callback 回调
     */
    public void downloadFile(String url, Object tag, FileCallback callback) {
        OkGo.<File>get(url)
                .tag(tag)
                .execute(callback);
    }

    /**
     * 上传文件
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param files         上传文件列表
     * @param tag           请求tag
     * @param callback      回调
     */
    public void uploadFiles(String url, HashMap<String, Object> requestParams, List<File> files, Object tag, AbstractCallback callback) {
        HttpParams httpParams = getHttpParams(requestParams);
        for (int i = 0; i < files.size(); i++) {
            httpParams.put("file_" + i, files.get(i));
        }
        OkGo.<String>post(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * 上传文件:一个key上传多个文件
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param files         上传文件列表
     * @param tag           请求tag
     * @param callback      回调
     */
    public void uploadFiles(String url, HashMap<String, Object> requestParams, HashMap<String, List<File>> files, Object tag, AbstractCallback callback) {
        HttpParams httpParams = getHttpParams(requestParams);
        Set<String> keys = files.keySet();
        for (String key : keys) {
            httpParams.putFileParams(key, files.get(key));
        }

        OkGo.<String>post(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * 上传文件:一个key上传多个文件(V2.0)
     *
     * @param url           上传地址
     * @param requestParams 请求参数
     * @param files         上传文件列表
     * @param tag           tag
     * @param callback      回调
     */
    public void uploadFiles2(String url, HashMap<String, String> requestParams, HashMap<String, List<File>> files, Object tag, AbstractCallback callback) {
        HttpParams httpParams = new HttpParams();
        for (String key : requestParams.keySet()) {
            httpParams.put(key, requestParams.get(key));
        }
        Set<String> keys = files.keySet();
        for (String key : keys) {
            httpParams.putFileParams(key, files.get(key));
        }
        OkGo.<String>post(url)
                .tag(tag)
                .params(httpParams)
                .execute(callback);
    }

    /**
     * 取消全局默认的OkHttpClient中标识为tag的请求
     *
     * @param tag tag
     */
    public void cancelTag(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    /**
     * 取消全局默认的OkHttpClient中的所有请求
     */
    public void cancelAll() {
        OkGo.getInstance().cancelAll();
    }

    //公共参数
    private void putCommonParams(HttpParams httpParams) {
        httpParams.put(REQUEST_PARAM_APP_ID, WApplication.getApplication().getAppId());
        httpParams.put(REQUEST_PARAM_DEVICE_ID, DeviceUtils.getDeviceId(WApplication.getApplication()));
        httpParams.put(REQUEST_PARAM_VERSION_CODE, BuildConfig.VERSION_CODE);
        httpParams.put(REQUEST_PARAM_USER_TOKEN, UserManager.getInstance().getUserToken());
    }

    private HttpParams getHttpParams(HashMap<String, Object> requestParams) {
        HttpParams httpParams = new HttpParams();
        putCommonParams(httpParams);
        httpParams.put(REQUEST_BIZ_PARAMS, new JSONObject(requestParams).toString());

        Logger.d(OkGoHelper.class.getSimpleName(), "请求参数为--> ：" + new JSONObject(httpParams.urlParamsMap).toString());
        return httpParams;
    }

    private void putFileParams(HttpParams httpParams, HashMap<String, File> fileParams) {
        Set<String> keys = fileParams.keySet();
        for (String fileKey : keys) {
            httpParams.put(fileKey, fileParams.get(fileKey));
        }
    }

    public void updateUserTokenHeader(String userToken) {
        OkGo.getInstance().getCommonHeaders().put(REQUEST_PARAM_USER_TOKEN, userToken);
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY); //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO); //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);

        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS); //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS); //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS); //全局的连接超时时间

        HttpHeaders commonHeaders = new HttpHeaders(); //header不支持中文，不允许有特殊字符
        commonHeaders.put(REQUEST_PARAM_APP_ID, WApplication.getApplication().getAppId());
        commonHeaders.put(REQUEST_PARAM_DEVICE_ID, DeviceUtils.getDeviceId(WApplication.getApplication()));
        commonHeaders.put(REQUEST_PARAM_VERSION_CODE, BuildConfig.VERSION_CODE + "");
        commonHeaders.put(REQUEST_PARAM_USER_TOKEN, UserManager.getInstance().getUserToken());
        HttpParams commonParams = new HttpParams(); //param支持中文,直接传,不要自己编码

        OkGo.getInstance().init(WApplication.getApplication())                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(commonHeaders)                      //全局公共头
                .addCommonParams(commonParams);                       //全局公共参数
    }

}
