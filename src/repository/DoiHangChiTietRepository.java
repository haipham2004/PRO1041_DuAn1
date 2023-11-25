/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietHoaDon;
import model.ChiTietSanPham;
import model.DoiHang;
import model.DoiHangChiTiet;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class DoiHangChiTietRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<DoiHangChiTiet> listDHCT = new ArrayList<>();

    public List<DoiHangChiTiet> getDHCT(String maDoiHang) {
        List<DoiHangChiTiet> listDHCT = new ArrayList<>();
        listDHCT.clear();
        try {
            sql = "SELECT DH.MaDoiHang, SP.MaSanPham, SP.TenSanPham, CTSPCu.MaCTSP, CTSPMoi.MaCTSP, HDCT.SoLuong,\n" +
"                    DHCT.TrangThai, DHCT.MoTa, HD.MaHoaDon, DHCT.MaDHCT, HDCT.MaHoaDonChiTiet\n" +
"                    FROM DoiHangChiTiet DHCT JOIN DoiHang DH ON DH.MaDoiHang = DHCT.MaDoiHang \n" +
"                    JOIN HoaDon HD ON DH.MaHoaDon = HD.MaHoaDon\n" +
"                    JOIN HoaDonChiTiet HDCT ON HDCT.MaHoaDonChiTiet = DHCT.MaHoaDonChiTiet\n" +
"					JOIN ChiTietSanPham CTSPCu ON HDCT.MaCTSP = CTSPCu.MaCTSP\n" +
"					JOIN ChiTietSanPham CTSPMoi ON DHCT.MaCTSP = CTSPMoi.MaCTSP\n" +
"                    JOIN SanPham SP ON SP.MaSanPham = CTSPCu.MaSanPham\n" +
"					WHERE DH.MaDoiHang = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maDoiHang);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(9));
                DoiHang dh = new DoiHang(rs.getString(1),hd);
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                ChiTietSanPham ctspCu = new ChiTietSanPham(rs.getString(4),sp);
                ChiTietSanPham ctspMoi = new ChiTietSanPham(rs.getString(5));
                ChiTietHoaDon hdct = new ChiTietHoaDon(ctspCu, rs.getInt(6));
                DoiHangChiTiet dhct = new DoiHangChiTiet(rs.getString(10), dh, hdct,
                        ctspMoi, rs.getBoolean(7), rs.getString(8));
                listDHCT.add(dhct);
            }
            return listDHCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
