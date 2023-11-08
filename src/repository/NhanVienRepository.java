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
import model.NhanVien;
import model.TaiKhoan;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class NhanVienRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<NhanVien> listNhanVien = new ArrayList<>();

    public List<NhanVien> getAll() {
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }

    public int insert(NhanVien nv) {
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO NhanVien (MaNV,MaTK,HoTen,GioiTinh,DiaChi,SoDienThoai,CCCD,NgayVaoLam,TrangThai,Anh) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, nv.getMaNhanVien());
            pst.setObject(2, nv.getMaTaiKhoan());
            pst.setObject(3, nv.getHoTen());
            pst.setObject(4, nv.isGioiTinh());
            pst.setObject(5, nv.getDiaChi());
            pst.setObject(6, nv.getSoDienThoai());
            pst.setObject(7, nv.getCCCD());
            pst.setObject(8, nv.getNgayVaoLam());
            pst.setObject(9, nv.isTrangThai());
            pst.setObject(10, nv.getAnh());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(NhanVien nv, String maNV) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE NhanVien SET MaTK=?, HoTen=?, GioiTinh=?, DiaChi=?, SoDienThoai=?, CCCD=?, NgayVaoLam=?, TrangThai=?, Anh=? WHERE MaNV=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(10, maNV);
            pst.setObject(1, nv.getMaTaiKhoan());
            pst.setObject(2, nv.getHoTen());
            pst.setObject(3, nv.isGioiTinh());
            pst.setObject(4, nv.getDiaChi());
            pst.setObject(5, nv.getSoDienThoai());
            pst.setObject(6, nv.getCCCD());
            pst.setObject(7, nv.getNgayVaoLam());
            pst.setObject(8, nv.isTrangThai());
            pst.setObject(9, nv.getAnh());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<NhanVien> getList2(String mot, String hai) {
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where GioiTinh = ? and DiaChi = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, mot);
            pst.setObject(2, hai);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }

    public List<NhanVien> getList3(String mot, String hai) {
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where " + mot + " like ? ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, "%" + hai + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }

    public NhanVien getOne(String ma) {
        NhanVien nv = null;
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where MaNV = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14));
                nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
