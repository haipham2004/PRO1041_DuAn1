/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import util.DBConnect1111111;

/**
 *
 * @author Admin
 */
public class NhanVienRepository {
    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<NhanVien> listNhanVien = new ArrayList<>();

    public List<NhanVien> getAll() {
        try {
            conn = DBConnect1111111.getConnection();
            sql = "select MaNV, MaTK, HoTen, GioiTinh, DiaChi, SoDienThoai, CCCD, NgayVaoLam, TrangThai, Anh from NhanVien";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getBoolean(9), rs.getString(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }
}
