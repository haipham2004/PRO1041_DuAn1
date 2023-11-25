/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DoiHang;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class DoiHangRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<DoiHang> listDoiHang = new ArrayList<>();

    public List<DoiHang> getAll() {
        listDoiHang.clear();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT DH.MaDoiHang,DH.NgayDoiTra,DH.TrangThai,\n"
                    + "HD.MaHoaDon,NV.MaNV,NV.HoTen,KH.MaKH,KH.HoTen\n"
                    + "From DoiHang DH join HoaDon HD ON DH.MaHoaDon = HD.MaHoaDon\n"
                    + "join KhachHang KH ON KH.MaKH = HD.MaKH\n"
                    + "join NhanVien NV ON NV.MaNV = DH.MaNV";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(7), rs.getString(8));
                NhanVien nv = new NhanVien(rs.getString(5), rs.getString(6));
                HoaDon hd = new HoaDon(rs.getString(4), kh);
                DoiHang dh = new DoiHang(rs.getString(1), hd, nv,
                        rs.getDate(2), rs.getBoolean(3));
                listDoiHang.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listDoiHang;
    }
}
