package com.example.aviaappmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.AirTicket;
import com.example.aviaappmobile.Tables.Purchases;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BuyActivity extends AppCompatActivity {

    public static int id;
    public static double cost;
    public static AirTicket airTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        getSupportActionBar().hide();

        EditText NumberCard = findViewById(R.id.NumberCard);
        EditText CVV = findViewById(R.id.CVV);
        EditText Date = findViewById(R.id.Date);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BuyActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        Button Buy = findViewById(R.id.Buy);
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NumberCard.getText().toString().length() == 16 && CVV.getText().toString().length() == 3 && Date.getText().toString() != ""){
                    APIInterface apiInterface;
                    apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                    Call<Purchases> postPurchases = apiInterface.postPurchases(new Purchases(id, RequestBuilder.idClient, cost));
                    postPurchases.enqueue(new Callback<Purchases>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<Purchases> call, Response<Purchases> response) {
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<Purchases> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    Call<AirTicket> putAirTickets = apiInterface.putAirTickets(id, airTicket);
                    putAirTickets.enqueue(new Callback<AirTicket>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<AirTicket> call, Response<AirTicket> response) {
                            Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "Вы успешно купили билет!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<AirTicket> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoreTicketActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}