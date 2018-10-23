package com.ljkj.qxn.wisdomsitepro.Utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.WisdomPopupWindow;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ItemSingleTextAdapter;

import java.util.List;

import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.util.DisplayUtils;

/**
 * 类描述：SelectWindowHelper
 * 创建人：lxx
 * 创建时间：2018/5/19 14:45
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SelectWindowHelper {


    public static void showSingleListWindow(Context context, final List<String> items, View parentView, final RadioButton button, final OnItemSelectListener onItemSelectListener) {
        ItemSingleTextAdapter adapter = new ItemSingleTextAdapter(context);
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_window_recycler, null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.loadData(items);

        final WisdomPopupWindow popupWindow = new WisdomPopupWindow.Builder(context)
                .setView(contentView)
                .setOutsideTouchable(true)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                .setViewOnclickListener(null)
                .build();

        adapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                if (onItemSelectListener != null) {
                    onItemSelectListener.onItemSelect(items.get(position), position);
                }
                popupWindow.dismiss();
            }
        });

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                button.setChecked(false);
            }
        });

        int[] location = new int[2];
        parentView.getLocationOnScreen(location);
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, 0, parentView.getHeight() + location[1]);
    }

    /**
     * 右上角弹出菜单
     *
     * @param context     context
     * @param contentView 内容View
     * @param parentView  parentView
     * @param xOffset     x轴偏移
     * @param yOffset     y轴偏移
     */
    public static void showTopRightMenu(Context context, View contentView, View parentView, int xOffset, int yOffset) {
        final WisdomPopupWindow popupWindow = new WisdomPopupWindow.Builder(context)
                .setView(contentView)
                .setOutsideTouchable(true)
                .setAnimationStyle(R.style.pop_right_top)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(null)
                .build();

        int[] location = new int[2];
        parentView.getLocationOnScreen(location);

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int contentWidth = contentView.getMeasuredWidth();
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, DisplayUtils.getScreenWidth() - contentWidth + xOffset, parentView.getHeight() + location[1] + yOffset);

//        SelectWindowHelper.showTopRightMenu(getContext(), contentView, tvRight, -30, 0);
    }


    public interface OnItemSelectListener {
        void onItemSelect(String item, int position);
    }
}
