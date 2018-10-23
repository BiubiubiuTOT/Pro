package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafetySupWorkplanContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafetySupWorkplanInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafetySupWorkplanPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.FilesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;


/**
 * 类描述：安全监督工作方案
 * 创建人：mhl
 * 创建时间：2018/2/9 13:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafetySupervisionWorkPlanActivity extends BaseActivity implements SafetySupWorkplanContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_base_step_count)
    TextView tvBaseStepCount;

    @BindView(R.id.tv_main_count)
    TextView tvMainCount;

    @BindView(R.id.tv_descri_count)
    TextView tvDescriCount;

    @BindView(R.id.tv_progress_record)
    TextView tvProgressRecord;

    @BindView(R.id.tv_suggestion_content)
    TextView tvSuggestionContent;

    @BindView(R.id.rl_files)
    RecyclerView rlFiles;

    FilesAdapter filesAdapter;

    private SafetySupWorkplanPresenter safetySupWorkplanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_supervision_work_plan);
    }

    @Override
    protected void initUI() {

        tvTitle.setText("安全监督工作方案");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlFiles.setLayoutManager(linearLayoutManager);
        rlFiles.setAdapter(filesAdapter = new FilesAdapter(this, new ArrayList<BaseEntity>()));
        rlFiles.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {

        safetySupWorkplanPresenter = new SafetySupWorkplanPresenter(this, SafeModel.newInstance());
        addPresenter(safetySupWorkplanPresenter);
        safetySupWorkplanPresenter.viewSafetySupWorkplan(UserManager.getInstance().getProjectId());
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showSafetySupWorkplan(SafetySupWorkplanInfo data) {

        if (data != null) {
            filLData(data);
        }
    }

    private void filLData(SafetySupWorkplanInfo data) {

        tvBaseStepCount.setText(data.getJcnum() + "");
        tvMainCount.setText(data.getSgnum() + "");
        tvDescriCount.setText(data.getZsnum() + "");
        tvProgressRecord.setText(data.getPlan());
        tvSuggestionContent.setText(data.getQtyq());
    }
}
