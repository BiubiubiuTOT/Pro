package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

/**
 * 类描述：添加成员所需信息
 * 创建人：rjf
 * 创建时间：2018/7/3 11:18.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class MemberAddInfo {
    private String address; //	家庭住址	string	选传
    private String age; //年龄	string	选传
    private String birthday; //出生日期	string	类型为Date，选传
    private String certificateCode; //证书编号	string	选传
    private String certificateDateBegin; //证书有效日期开始	string	类型为Date，选传
    private String certificateDateEnd; //证书有效日期截止	string	类型为Date，选传
    private String certificateName; //	证书名称	string	选传
    private String contactName; //	紧急联系人姓名	string	选传
    private String contactPhone; //	紧急联系人电话	string	选传
    private String degree; //	文化程度	string	选传
    private String departid; //	部门或班组id	string	必传
    private String duty; //	职务id	string	必传
    private String id; //	成员id	string	当编辑的时候必传
    private String idCard; //	身份证号	string	必传
    private String medicalHistory; //	是否重大病史	string	选传
    private String national; //	民族	string	选传
    private String photo; //	照片	string	照片
    private String proId; //	项目id	string	必传
    private String realName; //	成员姓名	string	必传
    private String sex; //	性别	string	选传
    private String userName; //	手机号	string	必传
    private String userType; //	人员类型	string	必传
    private String workDateBegin; //	开始工作日期	string	类型为Date，

    public String getAddress() {
        return address;
    }

    public MemberAddInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAge() {
        return age;
    }

    public MemberAddInfo setAge(String age) {
        this.age = age;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public MemberAddInfo setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public MemberAddInfo setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
        return this;
    }

    public String getCertificateDateBegin() {
        return certificateDateBegin;
    }

    public MemberAddInfo setCertificateDateBegin(String certificateDateBegin) {
        this.certificateDateBegin = certificateDateBegin;
        return this;
    }

    public String getCertificateDateEnd() {
        return certificateDateEnd;
    }

    public MemberAddInfo setCertificateDateEnd(String certificateDateEnd) {
        this.certificateDateEnd = certificateDateEnd;
        return this;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public MemberAddInfo setCertificateName(String certificateName) {
        this.certificateName = certificateName;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public MemberAddInfo setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public MemberAddInfo setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public String getDegree() {
        return degree;
    }

    public MemberAddInfo setDegree(String degree) {
        this.degree = degree;
        return this;
    }

    public String getDepartid() {
        return departid;
    }

    public MemberAddInfo setDepartid(String departid) {
        this.departid = departid;
        return this;
    }

    public String getDuty() {
        return duty;
    }

    public MemberAddInfo setDuty(String duty) {
        this.duty = duty;
        return this;
    }

    public String getId() {
        return id;
    }

    public MemberAddInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public MemberAddInfo setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public MemberAddInfo setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
        return this;
    }

    public String getNational() {
        return national;
    }

    public MemberAddInfo setNational(String national) {
        this.national = national;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public MemberAddInfo setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getProId() {
        return proId;
    }

    public MemberAddInfo setProId(String proId) {
        this.proId = proId;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public MemberAddInfo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public MemberAddInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public MemberAddInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public MemberAddInfo setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getWorkDateBegin() {
        return workDateBegin;
    }

    public MemberAddInfo setWorkDateBegin(String workDateBegin) {
        this.workDateBegin = workDateBegin;
        return this;
    }
}
