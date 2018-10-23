package com.ljkj.qxn.wisdomsitepro.data.entity;


import java.util.List;

/**
 * 类描述：项目统计实体
 * 创建人：lxx
 * 创建时间：2018/6/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectStatisticsInfo {

    /**
     * 施工天数
     */
    public String sgts;

    /**
     * 工期已用
     */
    public String gq;

    /**
     * 在册工人数
     */
    public String zcgrs;

    /**
     * 入场人数
     */
    public String rcry;

    /**
     * 在场人数
     */
    public String zcry;

    /**
     * 出勤率
     */
    public String cql;

    /**
     * 班组在场人数
     */
    public List<Team> bzzcrs;

    public static class Team {
        /**
         * 组名
         */
        public String name;

        /**
         * （实到/应到)
         */
        public String show;
    }

}
