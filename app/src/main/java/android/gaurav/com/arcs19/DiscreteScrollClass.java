package android.gaurav.com.arcs19;

//Also used for Sponsors.

public class DiscreteScrollClass {

    int icon,price,authimg;
    String name,sname;
    String des,loc,authdesc;

    public DiscreteScrollClass(){}
    //using des for sponsors to store website url
    public DiscreteScrollClass(int icon, String name, String des) {}


    public DiscreteScrollClass(int icon, String name, String des,String loc,String authdesc,int price,int authimg,String sname) {

        this.icon = icon;
        this.name = name;
        this.des = des;
        this.loc=loc;
        this.authdesc=authdesc;
        this.price=price;
        this.authimg=authimg;
        this.sname=sname;
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

    public String getLocation()
    {return loc;}
    public String getAuthDesc()
    {return authdesc;}
    public int getPrice()
    {return price;}
    public int getAuthImg()
    {return authimg;}
    public String getAuthName()
    {return sname;}
}
