/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.SanPham;
import util.DBConnect1111111;
/**
 *
 * @author Admin BVCN88 02
 */
public class LoaiSanPhamRepository {
    PreparedStatement pst=null;
    Connection conn=null;
    ResultSet rs=null;
    String sql=null;
    List<LoaiSanPham> listLoaiSanPham=new ArrayList<>();
    public List<LoaiSanPham>  getAll(){
        try {
            conn=DBConnect1111111.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listLoaiSanPham;
    }
}
