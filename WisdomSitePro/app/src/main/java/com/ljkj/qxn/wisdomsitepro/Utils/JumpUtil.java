package com.ljkj.qxn.wisdomsitepro.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import cdsp.android.http.RequestParams;

/**
 * 跳转工具
 * 创建人：lxx
 * 创建时间：2018/3/17 14:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class JumpUtil {


    public static void toBrowserForEnclosure(Context context, EnclosureInfo info) {
        String extend = info.getExtend();
        String isDownload = "true";
        if (extend.contains("pdf")) {
            isDownload = "false";
        }
        String subclassname = "org.jeecgframework.web.cgform.entity.upload.CgUploadEntity";
        String fieldId = info.getId();
        RequestParams requestParams = new RequestParams();
        requestParams.put("fileid", fieldId);
        requestParams.put("isDownload", isDownload);
        requestParams.put("subclassname", subclassname);
        StringBuilder url = new StringBuilder();
        url.append(HostConfig.getHost()).append("/commController.do?viewFile");
        url.append(UrlUtil.buildProtocolParams(url.toString()));
        url.append(UrlUtil.encodeParams(requestParams));

        toBrowser(context, url.toString());
    }


    public static void toBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
