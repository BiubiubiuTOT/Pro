package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeContactInfo;
import com.ljkj.qxn.wisdomsitepro.ui.message.adapter.NoticeContactSelectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：公告联系人选择Fragment
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeContactFragment extends BaseFragment {
    public static final String TAG = NoticeContactFragment.class.getName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_select_all)
    TextView selectAllText;

    private NoticeContactSelectAdapter adapter;
    private List<NoticeContactInfo> items;
    private OnSelectedChangedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnSelectedChangedListener) {
            this.listener = (OnSelectedChangedListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_contact, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter = new NoticeContactSelectAdapter(null));
    }

    @Override
    protected void initData() {
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeContactInfo info = (NoticeContactInfo) adapter.getItem(position);
                TextView personText = (TextView) adapter.getViewByPosition(position, R.id.tv_person);
                if (info == null || personText == null) {
                    return;
                }
                if (info.isChecked()) {
                    info.setChecked(false);
                    personText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
                } else {
                    info.setChecked(true);
                    personText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
                }

                if (isAllChecked()) {
                    selectAllText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.mipmap.ic_circle_select), null, null, null);
                } else {
                    selectAllText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.mipmap.ic_circle_unselected), null, null, null);
                }

                if (listener != null) {
                    listener.selectedChanged();
                }
            }
        });

        adapter.setNewData(items);
//        test();
    }

    public void setData(List<NoticeContactInfo> items) {
        this.items = items;
        if (adapter != null) {
            adapter.setNewData(items);
        }
    }

    @OnClick({R.id.tv_select_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_all:
                if (isAllChecked()) {
                    selectAllText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.mipmap.ic_circle_unselected), null, null, null);
                    for (NoticeContactInfo info : adapter.getData()) {
                        info.setChecked(false);
                    }
                } else {
                    selectAllText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.mipmap.ic_circle_select), null, null, null);
                    for (NoticeContactInfo info : adapter.getData()) {
                        info.setChecked(true);
                    }
                }
                adapter.notifyDataSetChanged();
                if (listener != null) {
                    listener.selectedChanged();
                }
                break;
            default:
                break;
        }
    }

    private boolean isAllChecked() {
        for (NoticeContactInfo info : adapter.getData()) {
            if (!info.isChecked()) {
                return false;
            }
        }
        return true;
    }

    public interface OnSelectedChangedListener {
        void selectedChanged();
    }

    ///////////////////////////////
    void test() {
        List<NoticeContactInfo> list = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            list.add(new NoticeContactInfo());
        }
        adapter.setNewData(list);
    }

}
