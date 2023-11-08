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
import util.DBConnect;

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
        listChatLieu.clear();
        try {
            conn = DBConnect.getConnection();
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
      public ChatLieu getOne(String ma){
           ChatLieu cl=null;
            try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM ChatLieu where MaChatLieu=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
            cl=new ChatLieu(rs.getString(1), 
                        rs.getString(2), rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
           return cl;
       }
    public int them(ChatLieu cl){
        try {
            conn=DBConnect.getConnection();
            sql="INSERT INTO ChatLieu VALUES(?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setObject(1, cl.getMaChatLieu());
            pst.setObject(2, cl.getTenChatLieu());
            pst.setObject(3, cl.isTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int sua(ChatLieu cl, String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChatLieu set TenChatLieu=?,TrangThai=?,where MaChatLieu=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, cl.getTenChatLieu());
            pst.setObject(2, cl.isTrangThai());
            pst.setObject(3, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
