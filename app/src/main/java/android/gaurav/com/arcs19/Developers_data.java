package android.gaurav.com.arcs19;

public class Developers_data {
    private String name;
    private String post;

    public Developers_data() {
    }

    public Developers_data(String name, String post) {
        this.name = name;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
