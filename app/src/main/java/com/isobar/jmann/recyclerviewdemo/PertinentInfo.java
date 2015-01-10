package com.isobar.jmann.recyclerviewdemo;

import java.util.Date;

/**
 * Created by jmann on 1/8/15.
 */
public class PertinentInfo {

    protected String item;
    protected Date time;

    public PertinentInfo(String item, Date time) {
        this.item = item;
        this.time = time;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
