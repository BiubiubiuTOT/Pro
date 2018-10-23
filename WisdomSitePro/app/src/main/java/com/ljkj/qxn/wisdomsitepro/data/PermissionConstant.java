package com.ljkj.qxn.wisdomsitepro.data;

import android.Manifest;

/**
 * 类描述：权限常量
 * 创建人：mhl
 * 创建时间：2017/9/19  下午 14:13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PermissionConstant {

    /**
     * 图片选择权限
     */
    public static final String[] PERMISSIONS_OF_IMAGE = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    /**
     * 位置信息权限
     */
    public static final String[] PERMISSIONS_OF_LOCATION = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    /**
     * 文件权限
     */
    public static final String[] PERMISSIONS_OF_FILE = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
