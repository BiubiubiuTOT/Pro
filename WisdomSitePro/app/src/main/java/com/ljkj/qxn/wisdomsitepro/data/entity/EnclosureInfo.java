package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;

/**
 * 类描述：附件对象
 * 创建人：mhl
 * 创建时间：2018/2/28 9:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class EnclosureInfo extends BaseEntity {

    /**
     * 附件对象ID
     */
    private String id;

    /**
     * 附件物理路径
     */
    @SerializedName("realpath")
    private String path;

    /**
     * 附件名称
     */
    @SerializedName("attachmenttitle")
    private String title;

    /**
     * 扩展名
     */
    private String extend;

    /**
     * swf格式路径
     */
    private String swfpath;


    /**
     * 类型来源 ： 0企业端， 1政府端
     */
    private int typeSource;


    /***政府端附件***/

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

    public int getTypeSource() {
        return typeSource;
    }

    public void setTypeSource(int typeSource) {
        this.typeSource = typeSource;
    }

    public EnclosureInfo(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getSwfpath() {
        return swfpath;
    }

    public void setSwfpath(String swfpath) {
        this.swfpath = swfpath;
    }

    @Override
    public String toString() {
        return "EnclosureInfo{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", extend='" + extend + '\'' +
                ", swfpath='" + swfpath + '\'' +
                '}';
    }
}
