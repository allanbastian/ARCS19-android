package android.gaurav.com.arcs19;

public class DiscreteScrollClass {

    int icon;
    String name;
    String des;

    public DiscreteScrollClass(){}

    public DiscreteScrollClass(int icon, String name, String des) {
        this.icon = icon;
        this.name = name;
        this.des = des;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
