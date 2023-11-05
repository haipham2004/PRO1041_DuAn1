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
import util.DBConnect1111111;

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
        try {
            conn = DBConnect1111111.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n" +
"					KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n" +
"					FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n" +
"					INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n" +
"					INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2));
                MauSac ms = new MauSac(rs.getString(3), rs.getString(4));
                ChatLieu cl = new ChatLieu(rs.getString(5), rs.getString(6));
                KichThuoc kt = new KichThuoc(rs.getString(7), rs.getString(8));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(9), rs.getDouble(10), rs.getBoolean(11));
                listChiTietSanPham.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham;
    }
}
