/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MauSac;
import model.TaiKhoan;
import util.DBConnect;

/**
 *
 * @author Admin BVCN88 02
 */
public class DangNhapRepository {

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

    public String getTenNhanVien(String username) {
        String tenNhanVien = null;
        try {
            conn = DBConnect.getConnection();
            sql = "Select NV.HoTen From TaiKhoan TK Join NhanVien NV \n"
                    + "ON TK.MaTK = NV.MaTK where TK.UserName = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                tenNhanVien = rs.getString(1);
            }
            return tenNhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
