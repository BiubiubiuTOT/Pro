package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeSystemPdfContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeSystemPdfInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeSystemPdfPresenter;

import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：安全体系pdf
 * 创建人：liufei
 * 创建时间：2018/3/20 17:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeSystemPdfFragment extends BaseFragment implements SafeSystemPdfContract.View {

    public static final String TAG = SafeSystemPdfFragment.class.getName();

    public static final int TYPE_MANAGER = 0;
    public static final int TYPE_RULE = 1;

    private int type;
    private SafeSystemPdfPresenter pdfPresenter;

    public static SafeSystemPdfFragment newInstance(int type) {
        SafeSystemPdfFragment fragment = new SafeSystemPdfFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safe_system_pdf, container, false);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
        return view;
    }

    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {
        pdfPresenter = new SafeSystemPdfPresenter(this, SafeModel.newInstance());
        addPresenter(pdfPresenter);
//        pdfPresenter.getSafeSystemPdf(UserManager.getInstance().getProjectId(), type);
    }

    @Override
    public void showSafeSystemPdf(SafeSystemPdfInfo data) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, H5Helper.getPdfFragment(data.getPath()))
                .commit();
    }
}
