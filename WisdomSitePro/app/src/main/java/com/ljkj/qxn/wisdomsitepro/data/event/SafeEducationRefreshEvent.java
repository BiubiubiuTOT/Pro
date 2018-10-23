package com.ljkj.qxn.wisdomsitepro.data.event;

/**
 * 类描述：安全教育刷新事件
 * 创建人：lxx
 * 创建时间：2018/7/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeEducationRefreshEvent {
    public static final int EDUCATION = 1;
    public static final int TECHNOLOGY = 2;

    public int type;

    public SafeEducationRefreshEvent(int type) {
        this.type = type;
    }
}
