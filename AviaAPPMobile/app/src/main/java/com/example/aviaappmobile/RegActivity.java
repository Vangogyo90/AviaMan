package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        getSupportActionBar().hide();

        if (RequestBuilder.API_KEY != null){
            Intent intent = new Intent(getApplicationContext(), UserCabinetActivity.class);
            startActivity(intent);
            finish();
        }

        EditText Login = findViewById(R.id.Login);
        EditText Password = findViewById(R.id.Password);
        EditText TelephoneNumber = findViewById(R.id.TelephoneNumber);
        EditText Email = findViewById(R.id.Email);

        Button Registr = findViewById(R.id.Registr);
        Registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call<User> postUser = apiInterface.postUser(new User(Login.getText().toString(), Password.getText().toString(), TelephoneNumber.getText().toString(),
                        Email.getText().toString(), 1));
                postUser.enqueue(new Callback<User>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Вы ввели неверный формат данных", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button Enter = findViewById(R.id.Enter);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}