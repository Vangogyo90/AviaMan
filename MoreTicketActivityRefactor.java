package com.example.aviaappmobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.security.auth.callback.Callback;
import javax.swing.text.View;

import com.example.aviaappmobile.Tables.AirTicket;
import com.example.aviaappmobile.Tables.Rate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MoreTicketActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.2.2:7023/api/";
    public static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_ticket);
        getSupportActionBar().hide();

        static int id;
        int idAir;
        double cost;
        Rate rate;
        int idFly;
        AirTicket airTickett;

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

        Button Transfers = findViewById(R.id.Transfers);
        Button Refresh = findViewById(R.id.Refresh);
        Button Back = findViewById(R.id.Back);
        Button Check = findViewById(R.id.Check);
        Button Buy = findViewById(R.id.Buy);
        Button MoreAboutRate = findViewById(R.id.MoreAboutRate);

        APIInterface apiInterface;
        apiInterface = Request.buildRequest(URL, API_KEY).create(APIInterface.class);

        DateDelay.setVisibility(View.GONE);

        GetData();

        Refresh.setOnClickListener(new View.OnClickListener() {
            GetData();
        });

        Transfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransfersActivity.id = idFly;
                TransfersActivity.buy = false;
                Intent intent = new Intent(getApplicationContext(), TransfersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        MoreAboutRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateMoreActivity.rate = rate;
                RateMoreActivity.buy = false;
                Intent intent = new Intent(getApplicationContext(), RateMoreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RequestBuilder.API_KEY != null){
                    Call<User> userId = apiInterface.userId(RequestBuilder.idClient);
                    userId.enqueue(new Callback<User>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if (user.isVerifiedAccount()) {
                                BuyActivity.id = idAir;
                                BuyActivity.cost = cost;
                                BuyActivity.airTicket = new AirTicket(airTickett.getIdAirTicket(), airTickett.getFlightId(), airTickett.getRateAirlaneId(), airTickett.getCostOfAirTicket(),
                                        airTickett.isHaveTransfer(), airTickett.getSeatNumber(), true);
                                Intent intent = new Intent(getApplicationContext(), BuyActivity.class);
                                startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(getApplicationContext(), "Только подтверденный пользователь может покупать билеты", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(), "Только вошедший пользователь может покупать билеты", Toast.LENGTH_SHORT).show();
            }
        });

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ArrayList<FlightDelay>> getDelay = apiInterface.getDelay(idFly);
                getDelay.enqueue(new Callback<ArrayList<FlightDelay>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ArrayList<FlightDelay>> call, Response<ArrayList<FlightDelay>> response) {
                        ArrayList<FlightDelay> flightDelays = response.body();
                        if (flightDelays.size() > 0){
                            DateDelay.setVisibility(View.VISIBLE);
                            DateDelay.setText("Опоздание: " + flightDelays.get(0).getDateAndTimeDelay().replace("T", " "));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Рейс не запаздывает", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ArrayList<FlightDelay>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

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

//Класс для связи API с приложением
private class Request{
    static Retrofit buildRequest(String URL, String API_KEY){
    Retrofit retrofit
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();
    OkHttpClient client = new OkHttpClient.Builder()
            .connectionSpecs(Collections.singletonList(spec))
            .certificatePinner(new CertificatePinner.Builder()
                    .add("drakeet.me", "sha256/gGOcYKAwzEaUfun6YdxZvFSQq/x2lF/R8UizDFofveY=")
                    .build())
            .addInterceptor(new Interceptor() {
        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + API_KEY)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();
    retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build();
    return retrofit;
    }


}

class Gones {
    private void SetGone(boolean i, TextView Transfers){
        if (i)
            Transfers.setVisibility(View.GONE);
        else
            Transfers.setVisibility(View.VISIBLE);
    }
}

class Data implements  AppCompatActivity implements AppCompatCallback{
    private AppCompatDelegate delegate;
        private AppCompatDelegate delegate;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            delegate = AppCompatDelegate.create(this, this);
            delegate.onCreate(savedInstanceState);
            super.onCreate(savedInstanceState);
        }
        public void GetData(APIInterface apiInterface, TextView Cities, TextView Price, TextView NumberSeat, TextView DateDeparture,
        TextView DateArrival, TextView AviaCompany, TextView NamePlane, TextView NumberFlight, TextView NameRate, int id){
        Call<ArrayList<AirTicket>> getAirTicketListALL = apiInterface.getAirTicketListALL(id);
        getAirTicketListALL.enqueue(new Callback<ArrayList<AirTicket>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                if (response.isSuccessful()){
                    ArrayList<AirTicket> airTickets = response.body().get(0);

                    Cities.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
                    Price.setText(Double.toString(airTicket.getRateAirlanes().getRates().getCostOfRate() + airTicket.getCostOfAirTicket()) + "руб.");
                    NumberSeat.setText("Номер места: " + airTicket.getSeatNumber());
                    DateDeparture.setText("Дата и время вылета: " + airTicket.getFlights().getDateAndTimeDeparture().replace("T", " "));
                    DateArrival.setText("Дата и время прилета: " + airTicket.getFlights().getDateAndTimeArrival().replace("T", " "));
                    AviaCompany.setText("Авиакомпания: " + airTicket.getFlights().getAirlines().getNameAirline());
                    NamePlane.setText("Самолет: " + airTicket.getFlights().getPlanes().getNamePlane());
                    NumberFlight.setText("Номер полета: " + airTicket.getFlights().getNumberOfFlight());
                    NameRate.setText("Название тарифа: " + airTicket.getRateAirlanes().getRates().getNameRate());

                    if (DateDeparture.getText().toString().equals(DateArrival.getText().toString())){
                        Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();
                    }

                    Gones.SetGone(airTicket.isHaveTransfer());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Ошибка, проверьте подключение", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<AirTicket>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}