/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import model.ChiTietSanPham;
import model.MauSac;
import model.SanPham;
import repository.ChiTietSanPhamRepository;
import repository.MauSacRepository;
import repository.SanPhamRepository;

/**
 *
 * @author Admin BVCN88 02
 */
public class Test {
    public static void main(String[] args) {
        SanPhamRepository repo=new SanPhamRepository();
        ChiTietSanPhamRepository repo2=new ChiTietSanPhamRepository();
        MauSacRepository repo3=new MauSacRepository();
        List<SanPham> lisp=repo.listPageSP(4);
        for (SanPham sanPham : lisp) {
            System.out.println("Sản phẩm: "+sanPham);
        }
        List<ChiTietSanPham> list2=repo2.listPageCTSP(2);
        for (ChiTietSanPham chiTietSanPham : list2) {
            System.out.println("CTSP: "+chiTietSanPham);
        }
        List<MauSac> list3=repo3.listPageMS(1);
        for (MauSac mauSac : list3) {
            System.out.println("Màu sắc: "+mauSac);
        }
        System.out.println("Hải Phạm");
        int tong=repo.tongBanGhi();
        System.out.println("Tổng: "+tong);
    }
}
