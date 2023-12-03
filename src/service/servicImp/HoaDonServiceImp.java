/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.HoaDon;
import repository.HoaDonRepository;
import service.AdamStore;

/**
 *
 * @author Admin
 */
public class HoaDonServiceImp implements AdamStore<HoaDon, String> {

    HoaDonRepository repo = new HoaDonRepository();

    @Override
    public List<HoaDon> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(HoaDon k) {
        return repo.themHoaDon(k);
    }

    @Override
    public int sua(HoaDon k, String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon getOne(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> getList(String e) {
        return repo.getList(e);
    }

    public List<HoaDon> getHoaDonCho() {
        return repo.getHoaDonCho();
    }

    public int countHoaDon() {
        return repo.countHoaDon();
    }
    
    public List<HoaDon> getLSHoaDon() {
        return repo.getLSHoaDon();
    }
    
    public int chuyenSangDoiHang(String maHD){
        return repo.chuyenSangDoiHang(maHD);
    }
    
    public int themHoaDonCho(HoaDon hd){
        return repo.themHoaDonCho(hd);
    }
    public HoaDon get1HoaDonCho(String maHD) {
        return repo.get1HoaDonCho(maHD);
    }
}
