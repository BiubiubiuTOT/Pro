package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;

import java.util.List;
import java.util.Map;

public interface DataInterface {

    void putData(SiteStationRecorderManageDetailInfo info, Map<String, List<String>> map);

    boolean checkData();

    void updateUIForDetail(SiteStationRecorderManageDetailInfo info);

    void showFiles(List<FileEntity> fileEntities);

}
