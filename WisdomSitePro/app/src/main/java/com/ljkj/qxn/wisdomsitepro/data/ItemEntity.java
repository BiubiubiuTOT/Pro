package com.ljkj.qxn.wisdomsitepro.data;

import android.app.Activity;

/**
 * 类描述：Item
 * 创建人：mhl
 * 创建时间：2018/2/1 10:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ItemEntity {

    /**
     * 名称
     */
    public String name;

    /**
     * 图片资源Id
     */
    public int resId;

    /**
     * 对应跳转Activity
     */
    public Class<? extends Activity> clazz;

    public ItemEntity(String name, int resId, Class clazz) {

        this.name = name;
        this.resId = resId;
        this.clazz = clazz;
    }
}
