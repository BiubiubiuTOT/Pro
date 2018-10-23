package com.ljkj.qxn.wisdomsitepro.data.entity;

import org.json.JSONArray;

/**
 * 类描述：工程形象进度
 * 创建人：liufei
 * 创建时间：2018/3/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectProgressAddInfo extends ProjectProgressInfo {

    private JSONArray file;
    private String createUserId;
    private String createUserName;

    public JSONArray getFile() {
        return file;
    }

    public void setFile(JSONArray file) {
        this.file = file;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
