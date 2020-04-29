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
public class AccountDTO implements Serializable {

    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String dateCreate;
    private String address;
    private String role;
    private String status;

    public AccountDTO() {
    }

    public AccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountDTO(String email, String password, String fullname, String phone, String address, String role, String status) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.status = status;
    }
    
    public AccountDTO(String email, String password, String fullname, String phone, String dateCreate, String address, String role, String status) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.dateCreate = dateCreate;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
