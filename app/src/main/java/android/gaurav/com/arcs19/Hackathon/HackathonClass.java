package android.gaurav.com.arcs19.Hackathon;

public class HackathonClass {

    private String Question;
    private String Describe;
    private int Logo;

    public HackathonClass(String question, String describe, int logo) {
        Question = question;
        Describe = describe;
        Logo = logo;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getDescribe() {
        return Describe;
    }

    public int getLogo() {
        return Logo;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }
}
