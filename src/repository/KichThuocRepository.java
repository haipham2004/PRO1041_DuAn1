/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KichThuoc;
import util.DBConnect;

/**
 *
 * @author Admin BVCN88 02
 */
public class KichThuocRepository {
    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<KichThuoc> listKichThuoc = new ArrayList<>();
       public List<KichThuoc> getAll() {
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM KichThuoc";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                KichThuoc kt=new KichThuoc(rs.getString(1), 
                        rs.getString(2), rs.getBoolean(3));
                listKichThuoc.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKichThuoc;
    }
       public KichThuoc getOne(String ma){
           KichThuoc kt=null;
            try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM KichThuoc where MaKichThuoc=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
            kt=new KichThuoc(rs.getString(1), 
                        rs.getString(2), rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
           return kt;
       }
        public int them(KichThuoc kt){
        try {
            conn=DBConnect.getConnection();
            sql="INSERT INTO KichThuoc VALUES(?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setObject(1, kt.getMaKichThuoc());
            pst.setObject(2, kt.getTenKichThuoc());
            pst.setObject(3, kt.isTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
