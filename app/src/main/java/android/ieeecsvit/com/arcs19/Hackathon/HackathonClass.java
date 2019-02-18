package android.ieeecsvit.com.arcs19.Hackathon;

import com.google.firebase.storage.StorageReference;

public class HackathonClass {

    private String Question; //Store the domain name / question title
    private String Describe; //Description of the domain/ title
    private StorageReference Logo; // Logo of the the related domain

    public HackathonClass(String question, String describe, StorageReference logo) {
        Question = question;
        Describe = describe;
        Logo = logo;
    }
    // get and set functions for retriving class variable values and modifying class variable values respectively
    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getDescribe() {
        return Describe;
    }

    public StorageReference getLogo() {
        return Logo;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }
}
