/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import model.MauSac;
import util.DBConnect;

/**
 *
 * @author Admin BVCN88 02
 */
public class LoginRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;

    public boolean isExistedUser(String userName, String passWord) {
        try {
            conn = DBConnect.getConnection();
            sql = "Select UserName, PassWord from TaiKhoan where UserName=? and PassWord=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, passWord);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
