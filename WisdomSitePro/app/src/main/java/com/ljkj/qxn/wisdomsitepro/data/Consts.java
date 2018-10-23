package com.ljkj.qxn.wisdomsitepro.data;

import android.os.Environment;

import java.io.File;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 11:51
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class Consts {


    public class APPParams {

        public final static String APP_ID = "bbpapp10";

        public final static int ROWS = 10;
    }

    public class XR_LOAD_TYPE {

        public static final int LOAD_MORE = 0x80;

        public static final int REFRESH = 0x90;
    }

    public class EVENT_TYPE {

        public static final int INSPECTION_TYPE = 0x70;
        public static final int INSPECTION_PASS = 0x71;
        public static final int INSPECTION_ADD = 0x72;

    }

    public static class Cache {

        /**
         * 项目文件缓存文件夹
         */

        public final static String FILE_PATH = "wisdomSitePro";

        /**
         * 升级安装包下载路径
         */
        public final static String APK_PATH = "apk";

        /**
         * 得到当前外部存储设备的目录
         */
        public static final String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + FILE_PATH;

        /**
         * 压缩图片缓存文件夹
         */
        public static final String COMPRESS_IMAGE_CACHE_DIR = "compress_images";

        /**
         * 图片缓存文件夹
         */
        public static final String COMPRESS_CACHE_DIR = "cache_images";


        public static final String PDF_DIR = SDCardRoot + File.separator + "pdf";
    }
}
