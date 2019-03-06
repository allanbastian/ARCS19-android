package com.ieeecsvit.arcs19.Hackathon;

import com.google.firebase.storage.StorageReference;

public class HackathonClass {

    private String Question; //Store the domain name / question title
    private String Describe; //Description of the domain/ title
    private StorageReference Logo; // Logo of the the related domain
    private String About;

    public HackathonClass(String question, String describe, StorageReference logo, String About) {
        Question = question;
        Describe = describe;
        Logo = logo;
        this.About = About;
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

    public String getAbout() {
        return About;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }
}
