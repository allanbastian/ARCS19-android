package android.gaurav.com.arcs19.Forum;

public class ForumClass {


    String user, email, message, type, timestamp;

    ForumClass(){}

    ForumClass(String user, String email, String message, String type,String timestamp)
    {
        this.user = user;
        this.email = email;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
