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
public class AreaDTO implements Serializable{
    private int id;
    private String location;

    public AreaDTO() {
    }

    public AreaDTO(String location) {
        this.location = location;
    }
    
    public AreaDTO(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
