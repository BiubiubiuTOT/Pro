package com.ljkj.qxn.wisdomsitepro.data;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 类描述：应用中心实体
 * 创建人：lxx
 * 创建时间：2018/6/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ApplicationItemEntity extends SectionEntity<ProItemEntity> {
    /** 外部应用：青易筑 */
    public static final int TYPE_QING_YI_ZHU = 100;
    /** 外部应用：蓝领圈 */
    public static final int TYPE_LAN_LING_QUAN = 101;
    /** 环境监测 */
    public static final int TYPE_ENVIRONMENT = 102;

    public static final int BIM_TEMP = 103;

    public ApplicationItemEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ApplicationItemEntity(ProItemEntity proItemEntity) {
        super(proItemEntity);
    }

}
