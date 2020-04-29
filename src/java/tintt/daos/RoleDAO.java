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
public class RoleDAO implements Serializable{

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

    public int getRoleIDByDescription(String roleDescription) throws SQLException, NamingException {
        int result = -1;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Id FROM tbl_Role WHERE Description = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, roleDescription);
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
}
