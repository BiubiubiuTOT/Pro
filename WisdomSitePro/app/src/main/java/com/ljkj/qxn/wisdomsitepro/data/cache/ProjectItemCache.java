package com.ljkj.qxn.wisdomsitepro.data.cache;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.ProItemEntity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ConstructionSiteActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ManagerPeopleActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProjectEffectActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProjectProgressActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/1 10:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectItemCache {


    public static List<List<ProItemEntity>> listProItems = new ArrayList<>();

    static {

        List<ProItemEntity> list = new ArrayList<ProItemEntity>();
        list.add(new ProItemEntity("工程概况牌", R.mipmap.ic_project_item01, null, -1));
        list.add(new ProItemEntity("管理人员名单", R.mipmap.ic_project_item02, ManagerPeopleActivity.class, -1));
        list.add(new ProItemEntity("消防保卫牌", R.mipmap.ic_project_item04, null, 1));
        list.add(new ProItemEntity("安全生产牌", R.mipmap.ic_project_item05, null, 2));
//        list.add(new ProItemEntity("建筑经济指标", R.mipmap.ic_project_item03, null, -1));


        list.add(new ProItemEntity("文明施工牌", R.mipmap.ic_project_item06, null, 3));
        list.add(new ProItemEntity("现场施工图", R.mipmap.ic_project_item07, ConstructionSiteActivity.class, -1));
        list.add(new ProItemEntity("工程形象进度", R.mipmap.ic_project_item08, ProjectProgressActivity.class, -1));
        list.add(new ProItemEntity("工程效果图", R.mipmap.ic_project_item09, ProjectEffectActivity.class, -1));

        List<ProItemEntity> listSe = new ArrayList<ProItemEntity>();
//        listSe.add(new ProItemEntity("环境保护牌", R.mipmap.ic_project_item10, null, 4));
//        listSe.add(new ProItemEntity("企业文化牌", R.mipmap.ic_project_item11, null, 5));
//        listSe.add(new ProItemEntity("五方责任主体", R.mipmap.ic_project_item14, null, -1));
        listSe.add(new ProItemEntity("维权告示牌", R.mipmap.ic_project_item13, null, 6));
//        listSe.add(new ProItemEntity("节能公示牌", R.mipmap.ic_project_item12, null, -1));

        listProItems.add(list);
        listProItems.add(listSe);
    }
}
