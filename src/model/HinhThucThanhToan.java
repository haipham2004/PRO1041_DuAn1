/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin BVCN88 02
 */
public class HinhThucThanhToan {
    private String maHTTT;
    private String tenHTTT;
    private boolean trangThai;

    public HinhThucThanhToan() {
    }

    public HinhThucThanhToan(String maHTTT, String tenHTTT, boolean trangThai) {
        this.maHTTT = maHTTT;
        this.tenHTTT = tenHTTT;
        this.trangThai = trangThai;
    }

    public String getMaHTTT() {
        return maHTTT;
    }

    public void setMaHTTT(String maHTTT) {
        this.maHTTT = maHTTT;
    }

    public String getTenHTTT() {
        return tenHTTT;
    }

    public void setTenHTTT(String tenHTTT) {
        this.tenHTTT = tenHTTT;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
