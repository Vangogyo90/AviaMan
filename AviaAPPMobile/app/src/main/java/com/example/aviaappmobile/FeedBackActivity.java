package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aviaappmobile.Tables.AnswerOnTexted;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class FeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        getSupportActionBar().hide();

        RecyclerView list = findViewById(R.id.list);
        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<AnswerOnTexted>> getAnswerOnTexted = apiInterface.getAnswerOnTexted(RequestBuilder.idClient);
        getAnswerOnTexted.enqueue(new Callback<ArrayList<AnswerOnTexted>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<AnswerOnTexted>> call, Response<ArrayList<AnswerOnTexted>> response) {
                if (response.isSuccessful()){
                    list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    list.setHasFixedSize(true);
                    ArrayList<AnswerOnTexted> answerOnTexted = response.body();
                    CommentsAdapter adapter = new CommentsAdapter(getApplicationContext(), answerOnTexted);
                    list.setAdapter(adapter);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<AnswerOnTexted>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton Refresh = findViewById(R.id.Refresh);
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call<ArrayList<AnswerOnTexted>> getAnswerOnTexted = apiInterface.getAnswerOnTexted(RequestBuilder.idClient);
                getAnswerOnTexted.enqueue(new Callback<ArrayList<AnswerOnTexted>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ArrayList<AnswerOnTexted>> call, Response<ArrayList<AnswerOnTexted>> response) {
                        if (response.isSuccessful()){
                            list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            list.setHasFixedSize(true);
                            ArrayList<AnswerOnTexted> answerOnTexted = response.body();
                            CommentsAdapter adapter = new CommentsAdapter(getApplicationContext(), answerOnTexted);
                            list.setAdapter(adapter);
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ArrayList<AnswerOnTexted>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddComentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserCabinetActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}