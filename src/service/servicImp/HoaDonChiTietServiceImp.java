/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import javax.swing.JTable;
import model.HoaDon;
import model.HoaDonChiTiet;
import repository.HoaDonChiTietReposotpry;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietServiceImp {

    HoaDonChiTietReposotpry repo = new HoaDonChiTietReposotpry();

    public List<HoaDonChiTiet> getJoHang(JTable table) {
        return repo.getJoHang(table);
    }
    public List<HoaDonChiTiet> getJoHang2(JTable table, HoaDon hd) {
        return repo.getJoHang2(table, hd);
    }
    public int insert(List<HoaDonChiTiet> list) {
        return repo.insert(list);
    }
}
