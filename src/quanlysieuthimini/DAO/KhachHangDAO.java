/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.KhachHangDTO;

public class KhachHangDAO implements DAOInterface<KhachHangDTO> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public boolean insert(KhachHangDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT INTO `khachhang`(`MaKH`, `TenKH`, `DiaChi`,`SDT`, `TrangThai`) VALUES (?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, t.getMaKH());
            pst.setString(2, t.getTenKH());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getSDT());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(KhachHangDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE `khachhang` SET `TenKH`=?,`DiaChi`=?,`SDT`=? WHERE MaKH=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenKH());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getSDT());
            pst.setInt(4,t.getMaKH());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE  `khachhang` SET TrangThai=0 WHERE `MaKH` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> getAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM khachhang WHERE TrangThai=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                
                KhachHangDTO kh = new KhachHangDTO(MaKH, TenKH, DiaChi, SDT);
                result.add(kh);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public KhachHangDTO getById(int id) {
        KhachHangDTO result = null;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM khachhang WHERE MaKH=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                
                result = new KhachHangDTO(MaKH, TenKH, DiaChi, SDT);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'khachhang'";
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
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
