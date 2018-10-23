package cdsp.android.logging;

import android.app.Application;

import cdsp.android.base.BaseApplication;
import cdsp.android.util.JSONUtils;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/7/11
 * 描    述：日志打印工具类
 * 修订历史：
 * ================================================
 */

public abstract class Logger {

    private static Application mContext;

    /**
     * 是否为开发模式，默认为true
     */
    private static boolean isDevMode = true;

    private static final LogPrinter printer = new LogPrinter();

    private Logger() {
    }

    /**
     * 初始化日志工具类
     *
     * @param app
     */
    public static void init(BaseApplication app) {
        Logger.mContext = app;
        isDevMode = app.isDevMode();
    }

    public static void d(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.d(message, tag);
    }

    public static void e(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.e(message, tag);
    }

    public static void w(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.w(message, tag);
    }

    public static void i(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.i(message, tag);
    }

    public static void v(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.v(message, tag);
    }

    public static void wtf(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.wtf(message, tag);
    }

    /**
     * 打印Json
     *
     * @param json
     */
    public static void json(String json) {
        if (!isDevMode) {
            return;
        }
        printer.json(json, "");
    }

    /**
     * 打印Xml
     *
     * @param xml
     */
    public static void xml(String xml) {
        if (!isDevMode) {
            return;
        }
        printer.xml(xml, "");
    }

    /**
     * 打印Json
     *
     * @param json
     */
    public static void json(String tag, String json) {
        if (!isDevMode) {
            return;
        }
        printer.json(json, tag);
    }

    /**
     * 打印Xml
     *
     * @param xml
     */
    public static void xml(String tag, String xml) {
        if (!isDevMode) {
            return;
        }
        printer.xml(xml, tag);
    }

    /**
     * 打印object
     *
     * @param object
     */
    public static void object(Object object) {
        object("", object);
    }

    /**
     * 打印object
     *
     * @param object
     */
    public static void object(String tag, Object object) {
        if (object != null) {
            String jsonStr = JSONUtils.toJSONString(object);
            String classInfo = object.getClass().getSimpleName();
            json(tag, classInfo + ":" + jsonStr);
        }
    }

    // 纯输出
    public static void printD(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.printD(message, tag);
    }

    public static void printE(String tag, Object message) {
        if (!isDevMode) {
            return;
        }
        printer.printE(message, tag);
    }

    public static void d(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.d(message, "");
    }

    public static void e(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.e(message, "");
    }

    public static void w(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.w(message, "");
    }

    public static void i(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.i(message, "");
    }

    public static void v(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.v(message, "");
    }

    public static void wtf(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.wtf(message, "");
    }

    // 纯输出
    public static void printD(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.printD(message, "");
    }

    public static void printE(Object message) {
        if (!isDevMode) {
            return;
        }
        printer.printE(message, "");
    }

}
