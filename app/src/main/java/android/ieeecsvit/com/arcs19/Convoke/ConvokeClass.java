package android.ieeecsvit.com.arcs19.Convoke;

import com.google.firebase.storage.StorageReference;

public class ConvokeClass {
    private String mDeveloperName; //For convoke speaker's / developers's name
    private String mDeveloperCountry; //For convoke speaker's / developers's country
    private String mDeveloperFacebook; //For convoke speaker's / developers's facebook
    private String mDeveloperDetails;
    //private String mDeveloperInstagram;
    //private String mDeveloperTwitter;
    private StorageReference mStorageReference;
    //private String mDeveloperImage; //For convoke speaker's / developers's image

    /*public DeveloperClass(String developerName, String developerCountry, String developerGithub, String developerFacebook,String developerInstagram, String developerTwitter, int developerImage){
        mDeveloperName = developerName;
        mDeveloperCountry = developerCountry;
        mDeveloperGithub = developerGithub;
        mDeveloperFacebook = developerFacebook;
        mDeveloperInstagram = developerInstagram;
        mDeveloperTwitter = developerTwitter;
        mDeveloperImage = developerImage;

    }*/

    public ConvokeClass(String developerName, String developerCountry,String developerDetails, String developerFacebook, StorageReference storageReference){
        mDeveloperName = developerName;
        mDeveloperCountry = developerCountry;
        mDeveloperFacebook = developerFacebook;
        mDeveloperDetails = developerDetails;
        //mDeveloperInstagram = developerInstagram;
        //mDeveloperTwitter = developerTwitter;
        mStorageReference = storageReference;

    }

    public String getmConvokeName(){
        return mDeveloperName;
    }

    public String getmConvokeCountry(){
        return mDeveloperCountry;
    }

    public String getmConvokeFacebook(){
        return mDeveloperFacebook;
    }

    public String getmConvokeDetails() {
        return mDeveloperDetails;
    }

    /*public String getmDeveloperInstagram() {
        return mDeveloperInstagram;
    }*/


    /*public String getmDeveloperTwitter() {
        return mDeveloperTwitter;
    }*/

    public StorageReference getmStorageReference() {
        return mStorageReference;
    }
}