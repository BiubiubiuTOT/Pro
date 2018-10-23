package com.ljkj.qxn.wisdomsitepro.presenter.common;

import android.text.TextUtils;

import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageTagCompressorContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * 类描述：图片压缩
 * 创建人：mhl
 * 创建时间：2018/3/14 15:52
 * 修改人：lxx
 * 修改时间：
 * 修改备注：替换为Luban
 */

public class ImageTagCompressorPresenter extends ImageTagCompressorContract.Presenter {

    public ImageTagCompressorPresenter(ImageTagCompressorContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void compressorImages(final String tag, final List<String> sourceFilePaths) {
        if (isAttach) {
            view.showProgress("图片处理中...");
        }
        final List<String> compressFilePaths = new ArrayList<>();
        Luban.with(WApplication.getApplication())
                .load(sourceFilePaths)
                .ignoreBy(50)
                .setTargetDir(getPath())
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return getFileName(filePath);
                    }
                })
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        compressFilePaths.add(file.getAbsolutePath());
                        if (compressFilePaths.size() == sourceFilePaths.size() && isAttach) {
                            view.hideProgress();
                            view.showCompressFilePaths(tag, compressFilePaths);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //出错处理：将原路径作为压缩后的路径
                        String errorPath = sourceFilePaths.get(compressFilePaths.size());
                        compressFilePaths.add(errorPath);
                        if (compressFilePaths.size() == sourceFilePaths.size() && isAttach) {
                            view.hideProgress();
                            view.showCompressFilePaths(tag, compressFilePaths);
                        }
                        e.printStackTrace();
                    }
                }).launch();
    }

    private String getFileName(String filePath) {
        File file = new File(filePath);
        String name = file.getName();
        if (TextUtils.isEmpty(name)) {
            String suffix = filePath.substring(filePath.lastIndexOf("."), filePath.length());
            name = System.currentTimeMillis() + (int) (Math.random() * 1000) + (TextUtils.isEmpty(suffix) ? ".jpg" : suffix);
        }
        return name;
    }

    private String getPath() {
        String path = Consts.Cache.SDCardRoot + File.separator + Consts.Cache.COMPRESS_IMAGE_CACHE_DIR;
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }


}
