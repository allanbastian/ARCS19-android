package android.ieeecsvit.com.arcs19;

import android.ieeecsvit.com.arcs19.Login.UserClass;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {


    @POST("api/register")
    Call<HashMap<String,String>> createUser(@Body UserClass userClass);

    @FormUrlEncoded
    @POST("api/login")
    Call<HashMap<String,Boolean>> loginUser(@Field("email") String email, @Field("password") String password);

    @GET("api/user/app-profile")
    Call<UserClass> getProfile(@Header("token") String jwtToken);

    @GET("api/user/app-receipt")
    Call<ArrayList<HashMap<String,String>>> getEvents(@Header("token") String jwtToken);

}
