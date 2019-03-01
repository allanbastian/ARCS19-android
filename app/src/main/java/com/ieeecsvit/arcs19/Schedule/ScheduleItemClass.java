package com.ieeecsvit.arcs19.Schedule;

public class ScheduleItemClass {
    String name, time;
    String icon;

    public ScheduleItemClass(){}

    public ScheduleItemClass(String name, String time, String icon)
    {
        this.name = name;
        this.time = time;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
