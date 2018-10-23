package com.ljkj.qxn.wisdomsitepro.data;

/**
 * 类描述
 * 创建人：rjf
 * 创建时间：2018/3/13 16:20.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardEntity {
    private String buildingName;
    private String buildingNum;

    public SafeGuardEntity(String buildingName, String buildingNum) {
        this.buildingName = buildingName;
        this.buildingNum = buildingNum;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getBuildingNum() {
        return buildingNum;
    }
}
