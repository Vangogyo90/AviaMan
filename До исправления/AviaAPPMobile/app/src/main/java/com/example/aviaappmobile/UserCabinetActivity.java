package com.example.aviaappmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aviaappmobile.Tables.PassportUser;
import com.example.aviaappmobile.Tables.User;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class UserCabinetActivity extends AppCompatActivity {

    private int id;

    EditText PassportSeries;
    EditText Passport_Number;
    EditText Surname;
    EditText Name;
    EditText Patronymic;
    EditText Date_Of_Birth;
    TextView Access;
    Button Retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cabinet);
        getSupportActionBar().hide();

        PassportSeries = findViewById(R.id.PassportSeries);
        Passport_Number = findViewById(R.id.Passport_Number);
        Surname = findViewById(R.id.Surname);
        Name = findViewById(R.id.Name);
        Patronymic = findViewById(R.id.Patronymic);
        Date_Of_Birth = findViewById(R.id.Date_Of_Birth);
        Access = findViewById(R.id.Access);
        Retry = findViewById(R.id.Retry);

        Button AddChange = findViewById(R.id.AddChange);
        AddChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                if (PassportSeries.getText().toString() != "" && Passport_Number.getText().toString() != "" && Surname.getText().toString() != "" &&
                Name.getText().toString() != "" && Patronymic.getText().toString() != "" && Date_Of_Birth.getText().toString() != "") {
                    Call<PassportUser> postPassportUser = apiInterface.postPassportUser(new PassportUser(PassportSeries.getText().toString(), Passport_Number.getText().toString(),
                            Surname.getText().toString(), Name.getText().toString(), Patronymic.getText().toString(), Date_Of_Birth.getText().toString(), RequestBuilder.idClient));
                    postPassportUser.enqueue(new Callback<PassportUser>() {
                        @Override
                        @EverythingIsNonNull
                        public void onResponse(Call<PassportUser> call, Response<PassportUser> response) {
                            AddChange.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Вы успешно сохранили паспорт", Toast.LENGTH_SHORT).show();
                            id = response.body().getIdPassportUser();
                            PassportSeries.setEnabled(false);
                            Passport_Number.setEnabled(false);
                            Surname.setEnabled(false);
                            Name.setEnabled(false);
                            Patronymic.setEnabled(false);
                            Date_Of_Birth.setEnabled(false);
                        }

                        @Override
                        @EverythingIsNonNull
                        public void onFailure(Call<PassportUser> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        Date_Of_Birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserCabinetActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Date_Of_Birth.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface;
                apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
                Call <Void> deletePassportUsers = apiInterface.deletePassportUsers(id);
                deletePassportUsers.enqueue(new Callback<Void>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Retry.setVisibility(View.GONE);
                        AddChange.setVisibility(View.VISIBLE);
                        PassportSeries.setEnabled(true);
                        Passport_Number.setEnabled(true);
                        Surname.setEnabled(true);
                        Name.setEnabled(true);
                        Patronymic.setEnabled(true);
                        Date_Of_Birth.setEnabled(true);
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        APIInterface apiInterface;
        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call <ArrayList<PassportUser>> getPassportUser = apiInterface.getPassportUser(RequestBuilder.idClient);
        getPassportUser.enqueue(new Callback<ArrayList<PassportUser>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<PassportUser>> call, Response<ArrayList<PassportUser>> response) {
                ArrayList<PassportUser> passportUsers = response.body();
                if (passportUsers.size() > 0){
                    PassportUser passportUser = passportUsers.get(0);
                    AddChange.setVisibility(View.GONE);
                    PassportSeries.setText(new String(DatatypeConverter.parseBase64Binary(passportUser.getPassportSeries())));
                    Passport_Number.setText(new String(DatatypeConverter.parseBase64Binary(passportUser.getPassportNumber())));
                    Surname.setText(new String(DatatypeConverter.parseBase64Binary(passportUser.getSurname())));
                    Name.setText(new String(DatatypeConverter.parseBase64Binary(passportUser.getName())));
                    Patronymic.setText(new String(DatatypeConverter.parseBase64Binary(passportUser.getPatronymic())));
                    Date_Of_Birth.setText(passportUser.getDateOfBirth());
                    id = passportUser.getIdPassportUser();
                    PassportSeries.setEnabled(false);
                    Passport_Number.setEnabled(false);
                    Surname.setEnabled(false);
                    Name.setEnabled(false);
                    Patronymic.setEnabled(false);
                    Date_Of_Birth.setEnabled(false);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<PassportUser>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        apiInterface = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<User> userId = apiInterface.userId(RequestBuilder.idClient);
        userId.enqueue(new Callback<User>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Access.setText(user.isVerifiedAccount() ? "Ваш аккаунт подтвержден" : "Ваш аккаунт не подтвержден");
                if (user.isVerifiedAccount())
                    Retry.setVisibility(View.GONE);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Button BuyingTickets = findViewById(R.id.BuyingTickets);
        BuyingTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuyingTicketsActivity.class);
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

        Button FeedBack = findViewById(R.id.FeedBack);
        FeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedBackActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}