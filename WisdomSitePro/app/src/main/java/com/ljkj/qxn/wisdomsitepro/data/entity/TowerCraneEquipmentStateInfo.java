package com.ljkj.qxn.wisdomsitepro.data.entity;

public class TowerCraneEquipmentStateInfo {

    private String alive;//在线

    private String death; //离线

    private String alarm; //提醒

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return "TowerCraneEquipmentStateInfo{" +
                "alive='" + alive + '\'' +
                ", death='" + death + '\'' +
                ", alarm='" + alarm + '\'' +
                '}';
    }
}
