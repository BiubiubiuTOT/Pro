package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEducationDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.BlockEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.DividerEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.ImageListEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtReEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEducationDetailInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeEducationDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeEducationDetailAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;


/**
 * 类描述：安全教育详情
 * 创建人：mhl
 * 创建时间：2018/3/14 13:39
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeEducationDetailActivity extends BaseActivity implements SafeEducationDetailContract.View, QueryFileContract.View {
    private static final String KEY_REC_ID = "recId";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SafeEducationDetailAdapter safeEducationDetailAdapter;
    private SafeEducationDetailPresenter presenter;
    private QueryFilePresenter queryFilePresenter;
    private SafeEducationDetailInfo safeEducationDetailInfo;

    public static void startActivity(Context context, String recId) {
        Intent intent = new Intent(context, SafeEducationDetailActivity.class);
        intent.putExtra(KEY_REC_ID, recId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_education_detail);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("安全教育交底详情");
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(safeEducationDetailAdapter = new SafeEducationDetailAdapter(this, new ArrayList<BaseEntity>()));
    }

    @Override
    protected void initData() {
        String recId = getIntent().getStringExtra(KEY_REC_ID);
        presenter = new SafeEducationDetailPresenter(this, SafeModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        addPresenter(presenter);
        presenter.viewSafeEduDetail(recId);
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showSafeEduDetail(SafeEducationDetailInfo data) {
        this.safeEducationDetailInfo = data;
        showData(data, null);
        queryFilePresenter.queryFile(data.id);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        if (fileEntities != null && fileEntities.size() > 0) {
            Iterator<FileEntity> iterator = fileEntities.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().isImageFile()) {
                    iterator.remove();
                }
            }
            showData(safeEducationDetailInfo, fileEntities);
        }
    }

    private void showData(SafeEducationDetailInfo data, List<FileEntity> fileEntities) {
        List<BaseEntity> datas = new ArrayList<BaseEntity>();
        datas.add(new TxtReEntryEntity("交底名称", data.presentationName));
        datas.add(new TxtReEntryEntity("交底日期", data.presentationDate));
        datas.add(new TxtEntryEntity("交底内容"));
        datas.add(new BlockEntryEntity(data.presentationInfo));

        if (fileEntities != null && fileEntities.size() > 0) {
            datas.add(new TxtEntryEntity("交底图片"));
            datas.add(new ImageListEntity(fileEntities));
        }

        datas.add(new DividerEntity(10.0f));
        datas.add(new TxtReEntryEntity("培训人", data.trainer));

        StringBuilder sb = new StringBuilder();
        if (data.persons != null) {
            for (int i = 0; i < data.persons.size(); i++) {
                if (i == data.persons.size() - 1) {
                    sb.append(data.persons.get(i).teamName);
                } else {
                    sb.append(data.persons.get(i).teamName).append(",");
                }
            }
        }
        datas.add(new TxtReEntryEntity("受训人", sb.toString()));


        if (data.isReport == 1) {
            datas.add(new TxtReEntryEntity("是否上报监督机构", "是"));
        } else {
            datas.add(new TxtReEntryEntity("是否上报监督机构", "否"));
        }
        safeEducationDetailAdapter.setItems(datas);
        safeEducationDetailAdapter.notifyDataSetChanged();
    }
}
