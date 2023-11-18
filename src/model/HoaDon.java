/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin BVCN88 02
 */
public class HoaDon {

    private String maHoaDon;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private Date ngayTao;
    private double tongTien;
    private boolean trangThai;
    private String ghiChu;
    private Events voucher;
    private int soLuongHoaDon;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, Date ngayTao) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
    }

    public HoaDon(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public HoaDon(Date ngayTao, int soLuongHoaDon) {
        this.ngayTao = ngayTao;
        this.soLuongHoaDon = soLuongHoaDon;
    }

    public int getSoLuongHoaDon() {
        return soLuongHoaDon;
    }

    public void setSoLuongHoaDon(int soLuongHoaDon) {
        this.soLuongHoaDon = soLuongHoaDon;
    }

    public HoaDon(String maHoaDon, NhanVien nhanVien, Date ngayTao, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDon(String maHoaDon, NhanVien nhanVien, KhachHang khachHang, Date ngayTao, double tongTien, boolean trangThai, String ghiChu, Events voucher) {
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.voucher = voucher;
    }

    public HoaDon(String maHoaDon, Date ngayTao, double tongTien, int soLuongHoaDon) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.soLuongHoaDon = soLuongHoaDon;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", nhanVien=" + nhanVien + ", tongTien=" + tongTien + '}';
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Events getVoucher() {
        return voucher;
    }

    public void setVoucher(Events voucher) {
        this.voucher = voucher;
    }

    public String chiTietTrangThai() {
        if (trangThai) {
            return "Đã thanh toán";
        } else {
            return "Chờ thanh toán";
        }
    }
}
