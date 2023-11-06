/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.KichThuoc;
import model.MauSac;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author Admin BVCN88 02
 */
public class ChiTietSanPhamRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<ChiTietSanPham> listChiTietSanPham = new ArrayList<>();

    public List<ChiTietSanPham> getAll() {
        listChiTietSanPham.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + " KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + " FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + " INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + " INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "	INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham;
    }

    public ChiTietSanPham getOne(String ma) {
        ChiTietSanPham ctsp = null;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + " KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + " FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + " INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + " INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "	INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where CTSP.MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ctsp;
    }

    public int them(ChiTietSanPham ctsp) {
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO ChiTietSanPham(MaCTSP,MaSanPham,MaMauSac,MaChatLieu,MaKichThuoc,SoLuong,Gia,TrangThai) VALUES(?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ctsp.getMaChiTietSanPham());
            pst.setObject(2, ctsp.getSanPham().getMaSanPham());
            pst.setObject(3, ctsp.getMauSac().getMaMauSac());
            pst.setObject(4, ctsp.getChatLieu().getMaChatLieu());
            pst.setObject(5, ctsp.getKichThuoc().getMaKichThuoc());
            pst.setObject(6, ctsp.getSoLuong());
            pst.setObject(7, ctsp.getGia());
            pst.setObject(8, ctsp.isTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suas(ChiTietSanPham ctsp, String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set MaSanPham=?,MaMauSac=?,MaChatLieu=?,MaKichThuoc=?,SoLuong=?,Gia=?,TrangThai=?\n"
                    + "where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ctsp.getSanPham().getMaSanPham());
            pst.setObject(2, ctsp.getMauSac().getMaMauSac());
            pst.setObject(3, ctsp.getChatLieu().getMaChatLieu());
            pst.setObject(4, ctsp.getKichThuoc().getMaKichThuoc());
            pst.setObject(5, ctsp.getSoLuong());
            pst.setObject(6, ctsp.getGia());
            pst.setObject(7, ctsp.isTrangThai());
            pst.setObject(8, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ChiTietSanPham> getList(String name) {
        List<ChiTietSanPham> listChiTietSanPham2 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where SP.TenSanPham like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, name);

            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham2.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham2;
    }

    public List<ChiTietSanPham> getListGia(double giaMin, double giaMax) {
        List<ChiTietSanPham> listChiTietSanPham2 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where Gia between ? and ? ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, giaMin);
            pst.setObject(2, giaMax);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham2.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham2;
    }
}
