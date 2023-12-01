/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.KhachHang;
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

    public String giuSo(String x) {
        String so = x.replaceAll("[^0-9]", "");
        return so;
    }

    public List<HoaDon> getHoaDonCho() {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "Select HD.MaHoaDon,NV.MaNV,NV.HoTen,HD.NgayTao,HD.TrangThai\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV where HD.TrangThai like N'Chờ thanh toán'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2), rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, rs.getDate(4), rs.getString(5));
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

    public List<HoaDon> getLSHoaDon() {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
                ps.setObject(10, hd.getVoucher().getMaEventa());
                if (!hd.getVoucher().isHinhThuc()) {
                    mucGiam = Double.parseDouble(giuSo(hd.getVoucher().getMucGiamGia())) / 100;
                    ps.setObject(5, hd.getTongTien() / (1 - mucGiam));
                    ps.setObject(6, (hd.getTongTien() / (1 - mucGiam)) - hd.getTongTien());
                    ps.setObject(7, hd.getTongTien());
                } else {
                    mucGiam = Double.parseDouble(giuSo(hd.getVoucher().getMucGiamGia()));
                    ps.setObject(5, hd.getTongTien() + mucGiam);
                    ps.setObject(6, mucGiam);
                    ps.setObject(7, hd.getTongTien());
                }
            }
            ps.setObject(8, hd.getTrangThai());
            ps.setObject(9, hd.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int chuyenSangDoiHang(String maHD) {
        try {
            sql = "Update HoaDon set TrangThai = N'Đang đổi hàng' where MaHoaDon = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<HoaDon> getList(String MaHDorMaKH) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where HD.MaHoaDon like ? or KH.MaKH like ? order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + MaHDorMaKH + '%');
            ps.setObject(2, '%' + MaHDorMaKH + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themHoaDonCho(HoaDon hd) {
        try {
            sql = "Insert into HoaDon(MaHoaDon,MaNV,NgayTao,TrangThai) Values(?,?,GETDATE(),N'Chờ thanh toán')";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getNhanVien().getMaNhanVien());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<HoaDon> getLSHoaDonDuocDoiHang() {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where DATEDIFF(DAY,HD.NgayTao,GETDATE()) < 7 order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDon> getListDuocDoiHang(String MaHDorMaKH) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where (HD.MaHoaDon like N'%1%' or KH.MaKH like N'%1%') and DATEDIFF(DAY,HD.NgayTao,GETDATE()) < 7\n"
                    + "and HD.TrangThai like N'Đã thanh toán' order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + MaHDorMaKH + '%');
            ps.setObject(2, '%' + MaHDorMaKH + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
