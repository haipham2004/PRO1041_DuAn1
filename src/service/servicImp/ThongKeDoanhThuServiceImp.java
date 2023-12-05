/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.Date;
import java.util.List;
import model.HoaDonChiTiet;
import repository.ThongKeDTRepository;
import service.AdamStore;

/**
 *
 * @author Admin
 */
public class ThongKeDoanhThuServiceImp implements AdamStore<HoaDonChiTiet, String>{
    ThongKeDTRepository repo = new ThongKeDTRepository();

    @Override
    public List<HoaDonChiTiet> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(HoaDonChiTiet k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int sua(HoaDonChiTiet k, String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet getOne(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> getList(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<HoaDonChiTiet> getListThongKeDT() {
        return repo.getListThongKeDT();
    }
    
    public List<HoaDonChiTiet> getListTKDT(Date ngayBd, Date ngayKt) {
        return repo.getListTKDT(ngayBd, ngayKt);
    }
}
