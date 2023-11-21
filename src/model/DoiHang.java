/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class DoiHang {
    private String maDoiHang;
    private HoaDon hoaDon;
    private NhanVien nhanVien;
    private Date ngayDoiTra;
    private boolean trangThai;
    private double tienTraKhach;

    public DoiHang() {
    }

    public DoiHang(String maDoiHang, HoaDon hoaDon, NhanVien nhanVien, Date ngayDoiTra, boolean trangThai, double tienTraKhach) {
        this.maDoiHang = maDoiHang;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
        this.ngayDoiTra = ngayDoiTra;
        this.trangThai = trangThai;
        this.tienTraKhach = tienTraKhach;
    }

    public String getMaDoiHang() {
        return maDoiHang;
    }

    public void setMaDoiHang(String maDoiHang) {
        this.maDoiHang = maDoiHang;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayDoiTra() {
        return ngayDoiTra;
    }

    public void setNgayDoiTra(Date ngayDoiTra) {
        this.ngayDoiTra = ngayDoiTra;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public double getTienTraKhach() {
        return tienTraKhach;
    }

    public void setTienTraKhach(double tienTraKhach) {
        this.tienTraKhach = tienTraKhach;
    }
    
    public String chiTietTrangThai(){
        if (trangThai) {
            return "Đã đổi trả";
        }else{
            return "Hủy";
        }
    }
}
