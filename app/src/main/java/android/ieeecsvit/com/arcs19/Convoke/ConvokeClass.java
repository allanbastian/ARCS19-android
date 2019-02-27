package android.ieeecsvit.com.arcs19.Convoke;

import com.google.firebase.storage.StorageReference;

public class ConvokeClass {
    private String mDeveloperName; //For convoke speaker's / developers's name
    private String mDeveloperCountry; //For convoke speaker's / developers's country
    private String mDeveloperFacebook; //For convoke speaker's / developers's facebook
    private String mDeveloperDetails;
    private StorageReference mStorageReference;

    public ConvokeClass(String developerName, String developerCountry,String developerDetails, String developerFacebook, StorageReference storageReference){
        mDeveloperName = developerName;
        mDeveloperCountry = developerCountry;
        mDeveloperFacebook = developerFacebook;
        mDeveloperDetails = developerDetails;
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
    public StorageReference getmStorageReference() {
        return mStorageReference;
    }
}