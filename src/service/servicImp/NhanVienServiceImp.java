/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.NhanVien;

import repository.NhanVienRepository;
import service.AdamStore;

/**
 *
 * @author Admin
 */
public class NhanVienServiceImp implements AdamStore<NhanVien, String>{
    NhanVienRepository repo = new NhanVienRepository();

    @Override
    public List<NhanVien> getAll() {
        return repo.getAll();
    }

    @Override
    public int them(NhanVien k) {
        return repo.insert(k);
    }

    @Override
    public int sua(NhanVien k, String e) {
        return repo.update(k, e);
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien getOne(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> getList(String e) {
        return repo.getList(e);
    }
    public List<NhanVien> getList1(String e) {
        return repo.getList1(e);
    }
    public List<NhanVien> getList2(String e, String f){
        return repo.getList2(e, f);
    }
}
