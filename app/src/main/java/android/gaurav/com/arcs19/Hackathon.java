package android.gaurav.com.arcs19;

public class Hackathon {

    private String Question;
    private String Describe;

    public Hackathon() {

    }

    public Hackathon(String question, String describe) {
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
