package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtReEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.QualityInforOfMixedSoilAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：混泥土信息-UI基类
 * 创建人：mhl
 * 创建时间：2018/2/6 17:08
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseQualityInforOfMixedSoilActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    protected TextView tvTitle;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    protected QualityInforOfMixedSoilAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_quality_infor_of_mixed_soil);
    }

    @Override
    protected void initUI() {
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(adapter = new QualityInforOfMixedSoilAdapter(this, new ArrayList<BaseEntity>()));
    }

    @Override
    protected void initData() {

        List<BaseEntity> datas = new ArrayList<BaseEntity>();

        datas.add(new TxtReEntryEntity("检验编号", "底部"));
        datas.add(new TxtReEntryEntity("委托单位", "贵州"));
        datas.add(new TxtReEntryEntity("见证单位", "贵州"));
        datas.add(new TxtReEntryEntity("检验编号", "底部"));
        datas.add(new TxtReEntryEntity("见证人", "张三"));
        datas.add(new TxtReEntryEntity("使用部位", "底部"));
        datas.add(new TxtReEntryEntity("样品名称", "普通"));
        datas.add(new TxtReEntryEntity("设计强度等级", "3级"));
        datas.add(new TxtReEntryEntity("养护方式", "养护"));

        datas.add(new TxtReEntryEntity("龄期（d）", "90天"));
        datas.add(new TxtReEntryEntity("成型日期", "2018-01-10"));
        datas.add(new TxtReEntryEntity("检验日期", "2018-01-10"));
        datas.add(new TxtReEntryEntity("检验结果", "良好"));
        datas.add(new TxtReEntryEntity("检验单位", "贵州"));
        datas.add(new TxtReEntryEntity("外观质量", "一般"));

        datas.add(new TxtEntryEntity("验收附件"));
        datas.add(new FileEntity("质量申报手册"));
        datas.add(new FileEntity("申报程序性文件"));
        datas.add(new FileEntity("质量记录"));
        adapter.setItems(datas);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
    }

    protected String getDateStr(String millisStr) {
        try {
            long millis = Long.parseLong(millisStr);
            return DateUtils.timeStampToDate(millis, DateUtils.PATTERN_DATE);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
