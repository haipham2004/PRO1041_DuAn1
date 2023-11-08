/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhuyenMai;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class KhuyenMaiRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;
    List<KhuyenMai> listKm = new ArrayList<>(); 
    public List<KhuyenMai> getAll() {
        listKm.clear();
        try {
            con = DBConnect.getConnection();
            sql = "select * from Events";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai nv = new KhuyenMai(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), 
                        rs.getString(7), rs.getString(8));
                        
                listKm.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKm;
    }
}
