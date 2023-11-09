/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Events;
import model.Voucher;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class VoucherRepository {

    PreparedStatement ps = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<Voucher> listVoucher = new ArrayList<>();

    public List<Voucher> getAll() {
        listVoucher.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT EV.MaEV,EV.TenEV,EV.HinhThuc,EV.MucGiamGia,VC.MaVoucher,"
                    + "VC.SoLuong,VC.TrangThai FROM Events EV\n"
                    + "INNER JOIN MaVoucher VC ON EV.MaEV = VC.MaEV";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev=new Events(rs.getString(1), rs.getString(2), 
                        rs.getBoolean(3), rs.getString(4));
                Voucher vc = new Voucher(rs.getString(5),
                        rs.getInt(6), rs.getBoolean(7),ev); 
                listVoucher.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listVoucher;
    }
}
