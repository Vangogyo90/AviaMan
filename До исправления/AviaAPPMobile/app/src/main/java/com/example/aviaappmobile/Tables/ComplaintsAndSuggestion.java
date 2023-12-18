package com.example.aviaappmobile.Tables;

public class ComplaintsAndSuggestion {
    private int idComplaintsAndSuggestions;
    private String textComplaintsOrSuggestions;
    private int userId;

    public ComplaintsAndSuggestion (int idComplaintsAndSuggestions, String textComplaintsOrSuggestions, int userId){
        this.idComplaintsAndSuggestions = idComplaintsAndSuggestions;
        this.textComplaintsOrSuggestions = textComplaintsOrSuggestions;
        this.userId = userId;
    }

    public ComplaintsAndSuggestion (String textComplaintsOrSuggestions, int userId){
        this.textComplaintsOrSuggestions = textComplaintsOrSuggestions;
        this.userId = userId;
    }

    public int getIdComplaintsAndSuggestions() {
        return idComplaintsAndSuggestions;
    }

    public void setIdComplaintsAndSuggestions(int idComplaintsAndSuggestions) {
        this.idComplaintsAndSuggestions = idComplaintsAndSuggestions;
    }

    public String getTextComplaintsOrSuggestions() {
        return textComplaintsOrSuggestions;
    }

    public void setTextComplaintsOrSuggestions(String textComplaintsOrSuggestions) {
        this.textComplaintsOrSuggestions = textComplaintsOrSuggestions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
