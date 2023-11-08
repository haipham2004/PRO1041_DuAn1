/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Voucher;
import util.DBConnect;

/**
 *
 * @author Admin
 */
public class VoucherRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<Voucher> listVoucher = new ArrayList<>();

    public List<Voucher> getAll() {
        listVoucher.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT * FROM Voucher";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Voucher vc = new Voucher(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getBoolean(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listVoucher;
    }
}
