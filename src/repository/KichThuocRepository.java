/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KichThuoc;
import model.MauSac;
import util.DBConnect1111111;

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
            conn = DBConnect1111111.getConnection();
            sql = "";
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
}
