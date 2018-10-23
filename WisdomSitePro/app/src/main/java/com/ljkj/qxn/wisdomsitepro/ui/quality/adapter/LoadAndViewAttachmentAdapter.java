package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.JumpUtil;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.logging.Logger;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 预览、下载附件Adapter
 * 创建人：lxx
 * 创建时间：2018/3/16 15:22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoadAndViewAttachmentAdapter extends BaseRecyclerAdapter<EnclosureInfo, LoadAndViewAttachmentAdapter.VH> {
    public LoadAndViewAttachmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        super.onBindViewHolder(holder, position);
        final EnclosureInfo info = getItem(position);
        holder.titleText.setText(info.getTitle());
        holder.loadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getTypeSource() == 1) { //政府端附件
                    jumpToBrowser(info.getValue());
//                    downloadApk(mContext, info.getValue(), info.getName());
//                    ToastUtils.showShort("已开始下载");
                } else {
                    JumpUtil.toBrowserForEnclosure(mContext, info);
                }

            }
        });

    }

    private void jumpToBrowser(String path) {
        Uri uri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    private void downloadApk(Context context, String url, String fileName) {
        try {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle("");
            String downloadPath = Consts.Cache.SDCardRoot + File.separator + Consts.Cache.FILE_PATH + File.separator + fileName;
            File file = new File(downloadPath);
            if (file.exists()) {
                file.delete();
            }
            Uri fileUri = Uri.parse("file://" + downloadPath);
//            request.setDestinationUri(fileUri);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
            long downloadId = downloadManager.enqueue(request);
            Logger.e("downloadId=" + downloadId);
        } catch (Exception e) {
            e.printStackTrace();
            jumpToBrowser(url);
        }
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView titleText;

        @BindView(R.id.tv_load)
        TextView loadText;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
