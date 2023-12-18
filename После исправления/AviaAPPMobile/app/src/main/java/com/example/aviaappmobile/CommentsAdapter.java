package com.example.aviaappmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aviaappmobile.Tables.AnswerOnTexted;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<AnswerOnTexted> answerOnTexteds;

    CommentsAdapter(Context context, List<AnswerOnTexted> answerOnTexteds){
        this.answerOnTexteds = answerOnTexteds;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    @NotNull
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.itemcom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position) {
        AnswerOnTexted answerOnTexted = answerOnTexteds.get(position);
        holder.Comment.setText("Жалоба или предложение:" + answerOnTexted.getComplaintsAndSuggestions().getTextComplaintsOrSuggestions());
        holder.Answer.setText("Ответ: " + answerOnTexted.getAnswerComplaintsAndSuggestions().getAnswerComplaintsOrSuggestions());
    }

    public int getItemCount() {return answerOnTexteds.size();}
    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView Comment;
        final TextView Answer;
        ViewHolder(View view){
            super(view);
            Comment = view.findViewById(R.id.Comment);
            Answer = view.findViewById(R.id.Answer);
        }
    }
}
