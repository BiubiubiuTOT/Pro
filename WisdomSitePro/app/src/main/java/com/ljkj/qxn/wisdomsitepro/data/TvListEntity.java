package com.ljkj.qxn.wisdomsitepro.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：附件实体
 * 创建人：mhl
 * 创建时间：2018/2/3 9:57
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class TvListEntity extends BaseEntity {

    public String mark;

    public List<TvListItemEntity> tvList = new ArrayList<TvListItemEntity>();

    public TvListEntity(List<TvListItemEntity> tvList) {
        this.tvList = tvList;
    }

    public TvListEntity(String mark, List<TvListItemEntity> tvList) {
        this.mark = mark;
        this.tvList = tvList;
    }
}
