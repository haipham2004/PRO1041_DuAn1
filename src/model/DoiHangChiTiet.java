/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class DoiHangChiTiet {
    private String maDHCT;
    private DoiHang doiHang;
    private ChiTietHoaDon chiTietHoaDon;
    private ChiTietSanPham chiTietSanPham;
    private int soLuong;
    private boolean trangThai;
    private String moTa;

    public DoiHangChiTiet() {
    }

    public DoiHangChiTiet(ChiTietSanPham chiTietSanPham, boolean trangThai, String moTa) {
        this.chiTietSanPham = chiTietSanPham;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }
    
    public DoiHangChiTiet(String maDHCT, DoiHang doiHang, ChiTietHoaDon chiTietHoaDon, ChiTietSanPham chiTietSanPham, int soLuong, boolean trangThai, String moTa) {
        this.maDHCT = maDHCT;
        this.doiHang = doiHang;
        this.chiTietHoaDon = chiTietHoaDon;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public String getMaDHCT() {
        return maDHCT;
    }

    public void setMaDHCT(String maDHCT) {
        this.maDHCT = maDHCT;
    }

    public DoiHang getDoiHang() {
        return doiHang;
    }

    public void setDoiHang(DoiHang doiHang) {
        this.doiHang = doiHang;
    }

    public ChiTietHoaDon getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

    public ChiTietSanPham getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
}
