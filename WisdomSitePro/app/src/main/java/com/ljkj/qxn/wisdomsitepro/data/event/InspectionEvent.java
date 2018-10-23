package com.ljkj.qxn.wisdomsitepro.data.event;

/**
 * 类描述：巡检相关事件
 * 创建人：rjf
 * 创建时间：2018/3/19 20:22.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class InspectionEvent {

    private int msgType;

    public InspectionEvent() {

    }

    public InspectionEvent(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
