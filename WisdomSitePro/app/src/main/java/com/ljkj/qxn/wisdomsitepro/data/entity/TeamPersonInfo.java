package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：班组
 * 创建人：lxx
 * 创建时间：2018/7/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TeamPersonInfo {

    /**
     * teams : 测试3
     * count : 1
     */

    private String teams;
    private String count;
    private String phone;
    private String unit;
    private String teamManger;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTeamManger() {
        return teamManger;
    }

    public void setTeamManger(String teamManger) {
        this.teamManger = teamManger;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
