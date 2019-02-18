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

    @SerializedName("ieeemember")
    public String ieeeMember;

    @SerializedName("contact")
    public String phoneNumber;

    @SerializedName("tShirtSize")
    public String tSize;

    @SerializedName("ieeeSection")
    public String section;

    @SerializedName("university")
    public String university;

    @SerializedName("registration")
    public String regNumber;

    @SerializedName("g-recaptcha-response")
    public String recaptchaResponse;




}
