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
import model.HoaDon;
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
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham order by CTSP.SoLuong DESC";
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
                    + "	INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where CTSP.MaCTSP=? order by CTSP.TrangThai DESC";
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
            sql = "INSERT INTO ChiTietSanPham(MaSanPham,MaMauSac,MaChatLieu,MaKichThuoc,SoLuong,Gia,TrangThai,qrCode) VALUES(?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ctsp.getSanPham().getMaSanPham());
            pst.setObject(2, ctsp.getMauSac().getMaMauSac());
            pst.setObject(3, ctsp.getChatLieu().getMaChatLieu());
            pst.setObject(4, ctsp.getKichThuoc().getMaKichThuoc());
            pst.setObject(5, ctsp.getSoLuong());
            pst.setObject(6, ctsp.getGia());
            pst.setObject(7, ctsp.isTrangThai());
            pst.setObject(8, ctsp.getQrCode());
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

    public boolean checkExitCTSP(String maCTSP) throws SQLException {
        conn = DBConnect.getConnection();
        sql = "SELECT*FROM ChiTietSanPham where MaCTSP=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, maCTSP);
        rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public List<ChiTietSanPham> getList(String name) {
        List<ChiTietSanPham> listChiTietSanPham3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where SP.TenSanPham like ? or CTSP.MaCTSP=? order by CTSP.TrangThai DESC ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name + '%');
            pst.setObject(2, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham3.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham3;
    }

    public List<ChiTietSanPham> getListLoc(String name1, String name2, String name3, String name4) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "  SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "                   KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "                   FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "                    INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "                    INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "                   INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ? and CL.TenChatLieu like ? and MS.TenMauSac like ? and KT.TenKichThuoc like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, '%' + name2 + '%');
            pst.setObject(3, '%' + name3 + '%');
            pst.setObject(4, '%' + name4 + '%');
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
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
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where Gia between ? and ? order by CTSP.TrangThai DESC";
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

    public List<ChiTietSanPham> listPageCTSP(int index) {
        List<ChiTietSanPham> listChiTietSanPham3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham \n"
                    + "order by CTSP.TrangThai DESC\n"
                    + "offset ? rows fetch next 4 rows only";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, (index - 1) * 4);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham3.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham3;
    }

    public List<ChiTietSanPham> getDanhSachSPCT(String maHoadon) {
        List<ChiTietSanPham> listCTSP = new ArrayList<>();
        try {
            sql = "SELECT CTSP.MaCTSP, CTSP.MASANPHAM, HD.MAHOADON, CTSP.SOLUONG, CTSP.GIA, CTSP.TRANGTHAI \n"
                    + " FROM ChiTietSanPham CTSP JOIN HoaDonChiTiet HDCT \n"
                    + "                  ON CTSP.MaCTSP = HDCT.MaCTSP JOIN HoaDon HD ON HD.MaHoaDon = HDCT.MaHoaDon WHERE HD.MaHoaDon = ?";
            conn = DBConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setObject(1, maHoadon);
            rs = pst.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(3));
                SanPham sp = new SanPham(rs.getString(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp, Integer.parseInt(rs.getString(4)), rs.getDouble(5), rs.getBoolean(6));
                listCTSP.add(ctsp);
            }
            return listCTSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int tongBanGhi() {
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM ChiTietSanPham";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                tong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tong;
    }

    public int updateTrangThaiSoLuong() {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham\n"
                    + "set TrangThai=0\n"
                    + "where SoLuong=0\n"
                    + "UPDATE ChiTietSanPham\n"
                    + "set TrangThai=1\n"
                    + "where SoLuong>0";
            pst = conn.prepareStatement(sql);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkMaQR(String qrCode) throws SQLException {
        conn = DBConnect.getConnection();
        sql = "SELECT * FROM ChiTietSanPham WHERE qrCode=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, qrCode);
        rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public int taoQR(String qrCode) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set qrCode=? where MaCTSP=? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, qrCode);
            pst.setString(2, qrCode);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkTrungCTSP(String name) throws SQLException {
        conn = DBConnect.getConnection();
        sql = "SELECT DISTINCT MaChatLieu,MaMauSac,MaKichThuoc\n"
                + "FROM ChiTietSanPham CTSP\n"
                + "INNER JOIN SanPham SP On CTSP.MaSanPham=SP.MaSanPham\n"
                + "WHERE SP.TenSanPham like ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, '%' + name + '%');
        rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}
