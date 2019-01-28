package android.gaurav.com.arcs19.Forum;

public class ForumClass {


    String username, message, msgtype, timestamp;

    ForumClass(){}

    ForumClass(String username, String message, String msgtype,String timestamp)
    {
        this.username = username;
        this.message = message;
        this.msgtype = msgtype;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
