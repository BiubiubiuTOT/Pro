package cdsp.android.util;

import android.content.Context;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：序列化和反序列化工具类
 * 修订历史：
 * ================================================
 */

public abstract class SerializableUtils {

    /**
     * 序列化数据
     *
     * @param context
     * @param fileName
     * @param obj
     * @throws IOException
     */
    public static void serializeData(Context context, String fileName, Object obj) throws IOException {
        if (!(obj instanceof Serializable) && !(obj instanceof Externalizable)) {
            throw new InvalidClassException("Object must be serialized!");
        }
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream ostream = new ObjectOutputStream(fos);
        ostream.writeObject(obj);
        ostream.flush();
        ostream.close();
        fos.close();
    }

    /**
     * 反序列化数据
     *
     * @param context
     * @param fileName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Object deserializeData(Context context, String fileName) throws ClassNotFoundException, IOException {
        FileInputStream fis = context.openFileInput(fileName);
        ObjectInputStream s = new ObjectInputStream(fis);
        Object obj = s.readObject();
        s.close();
        fis.close();
        return obj;
    }

}
