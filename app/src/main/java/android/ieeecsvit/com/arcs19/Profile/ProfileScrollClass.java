package android.ieeecsvit.com.arcs19.Profile;

public class ProfileScrollClass {
    int icon;
    String name;

    public ProfileScrollClass(int icon, String name) {
        this.icon = icon;
        this.name = name;
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
}
