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
import javax.naming.NamingException;
import tintt.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class StatusDAO implements Serializable{
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
    
    public int getStatusIDByDescription(String statusDescription) throws SQLException, NamingException {
        int result = -1;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Id FROM tbl_Status WHERE Description = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, statusDescription);
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

    public String getStatusDescriptionByID(int ID) throws SQLException, NamingException {
        String result = "";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Description FROM tbl_Status WHERE Id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, ID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("Description");
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
