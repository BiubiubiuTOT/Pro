package cdsp.android.http;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * 类描述：JSON 实体Callback
 * 创建人：mhl
 * 创建时间：2017/9/16  上午 10:36
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class JsonCallback<T> extends AbstractCallback {

    private TypeToken<T> type;
    private Class<T> clazz;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(TypeToken<T> type) {
        this.type = type;
    }

    @Override
    public void onSuccess(String result) {

        Gson gson = new Gson();
        T data = null;
        try {
            if (clazz != null) {
                data = gson.fromJson(result, clazz);
            } else if (type != null) {
                data = gson.fromJson(result, type.getType());
            }
            //成功返回数据
            onSuccess(data);
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            onError(resolveErrcode(exception), "数据解析出错");
        }
    }

    /**
     * 成功返回实体
     *
     * @param data
     */
    public abstract void onSuccess(T data);
}
