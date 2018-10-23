package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.HandleDownFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityCheckResultContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.DownFileInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ListFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.DownFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityCheckResultPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.LoadAndViewAttachmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;


/**
 * 类描述：检查详情（UI基类）
 * 创建人：mhl
 * 创建时间：2018/2/6 11:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseCheckDetailsActivity extends BaseActivity implements QualityCheckResultContract.View {

    protected static final int TYPE_REPORT = 1;
    protected static final int TYPE_MONEY = 2;
    protected static final int TYPE_PLAN = 3;
    protected static final int TYPE_MEASURE = 4;

    @BindView(R.id.tv_title)
    protected TextView tvTitle;

    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.tv_check_result)
    protected TextView checkResultText;

    @BindView(R.id.tv_rectify_reply)
    protected TextView rectifyReplyText;

    @BindView(R.id.rl_rectify_reply)
    protected RelativeLayout rectifyReplyLayout;

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


    @BindView(R.id.tv_next)
    protected TextView tvNext;


    @BindView(R.id.tv_project_name)
    protected TextView projectNameText;

    @BindView(R.id.tv_project_address)
    protected TextView projectAddressText;

    @BindView(R.id.tv_situation)
    protected TextView situationText;

    @BindView(R.id.tv_unit_advice)
    protected TextView unitAdviceText;

    @BindView(R.id.item_whether_plan)
    protected ItemView whetherPlanItem;

    @BindView(R.id.item_whether_measure)
    protected ItemView whetherMeasureItem;

    @BindView(R.id.item_money)
    protected ItemView moneyItem;

    @BindView(R.id.rl_rectify_image)
    protected RecyclerView rectifyImageRV;

    @BindView(R.id.rl_report_attachment)
    protected RecyclerView reportAttachmentRV;

    @BindView(R.id.rl_money_attachment)
    protected RecyclerView moneyAttachmentRV;

    @BindView(R.id.rl_plan_attachment)
    protected RecyclerView planAttachmentRV;

    @BindView(R.id.rl_measure_attachment)
    protected RecyclerView measureAttachmentRV;

    @BindView(R.id.iv_status1)
    protected ImageView statusImage1;

    @BindView(R.id.iv_status2)
    protected ImageView statusImage2;

    protected ShowImageAdapter rectifyImageAdapter;
    protected LoadAndViewAttachmentAdapter reportAdapter;
    protected LoadAndViewAttachmentAdapter moneyAdapter;
    protected LoadAndViewAttachmentAdapter planAdapter;
    protected LoadAndViewAttachmentAdapter measureAdapter;

    private QualityCheckResultPresenter checkResultPresenter;
    private DownFilePresenter checkResultImagePresenter;
    private QueryFilePresenter queryFilePresenter;

    protected QualityCheckResultDetail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_check_the_details);
    }

    @Override
    protected void initUI() {
        attachmentRV.setNestedScrollingEnabled(false);
        attachmentRV.setLayoutManager(new LinearLayoutManager(this));
        attachmentRV.setAdapter(attachmentAdapter = new LoadAndViewAttachmentAdapter(this));

        ///////////////////////////

        rectifyImageRV.setLayoutManager(new GridLayoutManager(this, 3));
        reportAttachmentRV.setLayoutManager(new LinearLayoutManager(this));
        moneyAttachmentRV.setLayoutManager(new LinearLayoutManager(this));
        measureAttachmentRV.setLayoutManager(new LinearLayoutManager(this));
        planAttachmentRV.setLayoutManager(new LinearLayoutManager(this));
        rectifyImageRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        rectifyImageRV.setAdapter(rectifyImageAdapter = new ShowImageAdapter(this));
        rectifyImageRV.setNestedScrollingEnabled(false);

        reportAttachmentRV.setAdapter(reportAdapter = new LoadAndViewAttachmentAdapter(this));
        moneyAttachmentRV.setAdapter(moneyAdapter = new LoadAndViewAttachmentAdapter(this));
        planAttachmentRV.setAdapter(planAdapter = new LoadAndViewAttachmentAdapter(this));
        measureAttachmentRV.setAdapter(measureAdapter = new LoadAndViewAttachmentAdapter(this));
    }

    @Override
    protected void initData() {
        checkResultPresenter = new QualityCheckResultPresenter(this, QualityModel.newInstance());
        addPresenter(checkResultPresenter);

        checkResultImagePresenter = new DownFilePresenter(new HandleDownFileContract.View() {
            @Override
            public void showProgress(String message) {
            }

            @Override
            public void hideProgress() {
            }

            @Override
            public void showError(String message) {
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
        }, QualityModel.newInstance());

        queryFilePresenter = new QueryFilePresenter(new QueryFileContract.View() {
            @Override
            public void showProgress(String message) {
            }

            @Override
            public void hideProgress() {
            }

            @Override
            public void showError(String message) {
            }

            @Override
            public void showFiles(List<FileEntity> data) {
                //显示整改图片
                rectifyImageAdapter.loadData(data);
            }
        }, CommonModel.newInstance());

        addPresenter(queryFilePresenter);
        addPresenter(checkResultImagePresenter);
    }

    @OnClick({R.id.tv_back, R.id.tv_check_result, R.id.tv_rectify_reply})
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
            case R.id.tv_rectify_reply:
                rectifyReplyLayout.setVisibility(rectifyReplyLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                Drawable rightDrawable = checkContentLayout.getVisibility() == View.VISIBLE ? ContextCompat.getDrawable(this, R.mipmap.ic_collapse) : ContextCompat.getDrawable(this, R.mipmap.ic_expand);
                rectifyReplyText.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawable, null);
                break;
            default:
                break;
        }
    }

    protected void requestDetail(String id) {
        checkResultPresenter.getCheckResultDetail(id, UserManager.getInstance().getProjectId());
    }

    @Override
    public void showCheckResultDetail(QualityCheckResultDetail detail) {
        this.detail = detail;
        if (detail.proInspect.getYhdj().equals("重大隐患")) {
            majorDangerLayout.setVisibility(View.VISIBLE);
        } else {
            majorDangerLayout.setVisibility(View.GONE);
        }

        projectNameItem.setContent(detail.proInspect.getProjectName());
        checkDateItem.setContent(DateUtil.format(detail.proInspect.getJcrqTime()));
        checkPartItem.setContent(detail.proInspect.getJcgcbw());
        checkCodeItem.setContent(detail.proInspect.getCheckNo());
        checkPersonItem.setContent(detail.proInspect.getJcry());
        dangerGradeItem.setContent(detail.proInspect.getYhdj());
        finishDateItem.setContent(DateUtil.format(detail.proInspect.getZgqx()));
        rectifyUnitItem.setContent(detail.proInspect.getZgfzr());
        rectifyTypeItem.setContent(detail.proInspect.getZglx());
        superviseDateItem.setContent(DateUtil.format(detail.proInspect.getSqjcdbrq()));
        contactsItem.setContent(detail.proInspect.getLinker());
        phoneItem.setContent(detail.proInspect.getLinkerPhone());
        dangerContentText.setText(detail.proInspect.getYhxq());

        //获取检查结果的现场照片、整改附件
        checkResultImagePresenter.handleDownFile(detail.proInspect.getJcjlb(), "fileDown");

        queryFilePresenter.queryFile(detail.base.getId());

        //回复
        projectNameText.setText(detail.base.getProName());
        projectAddressText.setText(detail.base.getProAddress());
        situationText.setText(detail.base.getZgqk());
        unitAdviceText.setText(detail.base.getJldwyj());
        whetherPlanItem.setContent(detail.base.getSfzdya());
        whetherMeasureItem.setContent(detail.base.getSfzdcs());
        moneyItem.setContent(detail.base.getZgzjse());
    }


}
