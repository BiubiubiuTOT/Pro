package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.message.NoticeContactContract;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeContactInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;
import com.ljkj.qxn.wisdomsitepro.presenter.message.GetNoticeContactPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.message.adapter.NoticeContactAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.CollectionUtils;

/**
 * 类描述：公告联系人
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeContactActivity extends BaseActivity implements NoticeContactContract.View, NoticeContactFragment.OnSelectedChangedListener {
    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_select_all)
    TextView selectAllText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_selected)
    TextView selectedText;

    @BindView(R.id.tv_sure)
    TextView sureText;

    private NoticeContactAdapter noticeContactAdapter;
    private GetNoticeContactPresenter presenter;

    public static void startActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, NoticeContactActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_contact);
    }

    @Override
    protected void initUI() {
        titleText.setText("公告范围");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        presenter = new GetNoticeContactPresenter(this, MessageModel.newInstance());
        addPresenter(presenter);
        noticeContactAdapter = new NoticeContactAdapter(null);
        recyclerView.setAdapter(noticeContactAdapter);

        noticeContactAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<NoticeContactInfo> items = noticeContactAdapter.getItem(position);
                if (items == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.tv_team:
                        if (noticeContactAdapter.isAllChecked(items)) {
                            for (NoticeContactInfo info : items) {
                                info.setChecked(false);
                            }
                        } else {
                            for (NoticeContactInfo info : items) {
                                info.setChecked(true);
                            }
                        }
                        noticeContactAdapter.notifyItemChanged(position);
                        updateSelectUI();
                        break;
                    case R.id.tv_subordinate:
                        showContactFragment(items);
                        break;
                    default:
                        break;
                }
            }
        });

        fetchData();
    }

    private void fetchData() {
        if (GetNoticeContactPresenter.getCacheList() == null) {
            presenter.getNoticeContact(UserManager.getInstance().getProjectId());
        } else {
//            List<List<NoticeContactInfo>> tempList = new ArrayList<>(GetNoticeContactPresenter.getCacheList());
            noticeContactAdapter.setNewData(CollectionUtils.deepCopy(GetNoticeContactPresenter.getCacheList()));
            updateSelectUI();
        }
    }


    @OnClick({R.id.tv_back, R.id.tv_select_all, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.tv_select_all:
                selectAll(!isAllSelected());

                break;
            case R.id.tv_sure:
                //TODO
                GetNoticeContactPresenter.setCacheList(noticeContactAdapter.getData());
                ArrayList<NoticeContactInfo> selectList = new ArrayList<>();
                for (List<NoticeContactInfo> list : noticeContactAdapter.getData()) {
                    for (NoticeContactInfo info : list) {
                        if (info.isChecked()) {
                            selectList.add(info);
                        }
                    }
                }
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("result", selectList);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    private boolean isAllSelected() {
        List<List<NoticeContactInfo>> datas = noticeContactAdapter.getData();
        for (List<NoticeContactInfo> list : datas) {
            for (NoticeContactInfo info : list) {
                if (!info.isChecked()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void selectAll(boolean all) {
        List<List<NoticeContactInfo>> datas = noticeContactAdapter.getData();
        for (List<NoticeContactInfo> list : datas) {
            for (NoticeContactInfo info : list) {
                info.setChecked(all);
            }
        }
        noticeContactAdapter.notifyDataSetChanged();
        updateSelectUI();
    }

    private void showContactFragment(List<NoticeContactInfo> items) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        NoticeContactFragment fragment = (NoticeContactFragment) getSupportFragmentManager().findFragmentByTag(NoticeContactFragment.TAG);
        if (fragment == null) {
            fragment = (NoticeContactFragment) Fragment.instantiate(this, NoticeContactFragment.TAG);
            fragmentTransaction
                    .add(R.id.container, fragment, NoticeContactFragment.TAG)
                    .commit();
        } else {
            if (fragment.isHidden()) {
                fragmentTransaction.show(fragment).commit();
            }
        }
        fragment.setData(items);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(NoticeContactFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            if (fragment.isHidden()) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
            }
        }

    }

    @Override
    public void showNoticeContact(List<List<NoticeContactInfo>> datas) {
        GetNoticeContactPresenter.setCacheList(CollectionUtils.deepCopy(datas));
        noticeContactAdapter.setNewData(datas);
        updateSelectUI();
    }

    private void updateSelectUI() {
        List<List<NoticeContactInfo>> datas = noticeContactAdapter.getData();
        int total = 0;
        int selectCount = 0;
        for (List<NoticeContactInfo> list : datas) {
            total += list.size();
            for (NoticeContactInfo info : list) {
                if (info.isChecked()) {
                    selectCount++;
                }
            }
        }
        selectedText.setText("已选择：" + selectCount + "人");
        sureText.setText("确定（" + selectCount + "/" + total + "）");
        if (isAllSelected()) {
            selectAllText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.ic_circle_select), null, null, null);
        } else {
            selectAllText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.ic_circle_unselected), null, null, null);
        }
    }

    @Override
    public void selectedChanged() {
        updateSelectUI();
        noticeContactAdapter.notifyDataSetChanged();
    }

}
