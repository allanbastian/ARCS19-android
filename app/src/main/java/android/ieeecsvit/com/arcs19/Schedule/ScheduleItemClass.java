package android.ieeecsvit.com.arcs19.Schedule;

public class ScheduleItemClass {
    String agenda, timing;
    String iconName;

    public ScheduleItemClass(String agenda, String timing, String iconName)
    {
        this.agenda = agenda;
        this.timing = timing;
        this.iconName = iconName;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
