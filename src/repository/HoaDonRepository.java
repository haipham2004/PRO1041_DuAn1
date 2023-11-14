/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.NhanVien;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDon> getHoaDonCho() {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "Select HD.MaHoaDon,NV.MaNV,NV.HoTen,HD.NgayTao,HD.TrangThai\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV where HD.TrangThai = 0";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2), rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, rs.getDate(4), rs.getBoolean(5));
                listHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listHoaDon;
    }
    
    public int countHoaDon(){
        int tongHoaDon = 0;
        try {
            sql = "Select COUNT(*) From HoaDon";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongHoaDon = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tongHoaDon;
    }
    
}
