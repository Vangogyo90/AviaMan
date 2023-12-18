package com.example.aviaappmobile.Tables;

public class PassportUser {
    private int idPassportUser;
    private String passportSeries;
    private String passportNumber;
    private String surname;
    private String name;
    private String patronymic;
    private String dateOfBirth;
    private int userId;
    private User users;

    public PassportUser (int idPassportUser, String passportSeries, String passportNumber, String surname, String name, String patronymic, String dateOfBirth, int userId, User users){
        this.idPassportUser = idPassportUser;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
        this.users = users;
    }

    public PassportUser (int idPassportUser, String passportSeries, String passportNumber, String surname, String name, String patronymic, String dateOfBirth, int userId){
        this.idPassportUser = idPassportUser;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public PassportUser (String passportSeries, String passportNumber, String surname, String name, String patronymic, String dateOfBirth, int userId){
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public int getIdPassportUser() {
        return idPassportUser;
    }

    public void setIdPassportUser(int idPassportUser) {
        this.idPassportUser = idPassportUser;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
