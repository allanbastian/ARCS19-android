package android.ieeecsvit.com.arcs19;

import android.ieeecsvit.com.arcs19.Login.UserClass;

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

/*
    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/

}
