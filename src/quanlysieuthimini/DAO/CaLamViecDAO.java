/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.CaLamViecDTO;

public class CaLamViecDAO implements DAOInterface<CaLamViecDTO>{
    public static CaLamViecDAO getInstance() {
        return new CaLamViecDAO();
    }
    
    @Override
    public boolean insert(CaLamViecDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "INSERT INTO `calamviec`(`TenCa`, `GioBatDau`,`GioKetThuc`, `LuongTheoCa`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenCa());
            pst.setTime(2, Time.valueOf(t.getGioBatDau()));
            pst.setTime(3, Time.valueOf(t.getGioKetThuc()));
            pst.setDouble(4, t.getLuongTheoCa());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public boolean update(CaLamViecDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE `calamviec` SET `TenCa`=?, `GioBatDau`=?, `GioKetThuc`=?, `LuongTheoCa`=? WHERE `MaCa`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenCa());
            pst.setTime(2, Time.valueOf(t.getGioBatDau()));
            pst.setTime(3, Time.valueOf(t.getGioKetThuc()));
            pst.setDouble(4, t.getLuongTheoCa());
            pst.setInt(5, t.getMaCa());
            
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
            String sql = "UPDATE calamviec SET TrangThai = 0 WHERE MaCa = ?";
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
    public ArrayList<CaLamViecDTO> getAll() {
        ArrayList<CaLamViecDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM calamviec WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaCa = rs.getInt("MaCa");
                String TenCa = rs.getString("TenCa");
                Time GioBatDau = rs.getTime("GioBatDau");
                Time GioKetThuc = rs.getTime("GioKetThuc");
                double LuongTheoCa = rs.getDouble("LuongTheoCa");
                
                CaLamViecDTO ncc = new CaLamViecDTO(MaCa, TenCa, GioBatDau.toLocalTime(), GioKetThuc.toLocalTime(), LuongTheoCa);
                result.add(ncc);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CaLamViecDTO getById(int id) {
        CaLamViecDTO result = null;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM calamviec WHERE MaCa=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaCa = rs.getInt("MaCa");
                String TenCa = rs.getString("TenCa");
                Time GioBatDau = rs.getTime("GioBatDau");
                Time GioKetThuc = rs.getTime("GioKetThuc");
                double LuongTheoCa = rs.getDouble("LuongTheoCa");
                
                result = new CaLamViecDTO(MaCa, TenCa, GioBatDau.toLocalTime(), GioKetThuc.toLocalTime(), LuongTheoCa);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'calamviec'";
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
