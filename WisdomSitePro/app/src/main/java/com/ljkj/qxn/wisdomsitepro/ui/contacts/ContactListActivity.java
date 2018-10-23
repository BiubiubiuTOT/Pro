package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.util.ContactAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.util.ContactInfo;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.util.ContactsUtils;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.util.SideLetterBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

public class ContactListActivity extends BaseActivity implements OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.side_bar)
    SideLetterBar mSideLetterBar;
    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    @BindView(R.id.letter_overlay)
    TextView mOverlay;

    @BindView(R.id.et_search)
    EditText mSearchBox;
    @BindView(R.id.iv_search_clear)
    ImageView mClearBtn;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ArrayList<ContactInfo> mContactList;
    private ArrayList<ContactInfo> mSearchList;
    private ContactAdapter mContactAdapter;
    private ArrayList<ContactInfo> mChooseContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initClick();
    }

    @Override
    protected void initUI() {
        //设置标题
        tvTitle.setText("手机通讯录");

        mSideLetterBar.setOverlay(mOverlay);
    }

    @Override
    protected void initData() {
        mSearchList = new ArrayList<>();
        mContactList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mContactAdapter = new ContactAdapter(this, mContactList);
        mRecyclerView.setAdapter(mContactAdapter);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mContactList = ContactsUtils.getContactList(ContactListActivity.this);
                handler.sendEmptyMessage(0);
            }
        });

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mContactList = ContactsUtils.getContactList(ContactListActivity.this);
        handler.sendEmptyMessage(0);
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void initClick() {
        mSideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                scrollToLetter(letter);
            }
        });

        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchBox.setText("");
                mClearBtn.setVisibility(View.GONE);
            }
        });

        mSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchKey = s.toString().trim();
                if (mContactList.size() == 0) return;

                mSearchList.clear();
                searchContacts(searchKey);
                if (TextUtils.isEmpty(searchKey)) {
                    mClearBtn.setVisibility(View.GONE);
                    mSideLetterBar.setVisibility(View.VISIBLE);
                    mSearchList.clear();
                    mContactAdapter.setContactList(mContactList);
                } else {
                    mClearBtn.setVisibility(View.VISIBLE);
                    mContactAdapter.setContactList(mSearchList);
                    if (mSearchList == null || mSearchList.size() <= 0) {
                        mSideLetterBar.setVisibility(View.GONE);
                    } else {
                        mSideLetterBar.setVisibility(View.VISIBLE);
                    }
                }
                llNoData.setVisibility(mRecyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
            }
        });

        //联系人未获取到之前搜索不可用
        mSearchBox.setEnabled(false);

        mContactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ContactInfo info, int position) {
                List<String> phone = info.getPhone();
                if (phone.size() == 1)
                    callPhone(phone.get(0));
                else
                    selectPhone(phone);
            }
        });


    }

    private void selectPhone(List<String> phone) {
        final Context dialogContext = new ContextThemeWrapper(this,
                android.R.style.Theme_Light);
        final LayoutInflater dialogInflater = (LayoutInflater) dialogContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, phone) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = dialogInflater.inflate(
                            android.R.layout.simple_list_item_1, parent, false);
                }

                final TextView text1 = (TextView) convertView
                        .findViewById(android.R.id.text1);
                final String display = this.getItem(position);
                text1.setText(display);

                return convertView;
            }
        };

        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item = adapter.getItem(which);
                callPhone(item);
            }
        };

        new AlertDialog.Builder(this).setTitle("请选择电话拨号")
                .setSingleChoiceItems(adapter, 0, clickListener).
                setNegativeButton("取消", null).
                create()
                .show();

    }

    private void callPhone(final String phone) {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CALL_PHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phone));
                        startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问电话权限");
                    }
                })
                .start();
    }


    /**
     * 搜索联系人
     *
     * @param searchKey 搜索key
     */
    private void searchContacts(String searchKey) {
        for (ContactInfo info : mContactList) {
            if (ContactsUtils.searchContact(searchKey, info)) {
                mSearchList.add(info);
            }
        }
    }

    /**
     * 滑动到索引字母出
     */
    private void scrollToLetter(String letter) {
        for (int i = 0; i < mContactList.size(); i++) {
            if (TextUtils.equals(letter, mContactList.get(i).getLetter())) {
                ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                break;
            }
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mProgressBar == null) {
                return true;
            }
            mProgressBar.setVisibility(View.GONE);
            refreshLayout.finishRefresh();

            if (mContactList.size() > 0) {
                mSearchBox.setEnabled(true);
                mContactAdapter.setContactList(mContactList);
                mSideLetterBar.setVisibility(View.VISIBLE);
            }
            llNoData.setVisibility(mRecyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
            return true;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
