package com.ljkj.qxn.wisdomsitepro.data;

import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/3 13:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ImageListEntity extends BaseEntity {

    public List<String> listImages = new ArrayList<String>();

    public List<FileEntity> listEnclosureInfos = new ArrayList<FileEntity>();

    public ImageListEntity(List<FileEntity> listEnclosureInfos) {
        this.listEnclosureInfos = listEnclosureInfos;
    }
}
