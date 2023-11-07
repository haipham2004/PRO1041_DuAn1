/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class KhachHangRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<KhachHang> listKhachHang = new ArrayList<>();

    public List<KhachHang> getAll() {
        listKhachHang.clear();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi,TrangThai From KhachHang";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getBoolean(8));
                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang;
    }

    public int Them(KhachHang kh) {
        try {
            con = DBConnect.getConnection();
            sql = "INSERT INTO KhachHang(MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi,TrangThai) \n"
                    + "Values (?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMaKhachHang());
            ps.setObject(2, kh.getHoTen());
            ps.setObject(3, kh.getNgaySinh());
            ps.setObject(4, kh.getSoDienThoai());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.isGioiTinh());
            ps.setObject(7, kh.getDiaChi());
            ps.setObject(8, kh.isGioiTinh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int CapNhat(KhachHang kh, String maKH) {
        try {
            con = DBConnect.getConnection();
            sql = "Update KhachHang set HoTen = ?, NgaySinh = ?,SoDienThoai = ?,Email = ?,\n"
                    + "GioiTInh = ?,DiaChi = ? ,TrangThai = ? where MaKH = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(8, maKH);
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getNgaySinh());
            ps.setObject(3, kh.getSoDienThoai());
            ps.setObject(4, kh.getEmail());
            ps.setObject(5, kh.isGioiTinh());
            ps.setObject(6, kh.getDiaChi());
            ps.setObject(7, kh.isGioiTinh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public KhachHang getOne(String maKH) {
        KhachHang kh = null;
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi,TrangThai From KhachHang where MaKH = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getBoolean(8));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<KhachHang> getList(String ten) {
        listKhachHang.clear();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi,TrangThai From KhachHang where HoTen like ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getBoolean(8));
                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang;
    }
}
