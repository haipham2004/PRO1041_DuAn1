/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Events;
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
    List<Events> listKm = new ArrayList<>();

    public List<Events> getAll() {
        listKm.clear();
        try {
            con = DBConnect.getConnection();
            sql = "select * from Events";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4),
                        rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKm;
    }

    public int themEvents(Events ev) {
        try {
            con = DBConnect.getConnection();
            sql = "insert into Events values (?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ev.getMaEventa());
            ps.setObject(2, ev.getTenEvent());
            ps.setObject(3, ev.isHinhThuc());
            ps.setObject(4, ev.getMucGiamGia());
            ps.setObject(5, ev.getThoiGianBatDau());
            ps.setObject(6, ev.getThoiGianKetThuc());
            ps.setObject(7, ev.getMoTa());
            ps.setObject(8, ev.isTrangThai());
            ps.setObject(9, ev.isDieuKienApDung());
            ps.setObject(10, ev.getDieuKienTongTien());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
