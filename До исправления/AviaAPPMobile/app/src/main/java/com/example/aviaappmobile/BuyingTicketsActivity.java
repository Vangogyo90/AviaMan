package com.example.aviaappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aviaappmobile.Tables.AirTicket;
import com.example.aviaappmobile.Tables.Purchases;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BuyingTicketsActivity extends AppCompatActivity {

    private static BuyingTicketsActivity instance;
    public static int pos;
    ArrayList<AirTicket> airTickets = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_tickets);
        getSupportActionBar().hide();

        RecyclerView list = findViewById(R.id.list);

        Button Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserCabinetActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView Warn = findViewById(R.id.Warn);

        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<Purchases>> getPurchasesListALL = apiInterface.getPurchasesListALL(RequestBuilder.idClient);
        getPurchasesListALL.enqueue(new Callback<ArrayList<Purchases>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<Purchases>> call, Response<ArrayList<Purchases>> response) {
                if (response.isSuccessful()){
                    list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    list.setHasFixedSize(true);
                    ArrayList<Purchases> purchases = response.body();
                    if (purchases.size() == 0){
                        Warn.setVisibility(View.VISIBLE);
                        Warn.setText("Вы еще ничего не купили(");
                    }
                    else{
                        Warn.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < purchases.size(); i++){
                        airTickets.add(purchases.get(i).getAirTickets());
                    }
                    TicketsAdapter2 adapter = new TicketsAdapter2(getApplicationContext(), airTickets);
                    list.setAdapter(adapter);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<Purchases>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        instance = this;
    }

    public static int GetId(ArrayList<AirTicket> heroes){
        int id = heroes.get(pos).getIdAirTicket();
        return id;
    }

    public static BuyingTicketsActivity getInstance() {
        return instance;
    }

    public static void MoreBut(){
        MoreBuyingTicketsActivity.id = GetId(instance.airTickets);
        Intent intent = new Intent(getInstance(), MoreBuyingTicketsActivity.class);
        getInstance().startActivity(intent);
        getInstance().finish();
    }
}