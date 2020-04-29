/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tintt.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AccountError implements Serializable{
    private String emailError;
    private String passwordError;
    private String fullnameError;
    private String phoneError;
    private String dateCreateError;
    private String addressError;
    private String roleError;
    private String statusError;

    public AccountError() {
    }

    public AccountError(String emailError, String passwordError, String fullnameError, String phoneError, String dateCreateError, String addressError, String roleError, String statusError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.fullnameError = fullnameError;
        this.phoneError = phoneError;
        this.dateCreateError = dateCreateError;
        this.addressError = addressError;
        this.roleError = roleError;
        this.statusError = statusError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getDateCreateError() {
        return dateCreateError;
    }

    public void setDateCreateError(String dateCreateError) {
        this.dateCreateError = dateCreateError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }
}
