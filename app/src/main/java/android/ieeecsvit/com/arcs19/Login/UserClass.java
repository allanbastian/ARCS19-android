package android.ieeecsvit.com.arcs19.Login;

import com.google.gson.annotations.SerializedName;

public class UserClass {

    //Login Class
    public UserClass(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    //Internal IEEE Member
    public UserClass(String email, String password, String name, String gender, String roomNumber, String phoneNumber, String ieeeMember, String tSize, String section, String university
                    ,String regNumber, String recaptchaResponse, Boolean ieee)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.phoneNumber = phoneNumber;
        this.tSize = tSize;
        this.university = university;
        this.regNumber = regNumber;
        this.recaptchaResponse = recaptchaResponse;
        if(ieee)
        {
            this.ieeeMember = ieeeMember;
            this.section = section;
        }
    }

    //External IEEE Member
    public UserClass(String email, String password, String name, String gender, String phoneNumber, String ieeeMember, String tSize, String section, String university
            , String recaptchaResponse, boolean ieee)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.tSize = tSize;
        this.university = university;
        this.recaptchaResponse = recaptchaResponse;
        if(ieee)
        {
            this.ieeeMember = ieeeMember;
            this.section = section;
        }
    }


    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("name")
    public String name;

    @SerializedName("gender")
    public String gender;

    @SerializedName("room")
    public String roomNumber;

    @SerializedName("memberNo")
    public String ieeeMember;

    @SerializedName("contact")
    public String phoneNumber;

    @SerializedName("tShirtSize")
    public String tSize;

    @SerializedName("section")
    public String section;

    @SerializedName("university")
    public String university;

    @SerializedName("registration")
    public String regNumber;

    @SerializedName("g-recaptcha-response")
    public String recaptchaResponse;

    @SerializedName("token")
    public boolean token;

    @SerializedName("error")
    public String error;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getIeeeMember() {
        return ieeeMember;
    }

    public void setIeeeMember(String ieeeMember) {
        this.ieeeMember = ieeeMember;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String gettSize() {
        return tSize;
    }

    public void settSize(String tSize) {
        this.tSize = tSize;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    public void setRecaptchaResponse(String recaptchaResponse) {
        this.recaptchaResponse = recaptchaResponse;
    }

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
