package cdsp.android.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：gson工具类
 * 修订历史：
 * ================================================
 */

public abstract class GsonUtils {

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public static String toJSONFromGeneric(Object object) {
        Type mySuperClass = object.getClass().getGenericSuperclass();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object, mySuperClass);
    }

    public static String toJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T parseObject(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static ArrayList<Object> getJsonObject(String jsonArrayData, Type type) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(jsonArrayData);
            int iSize = jsonArray.length();
            if (iSize > 0) {
                for (int i = 0; i < iSize; i++) {
                    String objStr = jsonArray.getJSONObject(i).toString();
                    Object obj = parseObject(objStr, type);
                    arrayList.add(obj);
                }
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }


    // ===========================================================


    /**
     * 转为json格式输出
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 将json转为object对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json转为list集合
     *
     * @param json
     * @return
     */
    public static <T> List<T> jsonToList(String json, TypeToken typeToken) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 将json转为map
     *
     * @param json
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<K, V>>() {
        }.getType());
    }

    /**
     * 将json转为泛型
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T jsonToGeneric(String json, TypeToken<T> token) {
        Gson gson = new Gson();
        return gson.fromJson(json, token.getType());
    }


}
