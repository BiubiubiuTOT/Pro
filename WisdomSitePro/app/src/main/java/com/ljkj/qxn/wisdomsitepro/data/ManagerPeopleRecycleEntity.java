package com.ljkj.qxn.wisdomsitepro.data;

import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;

import java.util.List;

/**
 * 类描述：管理人员 列表
 * 创建人：liufei
 * 创建时间：2018/1/18 15:36
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ManagerPeopleRecycleEntity extends BaseEntity {

    private String title;

    private List<ManagerPeopleInfo.ManagerPersonBean> childList;

    public ManagerPeopleRecycleEntity(String title, List<ManagerPeopleInfo.ManagerPersonBean> childList) {
        this.title = title;
        this.childList = childList;
    }

    public String getTitle() {
        return title;
    }

    public List<ManagerPeopleInfo.ManagerPersonBean> getChildList() {
        return childList;
    }
}
