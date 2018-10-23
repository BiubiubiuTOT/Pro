package com.ljkj.qxn.wisdomsitepro.data.event;

public class RefreshEvent {

    public String message;
    public int count;

    public RefreshEvent(String message) {
        this.message = message;
    }

    public RefreshEvent(String message, int count) {
        this.message = message;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
