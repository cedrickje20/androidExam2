package com.example.anexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Examlist extends AppCompatActivity {
    private ListView lv;
    private List<Questions> loginArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examlist);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Questions>> question = jsonPlaceHolderApi.getQuestion();
        question.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
            loginArrayList = response.body();
            for(int i = 0; i<loginArrayList.size();i++){
                Custom custom  = new Custom(loginArrayList,Examlist.this,R.layout.model);
                lv.setAdapter(custom);
            }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {

            }
        });
    }
}