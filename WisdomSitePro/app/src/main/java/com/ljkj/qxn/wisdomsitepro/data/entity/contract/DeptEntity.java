package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.ljkj.qxn.wisdomsitepro.data.ProItemEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptBean;

public class DeptEntity extends SectionEntity<DeptBean> {

    public DeptEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public DeptEntity(DeptBean bean) {
        super(bean);
    }
}
