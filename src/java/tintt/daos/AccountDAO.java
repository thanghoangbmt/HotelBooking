/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tintt.daos;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tintt.dtos.AccountDTO;
import tintt.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class AccountDAO implements Serializable {
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
    
    public AccountDTO checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        AccountDTO result = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.Fullname, a.Phone, a.Address, r.Description as RoleDescription, "
                        + "s.Description as StatusDescription "
                        + "FROM tbl_Account a, tbl_Role r, tbl_Status s "
                        + "WHERE Email = ? AND Password = ? "
                        + "AND s.Description = ? "
                        + "AND a.RoleID = r.ID AND a.StatusID = s.ID ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, "Active");
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("Fullname");
                    String phone = rs.getString("Phone");
                    String address = rs.getString("Address");
                    String role = rs.getString("RoleDescription");
                    String status = rs.getString("StatusDescription");
                    result = new AccountDTO();
                    result.setEmail(email);
                    result.setFullname(fullname);
                    result.setPhone(phone);
                    result.setAddress(address);
                    result.setRole(role);
                    result.setStatus(status);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }
}
