package com.example.abc.contacts;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.contacts.adapter.ContactListAdapter;
import com.example.abc.contacts.response.UserResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText passWord;
    LinearLayout layout_recycler_view,layout_submit;
    LinearLayout submit;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ContactListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        submit = (LinearLayout) findViewById(R.id.submit);
        layout_recycler_view = (LinearLayout) findViewById(R.id.layout_recycler_view);
        layout_submit = (LinearLayout) findViewById(R.id.layout_submit);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        layout_recycler_view.setVisibility(View.GONE);
        layout_submit.setVisibility(View.VISIBLE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = passWord.getText().toString();
                hitData(user,pass);
            }
        });

        initActionbar();

    }

    private void hitData(final String username, final String password) {
        Api apiService = ApiClient.getClient().create(Api.class);
        
        Call<UserResponse> call=apiService.userPost(username,password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    layout_recycler_view.setVisibility(View.VISIBLE);
                    layout_submit.setVisibility(View.GONE);
                    userName.setText("");
                    passWord.setText("");
                    mRecyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(layoutManager);
                    mAdapter = new ContactListAdapter(getApplicationContext(),response.body());
                    //Notify Adapter
                    // mAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mAdapter);
                }else {
                    Toast.makeText(MainActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();
                userName.setText("");
                passWord.setText("");

            }
        });
    }

    private Map postData(final String username, final String password){

        HashMap param=new HashMap();
        param.put("username",username);
        param.put("password",password);
        return param;
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.action_bar_tittle_text_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(viewActionBar, params);
        TextView actionbarTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        actionbarTitle.setText("Contacts");
        actionbarTitle.setTextSize(16);
        actionbarTitle.setTextColor(getResources().getColor(R.color.white));
//        actionbarTitle.setTypeface(TypefaceCache.get(getAssets(), 3));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                layout_recycler_view.setVisibility(View.GONE);
                layout_submit.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        layout_recycler_view.setVisibility(View.GONE);
        layout_submit.setVisibility(View.VISIBLE);
//        super.onBackPressed();
    }
}
