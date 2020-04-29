/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tintt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import tintt.dtos.AreaDTO;
import tintt.dtos.HotelDTO;
import tintt.dtos.RoomDTO;
import tintt.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class RoomDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<RoomDTO> getListRoomAvailable(String search, String area, int amount,
            String checkIn, String checkOut) throws NamingException, SQLException, ParseException {
        List<RoomDTO> result = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT r1.Id, t1.Type, r1.Price, r1.HotelId, "
                        + "r1.Amount - b1.Amount AS AvailableAmount, h1.Name AS HotelName, "
                        + "a1.Location FROM tbl_Room r1, (SELECT RoomId, Amount "
                        + "FROM tbl_BookingDetails "
                        + "WHERE ? BETWEEN CheckInDate AND CheckOutDate "
                        + "OR ? BETWEEN CheckInDate AND CheckOutDate) b1, tbl_Hotel h1, tbl_Area a1, tbl_Type t1 "
                        + "WHERE b1.RoomId = r1.Id "
                        + "AND r1.HotelId = h1.Id "
                        + "AND h1.AreaID = a1.Id "
                        + "AND r1.StatusId = 1 "
                        + "AND r1.TypeID = t1.Id";

                if (!search.isEmpty()) {
                    sql = sql + " AND h1.Name LIKE '%" + search + "%'";
                }

                if (!area.equals("All")) {
                    sql = sql + " AND a1.Location = " + area;
                }

                sql = sql + " AND (r1.Amount - b1.Amount) >= " + amount;

                ps = conn.prepareStatement(sql);
                if (!checkIn.isEmpty()) {

                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date dateJava = sdf1.parse(checkIn);
                    java.sql.Date sqlCheckInDate = new java.sql.Date(dateJava.getTime());

                    ps.setDate(1, sqlCheckInDate);
                }

                if (!checkOut.isEmpty()) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date dateJava = sdf1.parse(checkOut);
                    java.sql.Date sqlCheckOutDate = new java.sql.Date(dateJava.getTime());

                    ps.setDate(2, sqlCheckOutDate);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt("Id");
                    String type = rs.getString("Type");
                    double price = rs.getDouble("Price");
                    int hotelID = rs.getInt("HotelId");
                    int availableAmount = rs.getInt("AvailableAmount");
                    String hotelName = rs.getString("HotelName");
                    String location = rs.getString("Location");

                    AreaDTO areaDTO = new AreaDTO(location);
                    HotelDTO hotelDTO = new HotelDTO(hotelID, hotelName, areaDTO);
                    RoomDTO roomDTO = new RoomDTO(Id, type, availableAmount, price, hotelDTO);
                    result.add(roomDTO);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<RoomDTO> getListRoomAvailable2(String search, String area, int amount, String sCheckInDate, String sCheckOutDate, List<RoomDTO> listRoom) throws NamingException, SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT r.Id, t.Type, r.Price, r.HotelId, "
                        + "r.Amount AS AvailableAmount, h.Name AS HotelName, "
                        + "a.Location "
                        + "FROM tbl_Room r, tbl_Hotel h, tbl_Area a, tbl_Type t "
                        + "WHERE r.Id NOT IN "
                        + "(SELECT r1.Id "
                        + "FROM tbl_Room r1, (SELECT RoomId, Amount "
                        + "FROM tbl_BookingDetails "
                        + "WHERE '2020-04-27' BETWEEN CheckInDate AND CheckOutDate "
                        + "OR '2020-05-11' BETWEEN CheckInDate AND CheckOutDate) b1, "
                        + "tbl_Hotel h1, tbl_Area a1 "
                        + "WHERE b1.RoomId = r1.Id "
                        + "AND r1.HotelId = h1.Id "
                        + "AND h1.AreaID = a1.Id "
                        + "AND r1.StatusId = 1) "
                        + "AND r.TypeId = t.Id "
                        + "AND r.HotelId = h.Id "
                        + "AND h.AreaID = a.Id";
                        

//                        + "FROM tbl_Room r1, tbl_Hotel h1, tbl_Area a1, tbl_Type t1 "
                        //                        + "WHERE r1.Id NOT IN (SELECT RoomId FROM tbl_BookingDetails) "
                        //                        + "AND r1.HotelId = h1.Id "
                        //                        + "AND h1.AreaID = a1.Id "
                        //                        + "AND r1.StatusId = 1 "
                        //                        + "AND r1.TypeID = t1.Id";
                if (!search.isEmpty()) {
                    sql = sql + " AND h1.Name LIKE '%" + search + "%'";
                }

                if (!area.equals("All")) {
                    sql = sql + " AND a1.Location = " + area;
                }

                sql = sql + " AND r.Amount >= " + amount;
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt("Id");
                    String type = rs.getString("Type");
                    double price = rs.getDouble("Price");
                    int hotelID = rs.getInt("HotelId");
                    int availableAmount = rs.getInt("AvailableAmount");
                    String hotelName = rs.getString("HotelName");
                    String location = rs.getString("Location");

                    AreaDTO areaDTO = new AreaDTO(location);
                    HotelDTO hotelDTO = new HotelDTO(hotelID, hotelName, areaDTO);
                    RoomDTO roomDTO = new RoomDTO(Id, type, availableAmount, price, hotelDTO);
                    listRoom.add(roomDTO);
                }
            }
        } finally {
            closeConnection();
        }
        return listRoom;
    }
}
