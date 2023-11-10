/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.ChiTietSanPham;
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

    public List<ChiTietSanPham> listPageCTSP(int index) {
        return repo.listPageCTSP(index);
    }

    public int tongBanGhi() {
        return repo.tongBanGhi();
    }

    public boolean qrCode(String ma, String hinhAnh) {
        return repo.qrCode(ma, hinhAnh);
    }

}
