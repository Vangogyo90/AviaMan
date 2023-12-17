package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.AirTicket;
import com.example.aviaappmobile.Tables.FlightDelay;
import com.example.aviaappmobile.Tables.Rate;
import com.example.aviaappmobile.Tables.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MoreTicketActivity extends AppCompatActivity {

    public static int id;

    int idAir;

    double cost;

    Rate rate;

    int idFly;

    AirTicket airTickett;

    Retrofit retrofit;

    TextView Cities;
    TextView Price;
    TextView NumberSeat;
    TextView DateDeparture;
    TextView DateArrival;
    TextView AviaCompany;
    TextView NamePlane;
    TextView NumberFlight;
    TextView NameRate;
    TextView DateDelay;

    Button Transfers;
    Button Refresh;
    Button Back;
    Button Check;
    Button Buy;
    Button MoreAboutRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_ticket);
        getSupportActionBar().hide();
        Text();
        Buttons();

        DateDelay.setVisibility(View.GONE);

        APIInterface apiInterface;
        apiInterface = buildRequest().create(APIInterface.class);
        Call<ArrayList<AirTicket>> getAirTicketListALL = apiInterface.getAirTicketListALL(id);
        getAirTicketListALL.enqueue(new Callback<ArrayList<AirTicket>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                try{
                    ArrayList<AirTicket> airTickets = response.body();
                    AirTicket airTicket = airTickets.get(0);

                    if (Cities.getText().length() > 0){
                        Cities.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
                        Toast.makeText(getApplicationContext(), "Text в Cities показан", Toast.LENGTH_SHORT).show();
                        if (Price.getText().length() > 0){
                            Price.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
                            Toast.makeText(getApplicationContext(), "Text в Price показан", Toast.LENGTH_SHORT).show();
                            if (NumberSeat.getText().length() > 0){
                                NumberSeat.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
                                Toast.makeText(getApplicationContext(), "Text в NumberSeat показан", Toast.LENGTH_SHORT).show();
                                if (DateDeparture.getText().length() > 0){
                                    DateDeparture.setText("Дата и время вылета: " + airTicket.getFlights().getDateAndTimeDeparture().replace("T", " "));
                                    Toast.makeText(getApplicationContext(), "Text в DateDeparture показан", Toast.LENGTH_SHORT).show();
                                    if (DateArrival.getText().length() > 0){
                                        DateArrival.setText("Дата и время прилета: " + airTicket.getFlights().getDateAndTimeArrival().replace("T", " "));
                                        Toast.makeText(getApplicationContext(), "Text в DateArrival показан", Toast.LENGTH_SHORT).show();
                                        if (AviaCompany.getText().length() > 0){
                                            AviaCompany.setText("Авиакомпания: " + airTicket.getFlights().getAirlines().getNameAirline());
                                            Toast.makeText(getApplicationContext(), "Text в AviaCompany показан", Toast.LENGTH_SHORT).show();
                                            if (NamePlane.getText().length() > 0){
                                                NamePlane.setText("Самолет: " + airTicket.getFlights().getPlanes().getNamePlane());
                                                Toast.makeText(getApplicationContext(), "Text в NamePlane показан", Toast.LENGTH_SHORT).show();
                                                if (NumberFlight.getText().length() > 0){
                                                    NumberFlight.setText("Номер полета: " + airTicket.getFlights().getNumberOfFlight());
                                                    Toast.makeText(getApplicationContext(), "Text в NumberFlight показан", Toast.LENGTH_SHORT).show();
                                                    if (NameRate.getText().length() > 0){
                                                        NameRate.setText("Название тарифа: " + airTicket.getRateAirlanes().getRates().getNameRate());
                                                        Toast.makeText(getApplicationContext(), "Text в NameRate показан", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    rate = airTicket.getRateAirlanes().getRates();
                    idFly = airTicket.getFlightId();
                    idAir = airTicket.getIdAirTicket();
                    cost = airTicket.getRateAirlanes().getRates().getCostOfRate() + airTicket.getCostOfAirTicket();
                    airTickett = airTicket;

                    //6
                    if (airTicket.isHaveTransfer())
                        Transfers.setVisibility(View.GONE);
                    else
                        Transfers.setVisibility(View.VISIBLE);
                }
                catch(Exception e){
                    handleError();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<AirTicket>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //4
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //23
                APIInterface apiInterface;
                apiInterface = buildRequest().create(APIInterface.class);
                Call<ArrayList<AirTicket>> getAirTicketListALL = apiInterface.getAirTicketListALL(id);
                getAirTicketListALL.enqueue(new Callback<ArrayList<AirTicket>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                        //1
                        try{
                            //11
                            ArrayList<AirTicket> airTickets = response.body();
                            AirTicket airTicket = airTickets.get(0);

                            Cities.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
                            Price.setText(Double.toString(airTicket.getRateAirlanes().getRates().getCostOfRate() + airTicket.getCostOfAirTicket()) + "руб.");
                            NumberSeat.setText("Номер места: " + airTicket.getSeatNumber());
                            DateDeparture.setText("Дата и время вылета: " + airTicket.getFlights().getDateAndTimeDeparture().replace("T", " "));
                            DateArrival.setText("Дата и время прилета: " + airTicket.getFlights().getDateAndTimeArrival().replace("T", " "));
                            //22
                            if (DateDeparture.getText().toString() == DateArrival.getText().toString()){
                                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show();
                            }

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
                        catch(Exception e){
                            handleError();
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ArrayList<AirTicket>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
                    APIInterface apiInterface;
                    apiInterface = buildRequest().create(APIInterface.class);
                    Call<User> userId = apiInterface.userId(RequestBuilder.idClient);
                    userId.enqueue(new Callback<User>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            try{
                                BuyActivity.id = idAir;
                                BuyActivity.cost = cost;
                                BuyActivity.airTicket = new AirTicket(airTickett.getIdAirTicket(), airTickett.getFlightId(), airTickett.getRateAirlaneId(), airTickett.getCostOfAirTicket(),
                                        airTickett.isHaveTransfer(), airTickett.getSeatNumber(), true);
                                Intent intent = new Intent(getApplicationContext(), BuyActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            catch (Exception e){
                                handleError();
                            }
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
                APIInterface apiInterface;
                apiInterface = buildRequest().create(APIInterface.class);
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

    private void Text(){
        Cities = findViewById(R.id.Cities);
        Price = findViewById(R.id.Price);
        NumberSeat = findViewById(R.id.NumberSeat);
        DateDeparture = findViewById(R.id.DateDeparture);
        DateArrival = findViewById(R.id.DateArrival);
        AviaCompany = findViewById(R.id.AviaCompany);
        NamePlane = findViewById(R.id.NamePlane);
        NumberFlight = findViewById(R.id.NumberFlight);
        NameRate = findViewById(R.id.NameRate);
        DateDelay = findViewById(R.id.DateDelay);
    }

    private void Buttons(){
        Transfers.findViewById(R.id.Transfers);
        Refresh.findViewById(R.id.Refresh);
        Back.findViewById(R.id.Back);
        Check.findViewById(R.id.Check);
        Buy.findViewById(R.id.Buy);
        MoreAboutRate.findViewById(R.id.MoreAboutRate);
    }

    Retrofit buildRequest(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."+
                                        "eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsY"+
                                        "Wltcy9uYW1lIjoiQ2xpZW50MjExIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9"+
                                        "3cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiMSIsImlzcyI6Ik15QXV0aFNlcnZ"+
                                        "lciIsImF1ZCI6Ik15QXV0aENsaWVudCJ9.cOqnCLur44pf3v2oUpMrB1xmDjlay8VIz5DSAftNhas")
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7023/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit;
    }

    private void handleError() {
    }
}
