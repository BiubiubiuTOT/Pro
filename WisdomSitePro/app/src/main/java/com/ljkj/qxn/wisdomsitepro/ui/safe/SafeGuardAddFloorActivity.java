package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardAddContract;
import com.ljkj.qxn.wisdomsitepro.data.SafeGuardEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardEditFloorInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeGuardAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeGuardFloorAddAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全防护新增楼层
 * 创建人：rjf
 * 创建时间：2018/3/13 12:59.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardAddFloorActivity extends BaseActivity implements SafeGuardAddContract.View {
    private static final String KEY_LOU_ORDER = "louOrder";
    private static final String KEY_UNDER_FLOOR_COUNT = "underFloorCount";
    private static final String KEY_FLOOR_COUNT = "floorCount";
    private static final String KEY_SPLIT_FLOOR_COUNT = "splitFloorCount";

    public static final int AVERAGE_REFUGE_FLOOR = 15;
    private static final int MAX_FLOORS = 33;
    private static final int MAX_UNDER_FLOORS = 5;

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_refuge_floors)
    TextView tvRefugeFloors;
    @BindView(R.id.ll_refuge_floors)
    LinearLayout llRefugeFloors;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_set_refuge_floor)
    LinearLayout llSetRefugeFloor;
    @BindView(R.id.bt_submit_add_floor)
    Button btSubmitAddFloor;
    @BindView(R.id.item_under_floor)
    InputItemView itemUnderFloor;
    @BindView(R.id.item_floors)
    InputItemView itemFloors;
    @BindView(R.id.item_split_floor)
    InputItemView itemSplitFloor;

    private String louOrder;
    private int underFloorCount;
    private int floorCount;
    private int splitFloorCount;

    private SafeGuardFloorAddAdapter adapter;

    private List<String> refugeFloorList = new ArrayList<>();

    List<SafeGuardEntity> data = new ArrayList<>();
    private List<String> booleanStrList = new ArrayList<>();

    private List<String> floorList = new ArrayList<>();

    private List<String> underFloorList = new ArrayList<>();

    SafeGuardAddPresenter presenter;

    public static void startActivity(Context context, String louOrder, int underFloorCount, int floorCount, int splitFloorCount) {
        Intent intent = new Intent(context, SafeGuardAddFloorActivity.class);
        intent.putExtra(KEY_LOU_ORDER, louOrder);
        intent.putExtra(KEY_UNDER_FLOOR_COUNT, underFloorCount);
        intent.putExtra(KEY_FLOOR_COUNT, floorCount);
        intent.putExtra(KEY_SPLIT_FLOOR_COUNT, splitFloorCount);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_guard_add_floor);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("新增楼层");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SafeGuardFloorAddAdapter(this));

        this.louOrder = getIntent().getStringExtra(KEY_LOU_ORDER);
        this.underFloorCount = getIntent().getIntExtra(KEY_UNDER_FLOOR_COUNT, 0);
        this.floorCount = getIntent().getIntExtra(KEY_FLOOR_COUNT, 0);
        this.splitFloorCount = getIntent().getIntExtra(KEY_SPLIT_FLOOR_COUNT, 0);

        if (underFloorCount >= MAX_UNDER_FLOORS) {
            itemUnderFloor.setVisibility(View.GONE);
        }

        if (splitFloorCount >= 1) {
            itemSplitFloor.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {
        booleanStrList.add("是");
        booleanStrList.add("否");

        floorList = initOptionString(MAX_FLOORS - floorCount);
        underFloorList = initOptionString(MAX_UNDER_FLOORS - underFloorCount);

        presenter = new SafeGuardAddPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
    }

    @OnClick({R.id.tv_back, R.id.item_under_floor, R.id.item_floors, R.id.item_split_floor, R.id.ll_refuge_floors, R.id.bt_submit_add_floor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_under_floor:
                showOptionDialog(underFloorList, itemUnderFloor);
                break;
            case R.id.item_floors:
                if (floorCount >= MAX_FLOORS) {
                    showError("您已添加最大可建立层数");
                    return;
                }
                showOptionDialog(floorList, itemFloors);
                break;
            case R.id.item_split_floor:
                showOptionDialog(booleanStrList, itemSplitFloor);
                break;
            case R.id.ll_refuge_floors:
                break;
            case R.id.bt_submit_add_floor:
                int underFloors = 0;
                int floors = 0;
                int splitFloor = 0;

                if (TextUtils.isEmpty(itemUnderFloor.getContent()) && TextUtils.isEmpty(itemFloors.getContent()) &&
                        TextUtils.isEmpty(itemSplitFloor.getContent())) {
                    showError("请至少选择其中一项");
                    return;
                }

                if (itemUnderFloor.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(itemUnderFloor.getContent())) {
                    String underFloorStr = itemUnderFloor.getContent();
                    underFloors = Integer.valueOf(underFloorStr);
                }

                if (floorCount < MAX_FLOORS && !TextUtils.isEmpty(itemFloors.getContent())) {
                    String floorStr = itemFloors.getContent();
                    floors = Integer.valueOf(floorStr);
                }

                if (itemSplitFloor.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(itemSplitFloor.getContent())) {
                    String isSpiltFloor = itemSplitFloor.getContent();
                    if (booleanStrList.get(0).equals(isSpiltFloor)) {
                        splitFloor = 1;
                    }
                }

                presenter.addFloor(louOrder, floors, splitFloor, underFloors, UserManager.getInstance().getProjectId());
                break;
        }
    }

    public List<String> initOptionString(int maxValue) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= maxValue; i++) {
            result.add(String.valueOf(i));
        }

        return result;
    }

    @Override
    public void showAddResult() {
        showError("添加楼层成功");
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
