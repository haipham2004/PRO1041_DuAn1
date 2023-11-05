/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.MauSac;
import util.DBConnect;
/**
 *
 * @author Admin BVCN88 02
 */
public class MauSacRepository {
    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<MauSac> listMauSac = new ArrayList<>();
     public List<MauSac> getAll() {
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM MauSac";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                MauSac ms=new MauSac(rs.getString(1), 
                        rs.getString(2), rs.getBoolean(3));
                listMauSac.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listMauSac;
    }
     public MauSac getOne(String ma){
           MauSac ms=null;
            try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM MauSac where MaMauSac=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
            ms=new MauSac(rs.getString(1), 
                        rs.getString(2), rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
           return ms;
       }
      public int them(MauSac ms){
        try {
            conn=DBConnect.getConnection();
            sql="INSERT INTO MauSac VALUES(?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setObject(1, ms.getMaMauSac());
            pst.setObject(2, ms.getTenMauSac());
            pst.setObject(3, ms.isTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
