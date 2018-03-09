package com.example.abc.test_retrofit;

import com.example.abc.test_retrofit.response.UserResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("app_test/")
    @FormUrlEncoded
    Call<ResponseBody> userPost(@FieldMap Map<String,String> param);

}