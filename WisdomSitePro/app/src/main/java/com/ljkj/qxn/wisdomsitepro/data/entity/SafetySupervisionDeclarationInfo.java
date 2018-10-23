package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：安全监督申报
 * 创建人：mhl
 * 创建时间：2018/3/13 14:20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafetySupervisionDeclarationInfo {

    /**
     * 申报日期
     */
    private String applyTime;

    /**
     * 附件列表
     */
    private AttachmentBean attachment;

    /**
     * 申报附件id
     */
    private String fileId;

    /**
     * 监理单位
     */
    private String jlUnit;

    /**
     * 建设单位
     */
    private String jsUnit;

    /**
     * 建筑面积
     */
    private String jzmj;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 施工单位
     */
    private String sgUnit;

    /**
     * 申报状态  :	01已通过:,02:申报中,03:被驳回
     */
    private String status;

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public AttachmentBean getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentBean attachment) {
        this.attachment = attachment;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getJlUnit() {
        return jlUnit;
    }

    public void setJlUnit(String jlUnit) {
        this.jlUnit = jlUnit;
    }

    public String getJsUnit() {
        return jsUnit;
    }

    public void setJsUnit(String jsUnit) {
        this.jsUnit = jsUnit;
    }

    public String getJzmj() {
        return jzmj;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSgUnit() {
        return sgUnit;
    }

    public void setSgUnit(String sgUnit) {
        this.sgUnit = sgUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class AttachmentBean {


        /**
         * 贵州省建设工程安全监督注册登记表
         */
        private List<EnclosureInfo> fileList = new ArrayList<EnclosureInfo>();

        /**
         * 危险性较大的分部分项工程清单
         */
        private List<EnclosureInfo> file_wxqdList = new ArrayList<EnclosureInfo>();

        /**
         * 贵州省建设工程施工安全监督交底告知书
         */
        private List<EnclosureInfo> jdgzs_fileList = new ArrayList<EnclosureInfo>();

        /**
         * 施工安全监督工作方案
         */
        private List<EnclosureInfo> work_planList = new ArrayList<EnclosureInfo>();


        public List<EnclosureInfo> getFileList() {
            return fileList;
        }

        public void setFileList(List<EnclosureInfo> fileList) {
            this.fileList = fileList;
        }

        public List<EnclosureInfo> getFile_wxqdList() {
            return file_wxqdList;
        }

        public void setFile_wxqdList(List<EnclosureInfo> file_wxqdList) {
            this.file_wxqdList = file_wxqdList;
        }

        public List<EnclosureInfo> getJdgzs_fileList() {
            return jdgzs_fileList;
        }

        public void setJdgzs_fileList(List<EnclosureInfo> jdgzs_fileList) {
            this.jdgzs_fileList = jdgzs_fileList;
        }

        public List<EnclosureInfo> getWork_planList() {
            return work_planList;
        }

        public void setWork_planList(List<EnclosureInfo> work_planList) {
            this.work_planList = work_planList;
        }
    }
}
