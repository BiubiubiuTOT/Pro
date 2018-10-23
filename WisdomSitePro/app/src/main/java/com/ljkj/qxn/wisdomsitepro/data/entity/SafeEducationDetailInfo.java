package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/3/14 11:50
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeEducationDetailInfo {
    public String id;

    /** 交底名称 */
    public String presentationName;

    /** 交底日期 */
    public String presentationDate;

    /** 交底内容 */
    public String presentationInfo;

    /** 培训人 */
    public String trainer;

    public int isReport;

    public List<Person> persons;

    public static class Person {
        /** 劳务公司名 */
        public String laborCom;
        /** 班组名 */
        public String teamName;
    }


}
