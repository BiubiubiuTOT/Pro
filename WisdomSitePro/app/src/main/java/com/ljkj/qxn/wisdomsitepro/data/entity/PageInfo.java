package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;


/**
 * 类描述：分页实体
 * 创建人：mhl
 * 创建时间：2017/9/18  下午 15:39
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PageInfo<T> {

    /**
     * 数据列表
     */
    List<T> list;

    /**
     * 数据总数
     */
    int total;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", total=" + total +
                '}';
    }
}
