package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.VideoSourceInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import cdsp.android.ui.widget.CheckableTextView;

/**
 * 类描述：视频监控Adapter
 * 创建人：lxx
 * 创建时间：2018/6/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoMonitorAdapter extends BaseQuickAdapter<VideoSourceInfo, BaseViewHolder> {
    public static final int TYPE_AREA = 1;
    public static final int TYPE_CAMERA = 2;

    private int type;

    public VideoMonitorAdapter(@Nullable List<VideoSourceInfo> data, @Type int type) {
        super(R.layout.adapter_video_monitor, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final VideoSourceInfo item) {
        final CheckableTextView titleText = helper.getView(R.id.tv_title);
        titleText.setClickable(false);
        titleText.setChecked(item.isChecked());
        titleText.setText(type == TYPE_AREA ? item.getAreaName() : item.getName());
        titleText.setBackgroundResource(type == TYPE_AREA ? R.drawable.bg_video_monitor_area_selector : R.drawable.bg_video_monitor_camera_selector);
    }

    @IntDef({TYPE_AREA, TYPE_CAMERA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

}
