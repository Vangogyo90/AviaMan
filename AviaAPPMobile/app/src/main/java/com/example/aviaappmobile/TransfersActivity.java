package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.aviaappmobile.Tables.FlightTransfer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TransfersActivity extends AppCompatActivity {

    public static int id;
    public static boolean buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);
        getSupportActionBar().hide();

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buy){
                    Intent intent = new Intent(getApplicationContext(), MoreBuyingTicketsActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), MoreTicketActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        LinearLayout linear = findViewById(R.id.linear);

        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<FlightTransfer>> getTransfers = apiInterface.getTransfers(id);
        getTransfers.enqueue(new Callback<ArrayList<FlightTransfer>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<FlightTransfer>> call, Response<ArrayList<FlightTransfer>> response) {
                ArrayList<FlightTransfer> flightTransfers = response.body();
                for (int i = 0; i <flightTransfers.size(); i++){
                    TextView time = new TextView(getApplicationContext());
                    time.setText("Дата и время пересадки: " + flightTransfers.get(i).getTransfers().getTransferTime().replace("T", " "));
                    time.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    time.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_edittext) );
                    ((LinearLayout) linear).addView(time);

                    TextView city = new TextView(getApplicationContext());
                    city.setText("Город пересадки: " + flightTransfers.get(i).getTransfers().getCities().getNameCity());
                    city.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    city.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_edittext) );
                    ((LinearLayout) linear).addView(city);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<FlightTransfer>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}