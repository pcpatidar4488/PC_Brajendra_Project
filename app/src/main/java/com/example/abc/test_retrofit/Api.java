package com.example.abc.test_retrofit;

import com.example.abc.test_retrofit.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("api_test/")
    @FormUrlEncoded
    Call<UserResponse> userPost(@Field("username") String userName,
                                @Field("password") String passWord);
}