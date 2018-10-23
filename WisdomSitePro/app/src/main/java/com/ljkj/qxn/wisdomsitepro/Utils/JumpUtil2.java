package com.ljkj.qxn.wisdomsitepro.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import cdsp.android.glide.GlideHelper;
import cdsp.android.http.RequestParams;

/**
 * 跳转工具
 * 创建人：lxx
 * 创建时间：2018/3/17 14:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class JumpUtil2 {

    public static void toBrowserForEnclosure(Context context, FileEntity info) {
        String fieldId = info.getFileId();
        final String url = HostConfig.getFileDownUrl(fieldId);

        toBrowser(context, url);
    }


    public static void toBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
