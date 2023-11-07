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
public class KhuyenMai {
   private String maEventa;
   private String tenEvent;
   private String hinhThuc;
   private String mucGiamGia;
   private Date thoiGianBatDau;
   private Date thoiGianKetThuc;
   private String moTa;
   private String trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(String maEventa, String tenEvent, String hinhThuc, String mucGiamGia, Date thoiGianBatDau, Date thoiGianKetThuc, String moTa, String trangThai) {
        this.maEventa = maEventa;
        this.tenEvent = tenEvent;
        this.hinhThuc = hinhThuc;
        this.mucGiamGia = mucGiamGia;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getMaEventa() {
        return maEventa;
    }

    public void setMaEventa(String maEventa) {
        this.maEventa = maEventa;
    }

    public String getTenEvent() {
        return tenEvent;
    }

    public void setTenEvent(String tenEvent) {
        this.tenEvent = tenEvent;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(String mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
   
}
