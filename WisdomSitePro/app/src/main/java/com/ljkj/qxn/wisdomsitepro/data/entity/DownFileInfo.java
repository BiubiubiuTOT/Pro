package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：政府端下发的附件
 * 创建人：lxx
 * 创建时间：2018/3/15 14:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DownFileInfo {
    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件路径
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DownFileInfo{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
