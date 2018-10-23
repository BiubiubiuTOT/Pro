package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectOverViewContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.ProjectOverViewInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectOverViewPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;

public class ProjectOverviewBrandActivity extends BaseActivity implements ProjectOverViewContract.View, QueryFileContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.ll_base)
    LinearLayout baseLayout;

    @BindView(R.id.ll_unit)
    LinearLayout unitLayout;

    @BindView(R.id.ll_institution)
    LinearLayout institutionLayout;

    @BindView(R.id.content)
    LinearLayout contentLayout;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private ProjectOverViewPresenter projectOverViewPresenter;
    private QueryFilePresenter queryFilePresenter;
    private ShowImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_overview_brand);
    }

    @Override
    protected void initUI() {
        titleText.setText("工程概况牌");
        contentLayout.setVisibility(View.GONE);
        imagesRV.setLayoutManager(new GridLayoutManager(this, 3));
        imagesRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        imagesRV.setAdapter(adapter = new ShowImageAdapter(this));
        imagesRV.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        projectOverViewPresenter = new ProjectOverViewPresenter(this, ProjectModel.newInstance());
        addPresenter(projectOverViewPresenter);
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        projectOverViewPresenter.getProjectOverView(UserManager.getInstance().getProjectId());
    }

    @Override
    public void showProjectOverview(ProjectOverViewInfo info) {
        if (info == null) {
            return;
        }
        contentLayout.setVisibility(View.VISIBLE);

        baseLayout.addView(getTextView("工程名称：" + info.base.projName));
        baseLayout.addView(getTextView("项目编号：" + info.base.projCode));
        baseLayout.addView(getTextView("总建筑面积：" + info.base.totalArea));
        baseLayout.addView(getTextView("地上层数：" + info.base.numberOfFloors));
        baseLayout.addView(getTextView("地下层数：" + info.base.undergroundStoreys));
        baseLayout.addView(getTextView("工程造价：" + info.base.constructionCost));
        baseLayout.addView(getTextView("建筑高度：" + info.base.buildingHeight));
        baseLayout.addView(getTextView("建筑结构：" + info.base.structureTypeName));
        baseLayout.addView(getTextView("工程地址：" + info.base.projAddress));
        baseLayout.addView(getTextView("项目类型：" + info.base.projTypeName));
        baseLayout.addView(getTextView("基础类型：" + info.base.baseTypeName));
        baseLayout.addView(getTextView("工程分类：" + info.base.projCategoryParentName + " " + info.base.projCategoryName));
        baseLayout.addView(getTextView("建设类型：" + info.base.buildingTypeName));
        baseLayout.addView(getTextView("安全目标：" + info.base.safeGoals));
        baseLayout.addView(getTextView("质量目标：" + info.base.qualityGoals));
        baseLayout.addView(getTextView("开工日期：" + info.base.startDate));
        baseLayout.addView(getTextView("竣工日期：" + info.base.endDate));
        baseLayout.addView(getTextView("项目投资：" + info.base.projectInvestmentName));

        institutionLayout.addView(getTextView("建设单位：" + info.getUnitByType("105").unitName));
        institutionLayout.addView(getTextView("勘察单位：" + info.getUnitByType("107").unitName));
        institutionLayout.addView(getTextView("设计单位：" + info.getUnitByType("106").unitName));
        institutionLayout.addView(getTextView("监理单位：" + info.getUnitByType("108").unitName));
        institutionLayout.addView(getTextView("安监单位：" + info.getUnitByType("111").unitName));
        institutionLayout.addView(getTextView("质检单位：" + info.getUnitByType("112").unitName));

        unitLayout.addView(getTextView("名称：" + info.getUnitByType("110").unitName));
        unitLayout.addView(getTextView("资质等级：" + info.unitQualifications.grade));
        unitLayout.addView(getTextView("安全资格：" + info.unitQualifications.qualName));
        unitLayout.addView(getTextView("法人代表：" + info.getUnitByType("110").legalRepre));
        unitLayout.addView(getTextView("总工程师：" + info.getPersonByType("119").person));
        unitLayout.addView(getTextView("项目经理：" + info.getPersonByType("115").person));
        unitLayout.addView(getTextView("项目技术负责人：" + info.getPersonByType("118").person));

        queryFilePresenter.queryFile(info.base.projId);
    }

    private TextView getTextView(String content) {
        TextView textView = new TextView(this);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(0, 0, 0, DensityUtil.dp2px(4));
        textView.setLayoutParams(marginLayoutParams);
        textView.setPadding(DensityUtil.dp2px(10), 0, DensityUtil.dp2px(10), 0);
        textView.setTextColor(getResources().getColor(R.color.color_grey_333333));
        textView.setTextSize(14);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(content);
        return textView;
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
        adapter.loadData(fileEntities);
    }
}
