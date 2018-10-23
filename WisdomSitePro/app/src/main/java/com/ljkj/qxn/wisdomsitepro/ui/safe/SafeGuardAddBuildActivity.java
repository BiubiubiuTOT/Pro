package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardEditFloorInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeGuardAddPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全防护新增楼栋
 * 创建人：rjf
 * 创建时间：2018/3/13 10:56.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardAddBuildActivity extends BaseActivity implements SafeGuardAddContract.View {
    private static final String KEY_BUILD_NAME = "buildName";
    private static final int MAX_OPTION_BUILDING = 10;

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_building_name)
    EditText etBuildingName;

    @BindView(R.id.bt_submit_add_build)
    Button btSubmitAddBuild;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.comDivider1)
    View comDivider1;

    @BindView(R.id.rl_no_build)
    RelativeLayout rlNoBuild;

    SafeGuardAddPresenter presenter;
    @BindView(R.id.item_select_building_num)
    InputItemView itemSelectBuildingNum;

    private String buildName;

    private List<String> buildNumList = new ArrayList<>();

    public static void startActivity(Context context, String buildName) {
        Intent intent = new Intent(context, SafeGuardAddBuildActivity.class);
        intent.putExtra(KEY_BUILD_NAME, buildName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_guard_add_build);
        ButterKnife.bind(this);

    }

    @Override
    protected void initUI() {
        tvTitle.setText("新增楼栋");
        this.buildName = getIntent().getStringExtra(KEY_BUILD_NAME);
        if (!TextUtils.isEmpty(buildName)) {
            etBuildingName.setText(buildName);
            etBuildingName.setEnabled(false);
        }
    }

    @Override
    protected void initData() {
        presenter = new SafeGuardAddPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);

        for (int i = 1; i <= MAX_OPTION_BUILDING; i++) {
            buildNumList.add(String.valueOf(i));
        }
    }

    @OnClick({R.id.tv_back, R.id.item_select_building_num, R.id.bt_submit_add_build})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_select_building_num:
                showOptionDialog(buildNumList, itemSelectBuildingNum);
                break;
            case R.id.bt_submit_add_build:

                String buildingName = etBuildingName.getText().toString().trim();
                if (TextUtils.isEmpty(buildingName)) {
                    showError("请填写楼盘名称！");
                    return;
                }

                if (TextUtils.isEmpty(itemSelectBuildingNum.getContent())) {
                    showError("请选择楼栋数量！");
                    return;
                }

                try {
                    int buildingNum = Integer.valueOf(itemSelectBuildingNum.getContent());
                    presenter.addBuilding(buildingName, buildingNum, UserManager.getInstance().getProjectId());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void showAddResult() {
        showError("添加楼栋成功");
        finish();
    }

    @Override
    public void showAddResult(SafeGuardEditFloorInfo info) {

    }

    private void showOptionDialog(final List<String> items, final InputItemView inputItemView) {
        if (isFastDoubleClick()) {
            return;
        }
        int select = inputItemView.getTag() != null ? (int) inputItemView.getTag() : items.size() / 2;
        PickerDialogHelper.showPickerOption(this, items, select, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputItemView.setTag(options1);
                inputItemView.setContent(items.get(options1));
            }
        });
    }
}
