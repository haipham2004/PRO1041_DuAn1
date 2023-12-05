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
import model.HoaDonChiTiet;
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
            sql = "SELECT DH.MaDoiHang, SP.MaSanPham, SP.TenSanPham, CTSPCu.MaCTSP, CTSPMoi.MaCTSP, DHCT.SoLuong,\n"
                    + "DHCT.TrangThai, DHCT.MoTa, HD.MaHoaDon, DHCT.MaDHCT, HDCT.MaHoaDonChiTiet FROM DoiHangChiTiet DHCT\n"
                    + "JOIN DoiHang DH ON DH.MaDoiHang = DHCT.MaDoiHang  JOIN HoaDon HD ON DH.MaHoaDon = HD.MaHoaDon \n"
                    + "JOIN HoaDonChiTiet HDCT ON HDCT.MaHoaDonChiTiet = DHCT.MaHoaDonChiTiet JOIN ChiTietSanPham CTSPCu\n"
                    + "ON HDCT.MaCTSP = CTSPCu.MaCTSP JOIN ChiTietSanPham CTSPMoi ON DHCT.MaCTSP = CTSPMoi.MaCTSP JOIN SanPham SP ON SP.MaSanPham = CTSPCu.MaSanPham"
                    + "					WHERE DH.MaDoiHang = ? and DHCT.TrangThai = 1";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maDoiHang);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(9));
                DoiHang dh = new DoiHang(rs.getString(1), hd);
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                ChiTietSanPham ctspCu = new ChiTietSanPham(rs.getString(4), sp);
                ChiTietSanPham ctspMoi = new ChiTietSanPham(rs.getString(5));
                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getString(11), ctspCu);
                DoiHangChiTiet dhct = new DoiHangChiTiet(rs.getString(10), dh, hdct,
                        ctspMoi,rs.getInt(6), rs.getBoolean(7), rs.getString(8));
                listDHCT.add(dhct);
            }
            return listDHCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int them(DoiHangChiTiet dhct){
        try {
            sql = "Insert into DoiHangChiTiet(MaDHCT,MaDoiHang,MaHoaDonChiTiet,TrangThai) Values(?,?,?,0)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, dhct.getMaDHCT());
            ps.setObject(2, dhct.getDoiHang().getMaDoiHang());
            ps.setObject(3, dhct.getHoaDonChiTiet().getMaHDCT());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<DoiHangChiTiet> getDHCTDangDoi(String maDoiHang) {
        List<DoiHangChiTiet> listDHCT = new ArrayList<>();
        listDHCT.clear();
        try {
            sql = "SELECT DH.MaDoiHang, SP.MaSanPham, SP.TenSanPham, CTSPCu.MaCTSP, CTSPMoi.MaCTSP, DHCT.SoLuong,\n" +
"                    DHCT.TrangThai, DHCT.MoTa, HD.MaHoaDon, DHCT.MaDHCT, HDCT.MaHoaDonChiTiet, CTSPMoi.Gia FROM DoiHangChiTiet DHCT\n" +
"                   full JOIN DoiHang DH ON DH.MaDoiHang = DHCT.MaDoiHang full JOIN HoaDon HD ON DH.MaHoaDon = HD.MaHoaDon\n" +
"                   full JOIN HoaDonChiTiet HDCT ON HDCT.MaHoaDonChiTiet = DHCT.MaHoaDonChiTiet full JOIN ChiTietSanPham CTSPCu\n" +
"                    ON HDCT.MaCTSP = CTSPCu.MaCTSP full JOIN ChiTietSanPham CTSPMoi ON DHCT.MaCTSP = CTSPMoi.MaCTSP JOIN SanPham SP ON SP.MaSanPham = CTSPCu.MaSanPham\n" +
"                    					WHERE DHCT.MaDoiHang = ? and DHCT.TrangThai = 0";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maDoiHang);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(9));
                DoiHang dh = new DoiHang(rs.getString(1), hd);
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                ChiTietSanPham ctspCu = new ChiTietSanPham(rs.getString(4), sp);
                ChiTietSanPham ctspMoi = new ChiTietSanPham(rs.getString(5),rs.getDouble(12));
                HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getString(11), ctspCu);
                DoiHangChiTiet dhct = new DoiHangChiTiet(rs.getString(10), dh, hdct,
                        ctspMoi,rs.getInt(6), rs.getBoolean(7), rs.getString(8));
                listDHCT.add(dhct);
            }
            return listDHCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int countDoiHangChiTiet() {
        int tongDHCT = 0;
        try {
            sql = "Select COUNT(*) From DoiHang";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongDHCT = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tongDHCT;
    }
    
    public int xoa(String maDH){
        try {
            sql = "Delete From DoiHangChiTiet where MaDoiHang = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maDH);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int sua(DoiHangChiTiet dhct, String maDHCT){
        try {
            sql = "Update DoiHangChiTiet set MaCTSP = ?, SoLuong = ?, MoTa = ? Where MaDHCT = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, dhct.getChiTietSanPham().getMaChiTietSanPham());
            ps.setObject(2, dhct.getSoLuong());
            ps.setObject(3, dhct.getMoTa());
            ps.setObject(4, maDHCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public String getMaDHCT(String maHDCT){
        String maDHCT = null;
        try {
            sql = "Select MaDHCT From DoiHangChiTiet where MaHoaDonChiTiet = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHDCT);
            rs = ps.executeQuery();
            while(rs.next()){
                maDHCT = rs.getString(1);
            }
            return maDHCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int capNhatTrangThai(String maDH){
        try {
            sql = "Update DoiHangChiTiet set TrangThai = 1 Where MaDoiHang = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maDH);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
