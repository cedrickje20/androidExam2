package com.example.anexam;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText etUser;
    private EditText etPass;
    private Button btnSubmit;
    private TextView textView;
    ArrayList<Login> logins = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnSubmit = findViewById(R.id.btnLogin);
        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Login>> call = jsonPlaceHolderApi.getLogin();

        call.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(MainActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Login> logins = response.body();

                for (Login log : logins){
                    String content = "";
                    content += "ID: " + log.getId() + "\n";
                    content += "Title: " + log.getTitle() + "\n";


                    textView.append(content);
                }
                StringBuilder stringBuilder = new StringBuilder();

            }

            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                Log.d("Error: ",t.getMessage());
                textView.setText(t.getMessage());
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logins.add(new Login("GESIM","20225405"));
                logins.add(new Login("AW","321"));
                logins.add(new Login("WA","123"));
                String user, pass;
                user = etUser.getText().toString();
                pass = etPass.getText().toString();
//                Call<LoginResponse> call1 = jsonPlaceHolderApi.getLoginInfo(user, pass);
//                call1.enqueue(new Callback<LoginResponse>() {
//                    @Override
//                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                       textView.setText("OnResponse: "+response.body().getToken());
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResponse> call, Throwable t) {
//                        textView.setText("OnFailure: "+t.getMessage());
//                    }
//                });
                Intent intent = new Intent(MainActivity.this, Examlist.class);
                boolean found = false;
                for (Login item : logins) {
                    if (item.getId().equals(user) && item.getTitle().equals(pass)) {
                        found = true;
                        break;
                    }
                }
                if(found){
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT).show();
                }


            }
        });
        }
    }