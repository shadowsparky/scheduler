package ru.shadowsparky.scheduler.carolinescheduler.Utils;

import android.support.annotation.NonNull;

public class Schedule_Element {

    private Integer ID;
    private String Schedule_Header;
    private String Schedule_Description;

    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }
    public String getSchedule_Header() {
        return Schedule_Header;
    }
    public void setSchedule_Header(String schedule_Header) {
        Schedule_Header = schedule_Header;
    }
    public String getSchedule_Description() {
        return Schedule_Description;
    }
    public void setSchedule_Description(String schedule_Description) {
        Schedule_Description = schedule_Description;
    }
}
