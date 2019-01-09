package android.gaurav.com.arcs19;

//Also used for Sponsors.

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

<<<<<<< HEAD
=======
    //Using this constructor for Sponsors, des can be used for storing url.

    public DiscreteScrollClass(int icon, String name){
        this.icon = icon;
        this.name = name;
        this.des = "N/A";
    }
>>>>>>> 67c832021ce7f73b0da1249b4e96274da0b8d0d2

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
