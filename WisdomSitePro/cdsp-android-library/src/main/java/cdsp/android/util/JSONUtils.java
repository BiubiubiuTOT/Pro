package cdsp.android.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：json工具类
 * 修订历史：
 * ================================================
 */

public abstract class JSONUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param jsonStr
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static Object parse(String jsonStr) {
        return JSON.parse(jsonStr);
    }

    /**
     * 将json字符串转换为json对象
     *
     * @param jsonStr
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static JSONObject parseObject(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param jsonStr
     * @param cls
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static <T> T parseObject(String jsonStr, Class<T> cls) {
        return JSON.parseObject(jsonStr, cls);
    }

    /**
     * 将json字符串转换为json数组
     *
     * @param jsonStr
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static JSONArray parseArray(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }


    /**
     * 将json字符串转换为对象数组
     *
     * @param jsonStr
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static <T> Object[] parseToArray(String jsonStr) {
        return JSON.parseArray(jsonStr).toArray();
    }

    /**
     * 将json字符串转换为对象数组
     *
     * @param jsonStr
     * @param cls
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static <T> Object[] parseArray(String jsonStr, Class<T> cls) {
        return JSON.parseArray(jsonStr, cls).toArray();
    }

    /**
     * 将json字符串转换为列表
     *
     * @param jsonStr
     * @param cls
     * @return
     * @author loujingying@aliyun.com, 2017年9月4日 上午2:26:55
     * @since CDSP1.0.0
     */
    public static <T> List<T> parseList(String jsonStr, Class<T> cls) {
        return JSON.parseArray(jsonStr, cls);
    }

    /**
     * json字符串转化为map
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseMap(String jsonStr) {
        Map<String, Object> m = JSON.parseObject(jsonStr);
        return m;
    }

    /**
     * 将map转化为json对象
     * @param map
     * @return
     */
    public static JSONObject mapToJSONObject(Map<String,Object> map) {
        JSONObject object = new JSONObject(map);
        return object;
    }


}
