package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.AirTicket;
import com.example.aviaappmobile.Tables.Rate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MoreBuyingTicketsActivity extends AppCompatActivity {

    public static int id;

    int idAir;

    double cost;

    Rate rate;

    int idFly;

    AirTicket airTickett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_buying_tickets);
        getSupportActionBar().hide();

        TextView Cities = findViewById(R.id.Cities);
        TextView Price = findViewById(R.id.Price);
        TextView NumberSeat = findViewById(R.id.NumberSeat);
        TextView DateDeparture = findViewById(R.id.DateDeparture);
        TextView DateArrival = findViewById(R.id.DateArrival);
        TextView AviaCompany = findViewById(R.id.AviaCompany);
        TextView NamePlane = findViewById(R.id.NamePlane);
        TextView NumberFlight = findViewById(R.id.NumberFlight);
        TextView NameRate = findViewById(R.id.NameRate);
        TextView DateDelay = findViewById(R.id.DateDelay);
        DateDelay.setVisibility(View.GONE);

        Button Transfers = findViewById(R.id.Transfers);

        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<AirTicket>> getAirTicketListALL = apiInterface.getAirTicketListALL(id);
        getAirTicketListALL.enqueue(new Callback<ArrayList<AirTicket>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                ArrayList<AirTicket> airTickets = response.body();
                AirTicket airTicket = airTickets.get(0);
                Cities.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
                Price.setText(Double.toString(airTicket.getRateAirlanes().getRates().getCostOfRate() + airTicket.getCostOfAirTicket()) + "руб.");
                NumberSeat.setText("Номер места: " + airTicket.getSeatNumber());
                DateDeparture.setText("Дата и время вылета: " + airTicket.getFlights().getDateAndTimeDeparture().replace("T", " "));
                DateArrival.setText("Дата и время прилета: " + airTicket.getFlights().getDateAndTimeArrival().replace("T", " "));
                AviaCompany.setText("Авиакомпания: " + airTicket.getFlights().getAirlines().getNameAirline());
                NamePlane.setText("Самолет: " + airTicket.getFlights().getPlanes().getNamePlane());
                NumberFlight.setText("Номер полета: " + airTicket.getFlights().getNumberOfFlight());
                NameRate.setText("Название тарифа: " + airTicket.getRateAirlanes().getRates().getNameRate());
                rate = airTicket.getRateAirlanes().getRates();
                idFly = airTicket.getFlightId();
                idAir = airTicket.getIdAirTicket();
                cost = airTicket.getRateAirlanes().getRates().getCostOfRate() + airTicket.getCostOfAirTicket();
                airTickett = airTicket;
                if (airTicket.isHaveTransfer())
                    Transfers.setVisibility(View.GONE);
                else
                    Transfers.setVisibility(View.VISIBLE);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<AirTicket>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Transfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransfersActivity.id = idFly;
                TransfersActivity.buy = true;
                Intent intent = new Intent(getApplicationContext(), TransfersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button MoreAboutRate = findViewById(R.id.MoreAboutRate);
        MoreAboutRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateMoreActivity.rate = rate;
                RateMoreActivity.buy = true;
                Intent intent = new Intent(getApplicationContext(), RateMoreActivity.class);
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