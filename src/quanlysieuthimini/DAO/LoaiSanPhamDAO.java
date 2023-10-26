/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.LoaiSanPhamDTO;

public class LoaiSanPhamDAO implements DAOInterface<LoaiSanPhamDTO>{
    public static LoaiSanPhamDAO getInstance() {
        return new LoaiSanPhamDAO();
    }

    @Override
    public boolean insert(LoaiSanPhamDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT INTO `loaisanpham`(`MaLoai`, `TenLoai`,`CachBaoQuan`, `MoTa` , `TrangThai`) VALUES (?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaLoai());
            pst.setString(2, t.getTenLoai());
            pst.setString(3, t.getCachBaoQuan());
            pst.setString(4, t.getMoTa());
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public boolean update(LoaiSanPhamDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE `loaisanpham` SET `TenLoai`=?, `CachBaoQuan`=?, `MoTa`=? WHERE `MaLoai`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenLoai());
            pst.setString(2, t.getCachBaoQuan());
            pst.setString(3, t.getMoTa());
            pst.setInt(4, t.getMaLoai());
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE loaisanpham SET TrangThai = 0 WHERE MaLoai = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   
    @Override
    public ArrayList<LoaiSanPhamDTO> getAll() {
        ArrayList<LoaiSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM loaisanpham WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("MaLoai");
                String tenncc = rs.getString("TenLoai");
                String CachBQ = rs.getString("CachBaoQuan");
                String MoTa = rs.getString("MoTa");
                LoaiSanPhamDTO ncc = new LoaiSanPhamDTO(mancc, tenncc, CachBQ, MoTa);
                result.add(ncc);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public LoaiSanPhamDTO getById(int id) {
        LoaiSanPhamDTO result = null;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM loaisanpham WHERE MaLoai=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("MaLoai");
                String tenncc = rs.getString("TenLoai");
                String CachBQ = rs.getString("CachBaoQuan");
                String MoTa = rs.getString("MoTa");
                
                result = new LoaiSanPhamDTO(mancc,tenncc,CachBQ,MoTa);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'loaisanpham'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
