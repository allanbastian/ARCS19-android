package android.gaurav.com.arcs19.Hackathon;

public class HackathonClass {

    private String Question;
    private String Describe;

    public HackathonClass() {

    }

    public HackathonClass(String question, String describe) {
        Question = question;
        Describe = describe;
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

    public void setDescribe(String describe) {
        Describe = describe;
    }
}
