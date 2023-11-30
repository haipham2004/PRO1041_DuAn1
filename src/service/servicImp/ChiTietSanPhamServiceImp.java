/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.sql.SQLException;
import java.util.List;
import model.ChiTietSanPham;
import model.HoaDonChiTiet;
import repository.ChiTietSanPhamRepository;
import service.AdamStore;
import view.AdamStoreView;

/**
 *
 * @author Admin BVCN88 02
 */
public class ChiTietSanPhamServiceImp implements AdamStore<ChiTietSanPham, String> {

    ChiTietSanPhamRepository repo = new ChiTietSanPhamRepository();

    @Override
    public List<ChiTietSanPham> getAll() {
        return repo.getAll();
    }

    @Override
    public int them(ChiTietSanPham k) {
        return repo.them(k);
    }

    @Override
    public int sua(ChiTietSanPham k, String e) {
        return repo.suas(k, e);
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietSanPham getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<ChiTietSanPham> getList(String e) {
        return repo.getList(e);
    }

    public List<ChiTietSanPham> getListGia(double giaMin, double giaMax) {
        return repo.getListGia(giaMin, giaMax);
    }

    public List<ChiTietSanPham> listPageCTSP(int index,String name) {
        return repo.listPageCTSP(index,name);
    }
    
       public List<ChiTietSanPham> listPageCTSP2(int index) {
        return repo.listPageCTSP2(index);
    }

    public int tongBanGhi(String name) {
        return repo.tongBanGhi(name);
    }
    
    public int tongBanGhi2(){
        return repo.tongBanGhi2();
    }

    public int updateTrangThaiSoLuong() {
        return repo.updateTrangThaiSoLuong();
    }

    public boolean checkMaQR(String qrCode){
        return repo.checkMaQR(qrCode);
    }

    public int taoQR(String qrCode) {
        return repo.taoQR(qrCode);
    }

    public List<ChiTietSanPham> getDanhSachSPCT(String maHoadon) {
        return repo.getDanhSachSPCT(maHoadon);
    }

    public boolean checkTrungCTSP(String name1,String name2 ,String name3,String name4) {
        return repo.checkTrungCTSP(name1, name2, name3,name4);
    }

    public List<ChiTietSanPham> getListLoc(String name1, String name2, String name3, String name4) {
        return repo.getListLoc(name1, name2, name3, name4);
    }
 
}
