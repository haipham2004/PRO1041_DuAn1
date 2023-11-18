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
import java.util.Date;
import model.ChiTietHoaDon;
import model.ChiTietSanPham;
import model.SanPham;

/**
 *
 * @author Admin
 */
public class ThongKeSLRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;

//    public List<HoaDon> getlistHoaDon() {
//        List<HoaDon> listTKHoaDon = new ArrayList<>();
//        try {
//            con = DBConnect.getConnection();
//            sql = "select NgayTao, count(*) as TongHd from HoaDon\n"
//                    + "group by NgayTao";
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getDate(1), rs.getInt(2));
//                listTKHoaDon.add(hoaDon);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return listTKHoaDon;
//    }
//
//    public List<HoaDon> listBieuDoTKHoaDon(Date ngayBd, Date ngayKt) {
//        List<HoaDon> listTKHoaDon2 = new ArrayList<>();
//        try {
//            con = DBConnect.getConnection();
//            sql = "select NgayTao,count(*) as TongHd from HoaDon\n"
//                    + "where NgayTao  between  ? and ? \n"
//                    + "or NgayTao  between  ? and ? \n"
//                    + "group by NgayTao";
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, ngayBd);
//            ps.setObject(2, ngayKt);
//            ps.setObject(3, ngayBd);
//            ps.setObject(4, ngayKt);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getDate(1), rs.getInt(2));
//                listTKHoaDon2.add(hoaDon);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return listTKHoaDon2;
//    }
    public List<ChiTietHoaDon> getListCTHD() {
        List<ChiTietHoaDon> listCTHD = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select hdct.MaHoaDon,hd.NgayTao, ctsp.MaCTSP, hdct.SoLuong, "
                    + "hdct.DonGia  from HoaDonChiTiet hdct\n"
                    + "inner join HoaDon hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "inner join ChiTietSanPham ctsp on hdct.MaCTSP = ctsp.MaCTSP\n"
                    + "inner join SanPham sp on ctsp.MaSanPham = sp.MaSanPham";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(1), rs.getDate(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(3));
                ChiTietHoaDon cthd = new ChiTietHoaDon(hd, ctsp,
                        rs.getInt(4), rs.getDouble(5));
                listCTHD.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listCTHD;
    }

    public List<ChiTietHoaDon> getListTk(Date ngayBd, Date ngayKt) {
        List<ChiTietHoaDon> listCTHD2 = new ArrayList<>();
        listCTHD2.clear();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT hdct.MaHoaDon, hd.NgayTao, ctsp.MaCTSP, hdct.SoLuong, hdct.DonGia\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "INNER JOIN ChiTietSanPham ctsp ON hdct.MaCTSP = ctsp.MaCTSP\n"
                    + "INNER JOIN SanPham sp ON ctsp.MaSanPham = sp.MaSanPham\n"
                    + "WHERE hd.NgayTao BETWEEN CONCAT(?, ' ', '00:00:00')AND CONCAT(?, ' ', '23:59:59') ";
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayBd.getTime()));
            ps.setDate(2, new java.sql.Date(ngayKt.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(1), rs.getDate(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(3));
                ChiTietHoaDon cthd = new ChiTietHoaDon(hd, ctsp,
                        rs.getInt(4), rs.getDouble(5));
                listCTHD2.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listCTHD2;
    }

    public List<ChiTietHoaDon> getListBieuDoHD() {
        List<ChiTietHoaDon> listBieuDo1 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT NgayTao, count(*) as TongHd\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "GROUP BY NgayTao";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getDate(1), rs.getInt(2));
                ChiTietHoaDon cthd = new ChiTietHoaDon(hd);
                listBieuDo1.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listBieuDo1;
    }

    public List<ChiTietHoaDon> getListTKBieuDoHD(Date ngayBd, Date ngayKt) {
        List<ChiTietHoaDon> listBieuDo2 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT NgayTao, count(*) as TongHd\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "WHERE hd.NgayTao BETWEEN CONCAT(?, ' ', '00:00:00')AND CONCAT(?, ' ', '23:59:59') "
                    + "GROUP BY NgayTao";
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayBd.getTime()));
            ps.setDate(2, new java.sql.Date(ngayKt.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getDate(1), rs.getInt(2));
                ChiTietHoaDon cthd = new ChiTietHoaDon(hd);
                listBieuDo2.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listBieuDo2;
    }

    public List<ChiTietHoaDon> getListBieuDoTopSP() {
        List<ChiTietHoaDon> listBieuDo1 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select sp.TenSanPham,count(sp.MaSanPham) from HoaDonChiTiet cthd\n"
                    + "inner join HoaDon HD on cthd.MaHoaDon = hd.MaHoaDon\n"
                    + "inner join ChiTietSanPham ctsp on cthd.MaCTSP = ctsp.MaCTSP\n"
                    + "inner join SanPham sp on ctsp.MaSanPham = sp.MaSanPham\n"
                    + "group by sp.TenSanPham,sp.MaSanPham";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getInt(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(sp);
                ChiTietHoaDon cthd = new ChiTietHoaDon(ctsp);
                listBieuDo1.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listBieuDo1;
    }

}
