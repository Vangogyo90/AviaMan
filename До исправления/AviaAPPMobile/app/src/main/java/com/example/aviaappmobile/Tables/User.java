package com.example.aviaappmobile.Tables;

public class User {
    private int idUser;
    private String loginUser;
    private String loginPassword;
    private boolean verifiedAccount;
    private String telephoneNumberUser;
    private String eMail;
    private String salt;
    private int roleId;
    private Role roles;

    public User (int idUser, String loginUser, String loginPassword, boolean verifiedAccount, String telephoneNumberUser, String eMail, String salt, int roleId,
                 Role roles){
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.loginPassword = loginPassword;
        this.verifiedAccount = verifiedAccount;
        this.telephoneNumberUser = telephoneNumberUser;
        this.eMail = eMail;
        this.salt = salt;
        this.roleId = roleId;
        this.roles = roles;
    }

    public User (String loginUser, String loginPassword, String telephoneNumberUser, String eMail, int roleId){
        this.loginUser = loginUser;
        this.loginPassword = loginPassword;
        this.telephoneNumberUser = telephoneNumberUser;
        this.eMail = eMail;
        this.roleId = roleId;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public boolean isVerifiedAccount() {
        return verifiedAccount;
    }

    public void setVerifiedAccount(boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }

    public String getTelephoneNumberUser() {
        return telephoneNumberUser;
    }

    public void setTelephoneNumberUser(String telephoneNumberUser) {
        this.telephoneNumberUser = telephoneNumberUser;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
