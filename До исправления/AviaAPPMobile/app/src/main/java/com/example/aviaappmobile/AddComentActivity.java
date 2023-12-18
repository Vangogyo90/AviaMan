package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.ComplaintsAndSuggestion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AddComentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coment);
        getSupportActionBar().hide();

        EditText Comment = findViewById(R.id.Comment);

        Button Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call<ComplaintsAndSuggestion> postComplaintsAndSuggestion = apiInterface.postComplaintsAndSuggestion(new ComplaintsAndSuggestion(Comment.getText().toString(), RequestBuilder.idClient));
                postComplaintsAndSuggestion.enqueue(new Callback<ComplaintsAndSuggestion>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ComplaintsAndSuggestion> call, Response<ComplaintsAndSuggestion> response) {
                        if (response.isSuccessful())
                            Toast.makeText(getApplicationContext(), "Дождитесь ответа и ваш ответ высветиться", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "Что-то пошло не так, повторите попытку позже", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ComplaintsAndSuggestion> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedBackActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}