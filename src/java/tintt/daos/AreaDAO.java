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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tintt.dtos.AreaDTO;
import tintt.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class AreaDAO implements Serializable {

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

    public int getAreaIDByLocation(String location) throws SQLException, NamingException {
        int result = -1;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Id FROM tbl_Area WHERE Location = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, location);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Id");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getAreaLocationByID(int Id) throws SQLException, NamingException {
        String result = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Location FROM tbl_Area WHERE Id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("Location");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<AreaDTO> getListArea() throws NamingException, SQLException {
        List<AreaDTO> result = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Id, Location FROM tbl_Area";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int areaID = rs.getInt("Id");
                    String areaLocation = rs.getString("Location");
                    AreaDTO areaDTO = new AreaDTO(areaID, areaLocation);
                    result.add(areaDTO);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
