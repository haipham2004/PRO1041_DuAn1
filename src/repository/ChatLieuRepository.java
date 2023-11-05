/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.MauSac;
import util.DBConnect1111111;

/**
 *
 * @author Admin BVCN88 02
 */
public class ChatLieuRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<ChatLieu> listChatLieu = new ArrayList<>();

    public List<ChatLieu> getAll() {
        try {
            conn = DBConnect1111111.getConnection();
            sql = "SELECT*FROM ChatLieu";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listChatLieu.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChatLieu;
    }

}
