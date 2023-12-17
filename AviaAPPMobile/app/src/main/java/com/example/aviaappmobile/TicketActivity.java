package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aviaappmobile.Tables.AirTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TicketActivity extends AppCompatActivity {

    public static String wherefrom, where;
    public static LocalDateTime when;
    private static TicketActivity instance;
    public static int pos;
    ArrayList<AirTicket> airTickets;
    boolean i = true;
    boolean all = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        getSupportActionBar().hide();

        RecyclerView list = findViewById(R.id.list);

        ImageButton Reg = findViewById(R.id.Reg);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton ExpensiveOrChipe = findViewById(R.id.ExpensiveOrChipe);
        ExpensiveOrChipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call<ArrayList<AirTicket>> getAirTicketListAll = apiInterface.getAirTicketListAll();
                getAirTicketListAll.enqueue(new Callback<ArrayList<AirTicket>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                        if (response.isSuccessful()) {
                            list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            list.setHasFixedSize(true);
                            ArrayList<AirTicket> airTicket = response.body();
                            Collections.sort(airTicket, new Comparator<AirTicket>() {
                                @Override
                                public int compare(AirTicket o1, AirTicket o2) {
                                    double allPrice1 = o1.getRateAirlanes().getRates().getCostOfRate() + o1.getCostOfAirTicket();
                                    double allPrice2 = o2.getRateAirlanes().getRates().getCostOfRate() + o2.getCostOfAirTicket();
                                    if (i){
                                        i = false;
                                        return Double.compare(allPrice2, allPrice1);
                                    }
                                    else{
                                        i = true;
                                        return Double.compare(allPrice1, allPrice2);
                                    }
                                }
                            });
                            airTickets = airTicket;
                            TicketsAdapter adapter = new TicketsAdapter(getApplicationContext(), airTicket);
                            list.setAdapter(adapter);
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

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView Warn = findViewById(R.id.Warn);

        Button AllTickets = findViewById(R.id.AllTickets);
        AllTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call<ArrayList<AirTicket>> getAirTicketListAll = apiInterface.getAirTicketListAll();
                getAirTicketListAll.enqueue(new Callback<ArrayList<AirTicket>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                        if (response.isSuccessful()){
                            list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            list.setHasFixedSize(true);
                            all = true;
                            ArrayList<AirTicket> airTicket = response.body();
                            if (airTicket.size() == 0){
                                Reg.setVisibility(View.GONE);
                                Warn.setVisibility(View.VISIBLE);
                                ExpensiveOrChipe.setVisibility(View.GONE);
                                Warn.setText("По вашему запросу ничего не нашлось(");
                                AllTickets.setVisibility(View.VISIBLE);
                            }
                            else{
                                Reg.setVisibility(View.VISIBLE);
                                ExpensiveOrChipe.setVisibility(View.VISIBLE);
                                AllTickets.setVisibility(View.GONE);
                                Warn.setVisibility(View.GONE);
                            }
                            airTickets = airTicket;
                            TicketsAdapter adapter = new TicketsAdapter(getApplicationContext(), airTicket);
                            list.setAdapter(adapter);
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

        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<AirTicket>> getAirTicketList = apiInterface.getAirTicketList(wherefrom, where, when);
        getAirTicketList.enqueue(new Callback<ArrayList<AirTicket>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                if (response.isSuccessful()){
                    list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    list.setHasFixedSize(true);
                    all = false;
                    ArrayList<AirTicket> airTicket = response.body();
                    if (airTicket.size() == 0){
                        Reg.setVisibility(View.GONE);
                        Warn.setVisibility(View.VISIBLE);
                        ExpensiveOrChipe.setVisibility(View.GONE);
                        Warn.setText("По вашему запросу ничего не нашлось(");
                        AllTickets.setVisibility(View.VISIBLE);
                    }
                    else{
                        Reg.setVisibility(View.VISIBLE);
                        ExpensiveOrChipe.setVisibility(View.VISIBLE);
                        AllTickets.setVisibility(View.GONE);
                        Warn.setVisibility(View.GONE);
                    }
                    airTickets = airTicket;
                    TicketsAdapter adapter = new TicketsAdapter(getApplicationContext(), airTicket);
                    list.setAdapter(adapter);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<AirTicket>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton Refresh = findViewById(R.id.Refresh);
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (all){
                    APIInterface apiInterface;
                    apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                    Call<ArrayList<AirTicket>> getAirTicketListAll = apiInterface.getAirTicketListAll();
                    getAirTicketListAll.enqueue(new Callback<ArrayList<AirTicket>>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                            if (response.isSuccessful()){
                                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                list.setHasFixedSize(true);
                                ArrayList<AirTicket> airTicket = response.body();
                                if (airTicket.size() == 0){
                                    Reg.setVisibility(View.GONE);
                                    Warn.setVisibility(View.VISIBLE);
                                    ExpensiveOrChipe.setVisibility(View.GONE);
                                    Warn.setText("По вашему запросу ничего не нашлось(");
                                    AllTickets.setVisibility(View.VISIBLE);
                                }
                                else{
                                    Reg.setVisibility(View.VISIBLE);
                                    ExpensiveOrChipe.setVisibility(View.VISIBLE);
                                    AllTickets.setVisibility(View.GONE);
                                    Warn.setVisibility(View.GONE);
                                }
                                airTickets = airTicket;
                                TicketsAdapter adapter = new TicketsAdapter(getApplicationContext(), airTicket);
                                list.setAdapter(adapter);
                            }
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<ArrayList<AirTicket>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    APIInterface apiInterface;
                    apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                    Call<ArrayList<AirTicket>> getAirTicketList = apiInterface.getAirTicketList(wherefrom, where, when);
                    getAirTicketList.enqueue(new Callback<ArrayList<AirTicket>>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<ArrayList<AirTicket>> call, Response<ArrayList<AirTicket>> response) {
                            if (response.isSuccessful()){
                                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                list.setHasFixedSize(true);
                                ArrayList<AirTicket> airTicket = response.body();
                                if (airTicket.size() == 0){
                                    Reg.setVisibility(View.GONE);
                                    Warn.setVisibility(View.VISIBLE);
                                    ExpensiveOrChipe.setVisibility(View.GONE);
                                    Warn.setText("По вашему запросу ничего не нашлось(");
                                    AllTickets.setVisibility(View.VISIBLE);
                                }
                                else{
                                    Reg.setVisibility(View.VISIBLE);
                                    ExpensiveOrChipe.setVisibility(View.VISIBLE);
                                    AllTickets.setVisibility(View.GONE);
                                    Warn.setVisibility(View.GONE);
                                }
                                airTickets = airTicket;
                                TicketsAdapter adapter = new TicketsAdapter(getApplicationContext(), airTicket);
                                list.setAdapter(adapter);
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
        });

        instance = this;
    }

    public static int GetId(ArrayList<AirTicket> heroes){
        int id = heroes.get(pos).getIdAirTicket();
        return id;
    }

    public static TicketActivity getInstance() {
        return instance;
    }

    public static void MoreBut(){
        MoreTicketActivity.id = GetId(instance.airTickets);
        Intent intent = new Intent(getInstance(), MoreTicketActivity.class);
        getInstance().startActivity(intent);
        getInstance().finish();
    }
}