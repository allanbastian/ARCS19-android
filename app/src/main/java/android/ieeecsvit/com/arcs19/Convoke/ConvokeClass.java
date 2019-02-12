package android.ieeecsvit.com.arcs19.Convoke;

public class ConvokeClass {
    private String mDeveloperName; //For convoke speaker's / developers's name
    private String mDeveloperCountry; //For convoke speaker's / developers's country
    private String mDeveloperGithub; //For convoke speaker's / developers's gitHub url
    private String mDeveloperFacebook; //For convoke speaker's / developers's facebook
    //private String mDeveloperInstagram;
    //private String mDeveloperTwitter;
    private int mDeveloperImage; //For convoke speaker's / developers's image
    private boolean mDeveloperBookmark = false; //boolean if the Convoke Speaker is bookmarked

    /*public DeveloperClass(String developerName, String developerCountry, String developerGithub, String developerFacebook,String developerInstagram, String developerTwitter, int developerImage){
        mDeveloperName = developerName;
        mDeveloperCountry = developerCountry;
        mDeveloperGithub = developerGithub;
        mDeveloperFacebook = developerFacebook;
        mDeveloperInstagram = developerInstagram;
        mDeveloperTwitter = developerTwitter;
        mDeveloperImage = developerImage;

    }*/

    public ConvokeClass(String developerName, String developerCountry, String developerGithub, String developerFacebook, int developerImage){
        mDeveloperName = developerName;
        mDeveloperCountry = developerCountry;
        mDeveloperGithub = developerGithub;
        mDeveloperFacebook = developerFacebook;
        //mDeveloperInstagram = developerInstagram;
        //mDeveloperTwitter = developerTwitter;
        mDeveloperImage = developerImage;

    }

    public String getmConvokeName(){
        return mDeveloperName;
    }

    public String getmConvokeCountry(){
        return mDeveloperCountry;
    }

    public String getmConvokeGithub(){
        return mDeveloperGithub;
    }

    public String getmConvokeFacebook(){
        return mDeveloperFacebook;
    }

    /*public String getmDeveloperInstagram() {
        return mDeveloperInstagram;
    }*/

    public boolean getmConvokeBookmark() {
        return mDeveloperBookmark;
    }

    /*public String getmDeveloperTwitter() {
        return mDeveloperTwitter;
    }*/

    public int getmConvokeImage() {
        return mDeveloperImage;
    }

    public void setmConvokeBookmark(boolean mDeveloperBookmark) {
        this.mDeveloperBookmark = mDeveloperBookmark;
    }
}
