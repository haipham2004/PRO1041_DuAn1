/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.SanPham;
import repository.SanPhamRepository;
import service.AdamStore;

/**
 *
 * @author Admin BVCN88 02
 */
public class SanPhamServiceImp implements AdamStore<SanPham, String>{
    SanPhamRepository repo=new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return repo.getAll();
    }

    @Override
    public int them(SanPham k) {
        return repo.them(k);
    }

    @Override
    public int sua(SanPham k, String e) {
        return repo.sua(k, e);
    }

    @Override
    public int xoa(String e) {
        return repo.xoa(e);
    }

    @Override
    public SanPham getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<SanPham> getList(String e) {
        return repo.getList(e);
    }
    
}
