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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KichThuoc;
import model.MauSac;
import model.SanPham;
import util.DBConnect;
//import view.BanHangView;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietReposotpry {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;


    public List<HoaDonChiTiet> getJoHang(JTable table) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        int soCot = table.getRowCount();
        if (soCot == 0) {
            JOptionPane.showMessageDialog(null, "Giỏ hàng rỗng");
            return null;
        }
        try {
            con = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + " KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + " FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + " INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + " INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + " INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where maCTSP = ?";
            ps = con.prepareStatement(sql);
            for (int i = 0; i < soCot; i++) {
                String maCTSP = table.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(table.getValueAt(i, 1).toString());
                Double gia = Double.parseDouble(table.getValueAt(i, 2).toString());
                ps.setObject(1, maCTSP);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                    MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                    ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                    KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                    ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                            ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                    HoaDonChiTiet hdct = new HoaDonChiTiet(ctsp, soLuong, gia);
                    list.add(hdct);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<HoaDonChiTiet> getJoHang2(JTable table, HoaDon hd) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        int soCot = table.getRowCount();
        if (soCot == 0) {
            JOptionPane.showMessageDialog(null, "Giỏ hàng rỗng");
            return null;
        }
        try {
            con = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + " KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + " FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + " INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + " INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + " INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where maCTSP = ?";
            ps = con.prepareStatement(sql);
            for (int i = 0; i < soCot; i++) {
                String maCTSP = table.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(table.getValueAt(i, 1).toString());
                Double gia = Double.parseDouble(table.getValueAt(i, 2).toString());
                ps.setObject(1, maCTSP);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                    MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                    ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                    KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                    ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                            ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
//                    HoaDon hd = BHView.getFormBH();
                    HoaDonChiTiet hdct = new HoaDonChiTiet(ctsp.getMaChiTietSanPham() + hd.getMaHoaDon(), ctsp, hd, soLuong, gia, null, true);
                    list.add(hdct);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int insert(List<HoaDonChiTiet> list) {
        try {
            sql = "insert into HoaDonChiTiet(MaHoaDonChiTiet,MaCTSP,MaHoaDon,SoLuong,DonGia,GhiChu,TrangThai) values (?,?,?,?,?,?,?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            for (HoaDonChiTiet hdct : list) {
                ps.setObject(1, hdct.getMaHDCT());
                ps.setObject(2, hdct.getCtsp().getMaChiTietSanPham());
                ps.setObject(3, hdct.getHD().getMaHoaDon());
                ps.setObject(4, hdct.getSoLuong());
                ps.setObject(5, hdct.getDonGia());
                ps.setObject(6, hdct.getGhiChu());
                ps.setObject(7, hdct.getTrangThai());
                ps.executeUpdate();
            }
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int capNhatSoLuongThanhToan(List<HoaDonChiTiet> list) {
        int so = 0;
        try {
            con = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set SoLuong=SoLuong+?\n"
                    + "where MaCTSP=?";
            ps = con.prepareStatement(sql);
            for (HoaDonChiTiet ctsp : list) {
                ps.setObject(2, ctsp.getCtsp().getMaChiTietSanPham());
                ps.setObject(1, ctsp.getSoLuong());
                so += ps.executeUpdate();
            }
            return so;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
