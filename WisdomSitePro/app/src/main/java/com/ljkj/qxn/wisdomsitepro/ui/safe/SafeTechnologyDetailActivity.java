package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeTechnologyDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyDetail;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeTechnologyDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全技术交底详情
 * 创建人：lxx
 * 创建时间：2018/9/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyDetailActivity extends BaseActivity implements SafeTechnologyDetailContract.View, QueryFileContract.View {
    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_name)
    ItemView nameItem;

    @BindView(R.id.item_date)
    ItemView dateItem;

    @BindView(R.id.item_part)
    ItemView partItem;

    @BindView(R.id.item_person)
    ItemView personItem;

    @BindView(R.id.item_safe_person)
    ItemView safePersonItem;

    @BindView(R.id.item_team)
    ItemView teamItem;

    @BindView(R.id.item_team_head)
    ItemView teamHeadItem;

    @BindView(R.id.item_whether_report)
    ItemView whetherReportItem;

    @BindView(R.id.tv_contents)
    TextView contentText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private SafeTechnologyDetailPresenter safeTechnologyDetailPresenter;
    private QueryFilePresenter queryFilePresenter;
    private ShowImageAdapter adapter;

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, SafeTechnologyDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_technology_detail);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全技术交底详情");
        imagesRV.setLayoutManager(new GridLayoutManager(this, 4));
        imagesRV.setAdapter(adapter = new ShowImageAdapter(this));
    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        safeTechnologyDetailPresenter = new SafeTechnologyDetailPresenter(this, SafeModel.newInstance());
        addPresenter(safeTechnologyDetailPresenter);
        addPresenter(queryFilePresenter);
        safeTechnologyDetailPresenter.getSafeTechnologyDetail(id);
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        Iterator<FileEntity> iterator = fileEntities.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isImageFile()) {
                iterator.remove();
            }
        }
        adapter.loadData(fileEntities);
    }

    @Override
    public void showSafeTechnologyDetail(SafeTechnologyDetail detail) {
        nameItem.setContent(detail.presentationName);
        dateItem.setContent(detail.presentationDate);
        partItem.setContent(detail.presentationPosition);
        personItem.setContent(detail.presentationPerson);
        contentText.setText(detail.presentationInfo);
        safePersonItem.setContent(detail.safetyOfficer);

        StringBuilder teams = new StringBuilder();
        StringBuilder teamLeaders = new StringBuilder();
        if (detail.persons != null) {
            for (int i = 0; i < detail.persons.size(); i++) {
                teams.append(detail.persons.get(i).teamName);
                teamLeaders.append(detail.persons.get(i).teamPerson);
                if (i != detail.persons.size() - 1) {
                    teams.append(",");
                    teamLeaders.append(",");
                }
            }
        }
        teamItem.setContent(teams.toString());
        teamHeadItem.setContent(teamLeaders.toString());

        whetherReportItem.setContent(detail.isReport == 1 ? "是" : "否");
        queryFilePresenter.queryFile(detail.id);
    }

}
