package android.ieeecsvit.com.arcs19.Developers;

public class DeveloperClass {
    int icon;
    String name, post, linkedIn,github;
    public DeveloperClass(int icon, String name, String post, String linkedIn, String github){
        this.github = github;
        this.name = name;
        this.icon=icon;
        this.linkedIn=linkedIn;
        this.post=post;

    }

    public int getIcon() {
        return icon;
    }

    public String getGithub() {
        return github;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }
}
