package com.example.abc.test_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText passWord;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = passWord.getText().toString();
                hitData(user,pass);
            }
        });

    }

    private void hitData(final String username, final String password) {
        Api apiService = BaseUrl.getClient().create(Api.class);
        HashMap param=new HashMap();
        param.put("username",username);
        param.put("password",password);
        Call<ResponseBody> call=apiService.userPost(param);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
                userName.setText("");
                passWord.setText("");

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();

            }
        });


    }
}
