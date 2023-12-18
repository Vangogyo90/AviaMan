package com.example.aviaappmobile;

import com.example.aviaappmobile.Tables.AirTicket;
import com.example.aviaappmobile.Tables.AnswerOnTexted;
import com.example.aviaappmobile.Tables.Cities;
import com.example.aviaappmobile.Tables.ComplaintsAndSuggestion;
import com.example.aviaappmobile.Tables.FlightDelay;
import com.example.aviaappmobile.Tables.FlightTransfer;
import com.example.aviaappmobile.Tables.PassportUser;
import com.example.aviaappmobile.Tables.Purchases;
import com.example.aviaappmobile.Tables.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {
    //Cities
    @GET("Cities")
    Call<ArrayList<Cities>> getCityList();

    //AirTickets
    @GET("AirTickets/GetData/{wherefrom}/{where}/{when}")
    Call<ArrayList<AirTicket>> getAirTicketList(@Path("wherefrom") String wherefrom, @Path("where") String where, @Path("when") LocalDateTime when);

    @GET("AirTickets/GetDataFULL/{id}")
    Call<ArrayList<AirTicket>> getAirTicketListALL(@Path("id") int id);

    @GET("AirTickets/GetDataAll")
    Call<ArrayList<AirTicket>> getAirTicketListAll();

    @PUT("AirTickets/{id}")
    Call<AirTicket> putAirTickets(@Path("id") int id, @Body AirTicket airTicket);

    //User
    @POST("Users")
    Call<User> postUser(@Body User users);

    @GET("Users/{id}")
    Call<User> userId(@Path("id") int id);

    @GET("Users/{Login}/{Password}")
    Call<String> getAuthorization(@Path("Login") String Login, @Path("Password") String Password);

    @GET("Users/UserIDByLogin/{Login}")
    Call<Integer> getIdUser(@Path("Login") String Login);

    //Transfers
    @GET("FlightTransfers/GetData/{FlightsId}")
    Call<ArrayList<FlightTransfer>> getTransfers(@Path("FlightsId") int FlightsId);

    //PassportUser
    @GET("PassportUsers/GetData/{id}")
    Call<ArrayList<PassportUser>> getPassportUser(@Path("id") int id);

    @POST("PassportUsers")
    Call<PassportUser> postPassportUser(@Body PassportUser passportUsers);

    @PUT("PassportUsers/{id}")
    Call<PassportUser> putPassportUser(@Path("id") int id, @Body PassportUser passportUsers);

    @DELETE("PassportUsers/{id}")
    Call<Void> deletePassportUsers(@Path("id") int id);

    //Purchase
    @GET("Purchases/GetDataFULL/{id}")
    Call<ArrayList<Purchases>> getPurchasesListALL(@Path("id") int id);

    @POST("Purchases")
    Call<Purchases> postPurchases(@Body Purchases purchases);

    //FlightDelay
    @GET("FlightDelays/GetData/{id}")
    Call<ArrayList<FlightDelay>> getDelay(@Path("id") int id);

    //Answers
    @GET("AnswerOnTexteds/GetDataUser/{id}")
    Call<ArrayList<AnswerOnTexted>> getAnswerOnTexted(@Path("id") int id);

    //Comments
    @POST("ComplaintsAndSuggestions")
    Call<ComplaintsAndSuggestion> postComplaintsAndSuggestion(@Body ComplaintsAndSuggestion complaintsAndSuggestion);
}
