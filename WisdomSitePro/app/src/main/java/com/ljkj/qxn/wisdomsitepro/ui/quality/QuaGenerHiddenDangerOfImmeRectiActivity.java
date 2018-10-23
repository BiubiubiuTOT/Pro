package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.quality.HandleDownFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityCheckResultContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DownFileInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.DownFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityCheckResultPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.LoadAndViewAttachmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;


/**
 * 类描述：一般隐患（立即整改）
 * 创建人：mhl
 * 创建时间：2018/2/6 10:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QuaGenerHiddenDangerOfImmeRectiActivity extends BaseActivity implements QualityCheckResultContract.View, HandleDownFileContract.View {
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_MAJOR = 2;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_check_result)
    protected TextView checkResultText;

    @BindView(R.id.ll_check_content)
    protected LinearLayout checkContentLayout;

    @BindView(R.id.ll_major_danger)
    protected LinearLayout majorDangerLayout;

    @BindView(R.id.item_project_name)
    ItemView projectNameItem;

    @BindView(R.id.item_check_date)
    ItemView checkDateItem;

    @BindView(R.id.item_check_part)
    ItemView checkPartItem;

    @BindView(R.id.item_check_code)
    protected ItemView checkCodeItem;

    @BindView(R.id.item_check_person)
    ItemView checkPersonItem;

    @BindView(R.id.item_danger_grade)
    ItemView dangerGradeItem;

    @BindView(R.id.item_finish_date)
    ItemView finishDateItem;

    @BindView(R.id.item_rectify_unit)
    ItemView rectifyUnitItem;

    @BindView(R.id.item_rectify_type)
    ItemView rectifyTypeItem;

    @BindView(R.id.item_supervise_date)
    ItemView superviseDateItem;

    @BindView(R.id.item_contacts)
    ItemView contactsItem;

    @BindView(R.id.item_phone)
    ItemView phoneItem;

    @BindView(R.id.tv_danger_content)
    TextView dangerContentText;


    @BindView(R.id.rl_attachment)
    RecyclerView attachmentRV; //附件

    protected LoadAndViewAttachmentAdapter attachmentAdapter;

    private int type;
    private String id;

    private QualityCheckResultPresenter checkResultPresenter;

    private DownFilePresenter downFilePresenter;
    protected QualityCheckResultDetail detail;

    public static void startActivity(Context context, int type, String id) {
        Intent intent = new Intent(context, QuaGenerHiddenDangerOfImmeRectiActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gener_hidden_danger_of_imme_recti);
    }

    @Override
    protected void initUI() {
        type = getIntent().getIntExtra("type", TYPE_NORMAL);
        id = getIntent().getStringExtra("id");
        if (type == TYPE_NORMAL) {
            tvTitle.setText("检查详情-一般隐患");
            majorDangerLayout.setVisibility(View.GONE);
        } else {
            tvTitle.setText("检查详情-重大隐患");
            majorDangerLayout.setVisibility(View.VISIBLE);
        }
        attachmentRV.setNestedScrollingEnabled(false);
        attachmentRV.setLayoutManager(new LinearLayoutManager(this));
        attachmentRV.setAdapter(attachmentAdapter = new LoadAndViewAttachmentAdapter(this));
    }

    @Override
    protected void initData() {
        checkResultPresenter = new QualityCheckResultPresenter(this, QualityModel.newInstance());
        downFilePresenter = new DownFilePresenter(this, QualityModel.newInstance());
        addPresenter(checkResultPresenter);
        addPresenter(downFilePresenter);
        checkResultPresenter.getCheckResultDetail(id, UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.tv_next, R.id.tv_check_result})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_check_result:
                checkContentLayout.setVisibility(checkContentLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                Drawable right = checkContentLayout.getVisibility() == View.VISIBLE ? ContextCompat.getDrawable(this, R.mipmap.ic_collapse) : ContextCompat.getDrawable(this, R.mipmap.ic_expand);
                checkResultText.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
                break;
            case R.id.tv_next:
                if (detail != null) {
                    next();
                }
                break;
            default:
                break;
        }
    }

    protected void next() {
        QuaImmediateRectificationActivity.startActivity(this, detail.proInspect.getId(), detail.proInspect.getProjectName(), detail.getProAddress(), detail.proInspect.getCheckNo());

    }

    @Override
    public void showCheckResultDetail(QualityCheckResultDetail detail) {
        this.detail = detail;
        projectNameItem.setContent(detail.proInspect.getProjectName());
        checkDateItem.setContent(DateUtil.format(detail.proInspect.getJcrqTime()));
        checkPartItem.setContent(detail.proInspect.getJcgcbw());
        checkCodeItem.setContent(detail.proInspect.getCheckNo());
        checkPersonItem.setContent(detail.proInspect.getJcry());
        dangerGradeItem.setContent(detail.proInspect.getYhdj());
        finishDateItem.setContent(DateUtil.format(detail.proInspect.getZgqx()));
        rectifyUnitItem.setContent(detail.proInspect.getZgfzr());
        rectifyTypeItem.setContent(detail.proInspect.getZglx());
        superviseDateItem.setContent(detail.proInspect.getSqjcdbrq());
        contactsItem.setContent(detail.proInspect.getLinker());
        phoneItem.setContent(detail.proInspect.getLinkerPhone());
        dangerContentText.setText(detail.proInspect.getYhxq());

        downFilePresenter.handleDownFile(detail.proInspect.getJcjlb(), "fileDown");
    }

    @Override
    public void showDownFile(List<DownFileInfo> fileInfos) {
        //附件
        List<EnclosureInfo> imagePaths = new ArrayList<>();
        for (DownFileInfo downFileInfo : fileInfos) {
            EnclosureInfo enclosureInfo = new EnclosureInfo(downFileInfo.getName());
            enclosureInfo.setTypeSource(1);
            enclosureInfo.setName(downFileInfo.getName());
            enclosureInfo.setValue(downFileInfo.getValue());
            imagePaths.add(enclosureInfo);
        }
        attachmentAdapter.loadData(imagePaths);

    }


}
