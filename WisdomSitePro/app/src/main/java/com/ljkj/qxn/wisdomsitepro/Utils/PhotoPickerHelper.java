package com.ljkj.qxn.wisdomsitepro.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.foamtrace.photopicker.ImageConfig;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import java.util.ArrayList;

/**
 * 类描述：图片选择帮助类
 * 创建人：lxx
 * 创建时间：2018/6/7  14:51
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PhotoPickerHelper {

    /**
     * 开始选图片
     *
     * @param activity    activity
     * @param selectPaths 已选择的图片路径
     * @param requestCode 请求码
     */
    public static void startPicker(Activity activity, int requestCode, ArrayList<String> selectPaths) {
        startPicker(activity, requestCode, selectPaths, 9, true, true);
    }

    /**
     * 开始选图片
     *
     * @param activity    activity
     * @param selectPaths 已选择的图片路径
     * @param requestCode 请求码
     * @param photoCount  最大图片选择数量
     * @param multiMode   是否为多选模式
     * @param camera      是否显示摄像头
     */
    public static void startPicker(Activity activity, int requestCode, ArrayList<String> selectPaths, int photoCount, boolean multiMode, boolean camera) {
        PhotoPickerIntent intent = new PhotoPickerIntent(activity);
        intent.setSelectModel(multiMode ? SelectModel.MULTI : SelectModel.SINGLE);
        intent.setMaxTotal(photoCount);
        intent.setSelectedPaths(selectPaths);
        intent.setShowCarema(camera);
        intent.setImageConfig(getImageConfig());
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 开始选图片
     *
     * @param fragment    activity
     * @param selectPaths 已选择的图片路径
     * @param requestCode 请求码
     * @param photoCount  最大图片选择数量
     * @param multiMode   是否为多选模式
     * @param camera      是否显示摄像头
     */
    public static void startPicker(Fragment fragment, int requestCode, ArrayList<String> selectPaths, int photoCount, boolean multiMode, boolean camera) {
        PhotoPickerIntent intent = new PhotoPickerIntent(fragment.getContext());
        intent.setSelectModel(multiMode ? SelectModel.MULTI : SelectModel.SINGLE);
        intent.setMaxTotal(photoCount);
        intent.setSelectedPaths(selectPaths);
        intent.setShowCarema(camera);
        intent.setImageConfig(getImageConfig());
        fragment.startActivityForResult(intent, requestCode);
    }


    /**
     * 单张图片预览
     *
     * @param activity    activity
     * @param previewPath 预览地址
     */
    public static void startPreview(Activity activity, String previewPath) {
        ArrayList<String> list = new ArrayList<>();
        list.add(previewPath);
        startPreview(activity, 0, list, 0, false);
    }

    /**
     * 预览图片
     *
     * @param activity        activity
     * @param previewPaths    预览图片地址
     * @param currentPosition 当前预览的位置
     */
    public static void startPreview(Activity activity, int requestCode, ArrayList<String> previewPaths, int currentPosition) {
        startPreview(activity, requestCode, previewPaths, currentPosition, true);
    }

    /**
     * 预览图片
     *
     * @param activity        activity
     * @param previewPaths    预览图片地址
     * @param currentPosition 当前预览的位置
     * @param delete          是否显示右上角的删除图标
     */
    public static void startPreview(Activity activity, int requestCode, ArrayList<String> previewPaths, int currentPosition, boolean delete) {
        Intent intent = new Intent(activity, PhotoPreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PhotoPreviewActivity.EXTRA_PHOTOS, previewPaths);
        bundle.putInt(PhotoPreviewActivity.EXTRA_CURRENT_ITEM, currentPosition);
        bundle.putBoolean(PhotoPreviewActivity.EXTRA_DELETE, delete);
        intent.putExtras(bundle);
        if (delete) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivity(intent);
        }
    }

    public static void startPreview(Fragment fragment, int requestCode, ArrayList<String> previewPaths, int currentPosition, boolean delete) {
        Intent intent = new Intent(fragment.getContext(), PhotoPreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PhotoPreviewActivity.EXTRA_PHOTOS, previewPaths);
        bundle.putInt(PhotoPreviewActivity.EXTRA_CURRENT_ITEM, currentPosition);
        bundle.putBoolean(PhotoPreviewActivity.EXTRA_DELETE, delete);
        intent.putExtras(bundle);
        if (delete) {
            fragment.startActivityForResult(intent, requestCode);
        } else {
            fragment.startActivity(intent);
        }
    }

    private static ImageConfig getImageConfig() {
        ImageConfig config = new ImageConfig();
//        imageConfig.mimeType = new String[] { "image/jpeg", "image/png", "image/jpg","image/gif" };
        config.mimeType = new String[]{"image/jpeg", "image/png", "image/jpg"};
        config.minSize = 10 * 1024; //10KB
        config.minWidth = 10;
        config.minHeight = 10;
        return config;
    }

}
