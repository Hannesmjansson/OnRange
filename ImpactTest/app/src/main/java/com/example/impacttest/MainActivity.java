package com.example.impacttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button bt;
    TextView textView;
    String credBase64 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
      String url =  "https://impact.idc.nokia.com/m2m/endpoints/70b3d57050000f35/";      // https://impact.idc.nokia.com/ui/#/
        String usernameValue = "OnboardCB";
        String passValue = "alperna6799energi";
        String credentials = usernameValue +  passValue;
         credBase64 = "Basic " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
         ;
    Retrofit retrofit = builder.build();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserClient userClient = retrofit.create(UserClient.class);

                User user = new User("1234" , "TestGroup", 2, "1234", "http", "0202020202");

                Call<User> call = userClient.getUser(); // se userclient credBase64,  user
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){                                                    // kolla om respone code e 201?
                            textView.setText("SUCCESS " + response.code());
                        }
                        if(!response.isSuccessful()){
                            textView.setText("asd " + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        textView.setText("Fail: " +t.getMessage());
                    }
                });
            }
        });
    }




}