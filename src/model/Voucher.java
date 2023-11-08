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
    private String maEventa;
    private int soLuong;
    private boolean trangThai;

    public Voucher() {
    }

    public Voucher(String maVoucher, String maEventa, int soLuong, boolean trangThai) {
        this.maVoucher = maVoucher;
        this.maEventa = maEventa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMaEventa() {
        return maEventa;
    }

    public void setMaEventa(String maEventa) {
        this.maEventa = maEventa;
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
    

   

}
