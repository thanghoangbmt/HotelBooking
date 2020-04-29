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
import javax.naming.NamingException;
import tintt.dtos.AreaDTO;
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

    public HashMap<String, ArrayList<RoomDTO>> getListRoomAvailable(String search, String area,
            String checkIn, String checkOut) throws NamingException, SQLException, ParseException {
        HashMap<String, ArrayList<RoomDTO>> result = new HashMap<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT r1.Id, r1.TypeId, r1.Price, r1.HotelId, "
                        + "r1.Amount - b1.Amount AS AvailableAmount, h1.Name AS HotelName, "
                        + "a1.Location FROM tbl_Room r1, (SELECT RoomId, Amount "
                        + "FROM tbl_BookingDetails "
                        + "WHERE ? BETWEEN CheckInDate AND CheckOutDate "
                        + "OR ? BETWEEN CheckInDate AND CheckOutDate) b1, tbl_Hotel h1, tbl_Area a1 "
                        + "WHERE r1.Amount - b1.Amount >= 0"
                        + "AND b1.RoomId = r1.Id "
                        + "AND r1.HotelId = h1.Id "
                        + "AND h1.AreaID = a1.Id "
                        + "AND r1.StatusId = 1";

                if (!search.isEmpty()) {
                    sql = sql + "AND h1.Name LIKE '%" + search + "%'";
                }

                if (!area.equals("All")) {
                    sql = sql + "AND a1.Location = " + area;
                }

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
                    int typeID = rs.getInt("TypeId");
                    double price = rs.getDouble("Price");
                    int hotelID = rs.getInt("HotelId");
                    int availableAmount = rs.getInt("AvailableAmount");
                    System.out.println("");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
