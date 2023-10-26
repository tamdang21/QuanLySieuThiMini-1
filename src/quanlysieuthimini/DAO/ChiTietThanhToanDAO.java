/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.ChiTietInterface;
import quanlysieuthimini.DTO.ChiTietThanhToanDTO;

public class ChiTietThanhToanDAO implements ChiTietInterface<ChiTietThanhToanDTO> {
    
    public static ChiTietThanhToanDAO getInstance() {
        return new ChiTietThanhToanDAO();
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "DELETE FROM chitietthanhtoan WHERE MaHTTT = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean insert(ArrayList<ChiTietThanhToanDTO> t) {
        boolean result = false;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = ConnectionDB.openConnection();
                String sql = "INSERT INTO chitietthanhtoan (MaHTTT,MaHD,SoTien,NgayThanhToan) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.setInt(1, t.get(i).getMaHTTT());
                pst.setInt(2, t.get(i).getMaHD());
                pst.setDouble(3, t.get(i).getSoTien());
                pst.setTimestamp(4, t.get(i).getNgayThanhToan());
                
                result = pst.executeUpdate()>=1;
                ConnectionDB.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietThanhToanDTO> getAll(int id) {
        ArrayList<ChiTietThanhToanDTO> result = new ArrayList<ChiTietThanhToanDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM chitietthanhtoan WHERE MaHTTT = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaHTTT = rs.getInt("MaHTTT");
                int MaHD = rs.getInt("MaHD");
                double SoTien = rs.getDouble("SoTien");
                Timestamp NgayThanhToan = rs.getTimestamp("NgayThanhToan");
                
                ChiTietThanhToanDTO dvt = new ChiTietThanhToanDTO(MaHD, MaHTTT, SoTien, NgayThanhToan);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public boolean update(ArrayList<ChiTietThanhToanDTO> t, int pk) {
        boolean result = this.delete(pk);
        if(result) 
            result = this.insert(t);
        return result;
    }
}

    

