package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.Rate;

public class RateMoreActivity extends AppCompatActivity {

    public static Rate rate;
    public static boolean buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_more);
        getSupportActionBar().hide();

        TextView MaxWeightHand = findViewById(R.id.MaxWeightHand);
        TextView MaxWeightCheck = findViewById(R.id.MaxWeightCheck);
        TextView WIFI = findViewById(R.id.WIFI);
        TextView Breakfast = findViewById(R.id.Breakfast);
        TextView Dinner = findViewById(R.id.Dinner);
        TextView Lunch = findViewById(R.id.Lunch);
        TextView Sockets = findViewById(R.id.Sockets);
        TextView TV = findViewById(R.id.TV);

        MaxWeightHand.setText("Максимальный вес ручной клади: " + rate.getMaximumWeightHandLuggage() + "кг");
        MaxWeightCheck.setText("Максимальный вес багажа: " + rate.getMaximumWeightCheckedBaggage() + "кг");
        WIFI.setText("Wi-FI: " + (rate.isWiFi() ? "✓" : "x"));
        Breakfast.setText("Завтрак: " + (rate.isBreakfast() ? "✓" : "x"));
        Dinner.setText("Обед: " + (rate.isDinner() ? "✓" : "x"));
        Lunch.setText("Ужин: " + (rate.isLunch() ? "✓" : "x"));
        Sockets.setText("Розетки: " + (rate.isSockets() ? "✓" : "x"));
        TV.setText("TV: " + (rate.isTv() ? "✓" : "x"));

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
    }
}