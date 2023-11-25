/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon {

    private String MaHoaDonChiTiet;
    private ChiTietSanPham chiTietSanPham;
    private HoaDon hoaDon;
    private int SoLuong;
    private double DonGia;
    private String ghiChu;
    private boolean trangThai;

    public ChiTietHoaDon(String MaHoaDonChiTiet, ChiTietSanPham chiTietSanPham) {
        this.MaHoaDonChiTiet = MaHoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
    }

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public ChiTietHoaDon(String MaHoaDonChiTiet, ChiTietSanPham chiTietSanPham, int SoLuong) {
        this.MaHoaDonChiTiet = MaHoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.SoLuong = SoLuong;
    }

    public ChiTietHoaDon(ChiTietSanPham chiTietSanPham, int SoLuong) {
        this.chiTietSanPham = chiTietSanPham;
        this.SoLuong = SoLuong;
    }


    public ChiTietHoaDon(String MaHoaDonChiTiet, ChiTietSanPham chiTietSanPham, HoaDon hoaDon, int SoLuong, double DonGia, String ghiChu, boolean trangThai) {
        this.MaHoaDonChiTiet = MaHoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.hoaDon = hoaDon;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public ChiTietHoaDon(HoaDon hoaDon, ChiTietSanPham chiTietSanPham, int SoLuong, double DonGia) {
        this.chiTietSanPham = chiTietSanPham;
        this.hoaDon = hoaDon;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public ChiTietHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public String getMaHoaDonChiTiet() {
        return MaHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String MaHoaDonChiTiet) {
        this.MaHoaDonChiTiet = MaHoaDonChiTiet;
    }

    public ChiTietSanPham getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}
