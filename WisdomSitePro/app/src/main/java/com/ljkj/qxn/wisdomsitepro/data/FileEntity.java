package com.ljkj.qxn.wisdomsitepro.data;

/**
 * 类描述：v2.0文件实体
 * 创建人：lxx
 * 创建时间：2018/9/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FileEntity extends BaseEntity {

    /** 文件扩展名 */
    public String fileExt;

    /** 文件ID */
    public String fileId;

    /** 文件名 */
    public String fileName;

    /** 文件大小（单位：字节） */
    public String fileSize;

    /**
     * 文件类型，各文件类型定义在
     *
     * @see FileType
     */
    public String type;

    /** 项目code */
    public String projCode;

    /** 项目id */
    public String projId;


    public String relId;

    public String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjCode() {
        return projCode;
    }

    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public FileEntity(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public FileEntity() {
    }

    public FileEntity(String fileName) {
        this.fileName = fileName;
    }


    public boolean isImageFile() {
        return "jpg".equals(fileExt) || "jpeg".equals(fileExt) || "png".equals(fileExt);
    }


}
