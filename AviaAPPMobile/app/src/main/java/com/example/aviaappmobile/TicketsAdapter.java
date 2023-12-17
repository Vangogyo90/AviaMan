package com.example.aviaappmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aviaappmobile.Tables.AirTicket;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<AirTicket> airTickets;

    TicketsAdapter(Context context, List<AirTicket> airTickets){
        this.airTickets = airTickets;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    @NotNull
    public TicketsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketsAdapter.ViewHolder holder, int position) {
        AirTicket airTicket = airTickets.get(position);
        holder.FlightCities.setText(airTicket.getFlights().getDepartureCity().getNameCity() + "=>" + airTicket.getFlights().getArrivalCity().getNameCity());
        holder.PriceText.setText(Double.toString(airTicket.getRateAirlanes().getRates().getCostOfRate() + airTicket.getCostOfAirTicket()) + "руб.");
        holder.AirlaneText.setText(airTicket.getFlights().getAirlines().getNameAirline());
    }

    public int getItemCount() {return airTickets.size();}
    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView FlightCities;
        final TextView PriceText;
        final TextView AirlaneText;
        final ConstraintLayout more;
        ViewHolder(View view){
            super(view);
            FlightCities = view.findViewById(R.id.FlightCities);
            PriceText = view.findViewById(R.id.PriceText);
            AirlaneText = view.findViewById(R.id.AirlaneText);
            more = view.findViewById(R.id.more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    TicketActivity.pos = position;
                    TicketActivity.MoreBut();
                }
            });
        }
    }
}
