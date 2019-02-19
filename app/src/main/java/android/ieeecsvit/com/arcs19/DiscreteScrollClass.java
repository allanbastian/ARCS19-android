package android.ieeecsvit.com.arcs19;

//Also used for Sponsors.

import com.google.firebase.storage.StorageReference;

public class DiscreteScrollClass {

    int icon;
    StorageReference image, sImage;
    String name,price,sname, date ;
    String des,loc, sDesc;

    public DiscreteScrollClass(){}
    //using des for sponsors to store website url
    public DiscreteScrollClass(int icon, String name, String des) {
        this.icon=icon;
        this.name=name;
        this.des= des;

    }

    //Used in the Workshoo Fragment
    public DiscreteScrollClass(StorageReference image, String date, String name, String des, String loc, String sDesc, String price, StorageReference sImage, String sname) {

        this.image = image; //Event image
        this.date = date;   //Event date
        this.name = name;   //Event Name
        this.des = des;     //Event des
        this.loc=loc;       //Event loc
        this.sDesc = sDesc; //Speaker description
        this.price=price;   //Event price
        this.sImage = sImage;   //Speaker's Image
        this.sname=sname;   //Speaker's name
    }

    public String getDate() {
        return date;
    }

    public StorageReference getImage() {
        return image;
    }

    public StorageReference getsImage() {
        return sImage;
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
    {return sDesc;}
    public String getPrice()
    {return price;}
    public String getAuthName()
    {return sname;}

    public void setImage(StorageReference image) {
        this.image = image;
    }

    public void setsImage(StorageReference sImage) {
        this.sImage = sImage;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }
}
