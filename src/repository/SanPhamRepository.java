/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.SanPham;
import util.DBConnect1111111;

/**
 *
 * @author Admin BVCN88 02
 */
public class SanPhamRepository {
     PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<SanPham> listSanPham = new ArrayList<>();
     public List<SanPham> getAll() {
        try {
            conn = DBConnect1111111.getConnection();
            sql = "";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(4),
                        rs.getString(25), rs.getBoolean(6), rs.getString(4));
                SanPham sp=new SanPham(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), lsp);
                listSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  listSanPham;
    }
}
