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
import model.TaiKhoan;
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
        listNhanVien.clear();
        try {
            conn = DBConnect1111111.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai,nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }
    public int insert(NhanVien nv) {
        try {
            conn = DBConnect1111111.getConnection();
            sql = "INSERT INTO NhanVien (MaNV,MaTK,HoTen,GioiTinh,DiaChi,SoDienThoai,CCCD,NgayVaoLam,TrangThai,Anh) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, nv.getMaNhanVien());
            pst.setObject(2, nv.getMaTaiKhoan());
            pst.setObject(3, nv.getHoTen());
            pst.setObject(4, nv.isGioiTinh());
            pst.setObject(5, nv.getDiaChi());
            pst.setObject(6, nv.getSoDienThoai());
            pst.setObject(7, nv.getCCCD());
            pst.setObject(8, nv.getNgayVaoLam());
            pst.setObject(9, nv.isTrangThai());
            pst.setObject(10, nv.getAnh());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
