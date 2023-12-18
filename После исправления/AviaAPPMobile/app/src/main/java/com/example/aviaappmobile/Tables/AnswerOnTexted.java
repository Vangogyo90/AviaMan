package com.example.aviaappmobile.Tables;

public class AnswerOnTexted {
    private int idAnswerOnTexted;
    private int complaintsAndSuggestionsId;
    private int answerComplaintsAndSuggestionsId;
    private ComplaintsAndSuggestion complaintsAndSuggestions;
    private AnswerComplaintsAndSuggestion answerComplaintsAndSuggestions;

    public AnswerOnTexted (int idAnswerOnTexted, int complaintsAndSuggestionsId, int answerComplaintsAndSuggestionsId, ComplaintsAndSuggestion complaintsAndSuggestions,
                           AnswerComplaintsAndSuggestion answerComplaintsAndSuggestions){
        this.idAnswerOnTexted = idAnswerOnTexted;
        this.complaintsAndSuggestionsId = complaintsAndSuggestionsId;
        this.answerComplaintsAndSuggestionsId = answerComplaintsAndSuggestionsId;
        this.complaintsAndSuggestions = complaintsAndSuggestions;
        this.answerComplaintsAndSuggestions = answerComplaintsAndSuggestions;
    }

    public AnswerOnTexted (int complaintsAndSuggestionsId){
        this.complaintsAndSuggestionsId = complaintsAndSuggestionsId;
    }

    public int getIdAnswerOnTexted() {
        return idAnswerOnTexted;
    }

    public void setIdAnswerOnTexted(int idAnswerOnTexted) {
        this.idAnswerOnTexted = idAnswerOnTexted;
    }

    public int getComplaintsAndSuggestionsId() {
        return complaintsAndSuggestionsId;
    }

    public void setComplaintsAndSuggestionsId(int complaintsAndSuggestionsId) {
        this.complaintsAndSuggestionsId = complaintsAndSuggestionsId;
    }

    public int getAnswerComplaintsAndSuggestionsId() {
        return answerComplaintsAndSuggestionsId;
    }

    public void setAnswerComplaintsAndSuggestionsId(int answerComplaintsAndSuggestionsId) {
        this.answerComplaintsAndSuggestionsId = answerComplaintsAndSuggestionsId;
    }

    public ComplaintsAndSuggestion getComplaintsAndSuggestions() {
        return complaintsAndSuggestions;
    }

    public void setComplaintsAndSuggestions(ComplaintsAndSuggestion complaintsAndSuggestions) {
        this.complaintsAndSuggestions = complaintsAndSuggestions;
    }

    public AnswerComplaintsAndSuggestion getAnswerComplaintsAndSuggestions() {
        return answerComplaintsAndSuggestions;
    }

    public void setAnswerComplaintsAndSuggestions(AnswerComplaintsAndSuggestion answerComplaintsAndSuggestions) {
        this.answerComplaintsAndSuggestions = answerComplaintsAndSuggestions;
    }
}
