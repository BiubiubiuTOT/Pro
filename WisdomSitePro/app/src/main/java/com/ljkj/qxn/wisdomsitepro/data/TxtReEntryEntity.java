package com.ljkj.qxn.wisdomsitepro.data;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/3 9:50
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class TxtReEntryEntity extends BaseEntity {

    public String entryName;

    public String title;

    public TxtReEntryEntity(String entryName, String title) {
        this.entryName = entryName;
        this.title = title;
    }
}
