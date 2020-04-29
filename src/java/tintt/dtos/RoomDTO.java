/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tintt.dtos;

/**
 *
 * @author Admin
 */
public class RoomDTO {

    private int id;
    private String type;
    private int amount;
    private double price;
    private HotelDTO hotel;
    private String status;

    public RoomDTO() {
    }

    public RoomDTO(int id, String type, int amount, double price, HotelDTO hotel) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.hotel = hotel;
    }

    public RoomDTO(int id, String type, int amount, double price, HotelDTO hotel, String status) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.hotel = hotel;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
