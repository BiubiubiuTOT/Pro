package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

public class SafeTechnologyDetail {

    public String id;

    /** 交底名称 */
    public String presentationName;

    /** 交底日期 */
    public String presentationDate;

    /** 交底部位 */
    public String presentationPosition;

    /** 交底人 */
    public String presentationPerson;

    /** 专职安全员 */
    public String safetyOfficer;


    /** 是否上报监督机构 */
    public int isReport;

    /** 交底内容 */
    public String presentationInfo;

    public List<Person> persons;

    public static class Person {
        /** 劳务公司 */
        public String laborCom;

        /** 班组名称 */
        public String teamName;

        /** 班组负责人 */
        public String teamPerson;
    }

}
