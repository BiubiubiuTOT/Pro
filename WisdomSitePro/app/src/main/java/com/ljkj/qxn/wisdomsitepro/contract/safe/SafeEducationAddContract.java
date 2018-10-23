package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：新增安全教育
 * 创建人：mhl
 * 创建时间：2018/3/15 15:40
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SafeEducationAddContract {

    interface View extends BaseView {
        void showAddSafeEduResult();
        void showAddSafeTechnology();
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void addSafeEdu(String presentationName, String presentationDate, String presentationInfo, List<FileEntity> file,
                                        String trainer, List<SecurityEduTechPerson> persons, int isReport, String projId, String projCode, String createUserId,
                                        String createUserName);

        public abstract void addSafeTechnology(String presentationName, String presentationDate, String presentationPosition,
                                               String presentationPerson, String safetyOfficer, String teamLeader, List<SecurityEduTechPerson> team,
                                               int isReport, String presentationInfo, List<FileEntity> file, String projId,
                                               String projCode, String createUserId, String createUserName);
    }

}
