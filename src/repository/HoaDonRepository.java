/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.NhanVien;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDon> getHoaDonCho() {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "Select HD.MaHoaDon,NV.MaNV,NV.HoTen,HD.NgayTao,HD.TrangThai\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV where HD.TrangThai = 0";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2), rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, rs.getDate(4), rs.getBoolean(5));
                listHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listHoaDon;
    }

    public int countHoaDon() {
        int tongHoaDon = 0;
        try {
            sql = "Select COUNT(*) From HoaDon";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongHoaDon = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tongHoaDon;
    }

    public int themHoaDon(HoaDon hd) {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            sql = "insert into HoaDon(MaHoaDon, MaNV,MaKH,NgayTao,TongTien,TongTienKM,TongTienSauKM,TrangThai,GhiChu,MaEV) values (?,?,?,?,?,?,?,?,?,?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getNhanVien().getMaNhanVien());
            ps.setObject(3, hd.getKhachHang().getMaKhachHang());
            ps.setTimestamp(4, currentTime);
            Double mucGiam;
            if (hd.getVoucher() == null) {
                mucGiam = 0.0;
                ps.setObject(10, null);
                ps.setObject(5, hd.getTongTien());
                ps.setObject(6, 0);
                ps.setObject(7, hd.getTongTien());
            } else {
                mucGiam = Double.parseDouble(hd.getVoucher().getMucGiamGia()) / 100;
                ps.setObject(10, hd.getVoucher().getMaEventa());
                ps.setObject(5, hd.getTongTien() / (1-mucGiam));
                ps.setObject(6, (hd.getTongTien() / (1-mucGiam)) - hd.getTongTien());
                ps.setObject(7, hd.getTongTien());
            }
            ps.setObject(8, hd.isTrangThai());
            ps.setObject(9, hd.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
