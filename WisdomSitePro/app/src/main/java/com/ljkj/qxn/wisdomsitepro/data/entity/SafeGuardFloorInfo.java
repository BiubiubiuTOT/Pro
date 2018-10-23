package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：安全防护楼层详情
 * 创建人：rjf
 * 创建时间：2018/3/13 17:35.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardFloorInfo {

    /**
     * 图片路径
     */
    private String path;

    /**
     * 安全防护说明
     */
    private String aqfhsm;

    /**
     * 图片ID
     */
    private String id;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAqfhsm() {
        return aqfhsm;
    }

    public void setAqfhsm(String aqfhsm) {
        this.aqfhsm = aqfhsm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
