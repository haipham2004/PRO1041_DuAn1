/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Voucher {

    private String maVoucher;
    private int soLuong;
    private boolean trangThai;
    private Events ev;

    public Voucher(String maVoucher, int soLuong, boolean trangThai, Events ev) {
        this.maVoucher = maVoucher;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.ev = ev;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
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

    public Events getEv() {
        return ev;
    }

    public void setEv(Events ev) {
        this.ev = ev;
    }

    public Voucher() {
    }

}
