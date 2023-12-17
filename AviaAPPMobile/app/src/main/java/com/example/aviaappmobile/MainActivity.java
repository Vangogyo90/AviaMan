package com.example.aviaappmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.Cities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    int day, month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        AutoCompleteTextView whereFrom = findViewById(R.id.WhereFrom);
        AutoCompleteTextView where = findViewById(R.id.Where);

        EditText when = findViewById(R.id.When);

        Button searchButton = findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!whereFrom.getText().toString().equals(where.getText().toString()) && !whereFrom.getText().toString().equals("") && !where.getText().toString().equals("") && !when.getText().toString().equals("")){
                    TicketActivity.wherefrom = whereFrom.getText().toString();
                    TicketActivity.where = where.getText().toString();

                    String str = String.format("%04d-%02d-%02dT10:10:10", year, month, day);;
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                    TicketActivity.when = dateTime;

                    Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        when.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                                when.setText(dayOfMonth + "." + (month1 + 1) + "." + year1);
                                year = year1;
                                month = month1 + 1;
                                day = dayOfMonth;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<Cities>> getCityList = apiInterface.getCityList();

        getCityList.enqueue(new Callback<ArrayList<Cities>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<Cities>> call, Response<ArrayList<Cities>> response) {
                ArrayList<Cities> citiesList  = response.body();
                ArrayList<String> cities = new ArrayList<>();
                for (Cities city : citiesList) {
                    cities.add(city.getNameCity());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, cities);
                whereFrom.setAdapter(adapter);
                where.setAdapter(adapter);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<Cities>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}