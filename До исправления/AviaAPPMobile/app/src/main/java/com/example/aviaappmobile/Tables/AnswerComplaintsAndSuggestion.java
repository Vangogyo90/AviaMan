package com.example.aviaappmobile.Tables;

public class AnswerComplaintsAndSuggestion {
    private int idAnswerComplaintsAndSuggestions;
    private String answerComplaintsOrSuggestions;
    private int userId;

    public AnswerComplaintsAndSuggestion (int idAnswerComplaintsAndSuggestions, String answerComplaintsOrSuggestions, int userId){
        this.idAnswerComplaintsAndSuggestions = idAnswerComplaintsAndSuggestions;
        this.answerComplaintsOrSuggestions = answerComplaintsOrSuggestions;
        this.userId = userId;
    }

    public int getIdAnswerComplaintsAndSuggestions() {
        return idAnswerComplaintsAndSuggestions;
    }

    public void setIdAnswerComplaintsAndSuggestions(int idAnswerComplaintsAndSuggestions) {
        this.idAnswerComplaintsAndSuggestions = idAnswerComplaintsAndSuggestions;
    }

    public String getAnswerComplaintsOrSuggestions() {
        return answerComplaintsOrSuggestions;
    }

    public void setAnswerComplaintsOrSuggestions(String answerComplaintsOrSuggestions) {
        this.answerComplaintsOrSuggestions = answerComplaintsOrSuggestions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
