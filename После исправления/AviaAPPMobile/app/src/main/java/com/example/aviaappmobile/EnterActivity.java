package com.example.aviaappmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviaappmobile.Tables.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        getSupportActionBar().hide();

        if (RequestBuilder.API_KEY != null){
            Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
            startActivity(intent);
            finish();
        }

        EditText Login = findViewById(R.id.Login);
        EditText Password = findViewById(R.id.Password);

        Button Enter = findViewById(R.id.Enter);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call<String> getAuthorization = apiInterface.getAuthorization(Login.getText().toString(), Password.getText().toString());
                getAuthorization.enqueue(new Callback<String>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            RequestBuilder.API_KEY = response.body();
                            Toast.makeText(getApplicationContext(), "Вы успешно вошли в аккаунт", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Такого пользователя не существует", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                Call<Integer> getIdUser = apiInterface.getIdUser(Login.getText().toString());
                getIdUser.enqueue(new Callback<Integer>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()){
                            RequestBuilder.idClient = response.body();
                            Intent intent = new Intent(getApplicationContext(), UserCabinetActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}