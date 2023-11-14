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
import quanlysieuthimini.DAO.DAOInterface.ChiTietInterface;
import quanlysieuthimini.DTO.PhanCongCaDTO;

public class PhanCongCaDAO implements ChiTietInterface<PhanCongCaDTO>{
    public static PhanCongCaDAO getInstance() {
        return new PhanCongCaDAO();
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "Update phancongca set `TrangThai` = 0 WHERE MaCa = ?";
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
    public boolean insert(ArrayList<PhanCongCaDTO> t) {
        boolean result = false;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = ConnectionDB.openConnection();
                String sql = "INSERT INTO phancongca (MaCa,MaNV,Thu) VALUES (?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaCa());
                pst.setInt(2, t.get(i).getMaNV());
                pst.setString(3, t.get(i).getThu());
                result = pst.executeUpdate()>=1;
                ConnectionDB.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ArrayList<PhanCongCaDTO> getAll(int id) {
        ArrayList<PhanCongCaDTO> result = new ArrayList<PhanCongCaDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM phancongca WHERE MaCa = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaCa = rs.getInt("MaCa");
                int MaNV = rs.getInt("MaNV");
                String Thu = rs.getString("Thu");
                PhanCongCaDTO dvt = new PhanCongCaDTO(MaCa, MaNV, Thu);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public ArrayList<PhanCongCaDTO> getAllNotId() {
        ArrayList<PhanCongCaDTO> result = new ArrayList<PhanCongCaDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM phancongca WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaCa = rs.getInt("MaCa");
                int MaNV = rs.getInt("MaNV");
                String Thu = rs.getString("Thu");
                PhanCongCaDTO dvt = new PhanCongCaDTO(MaCa, MaNV, Thu);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public boolean update(ArrayList<PhanCongCaDTO> t, int pk) {
        boolean result = this.delete(pk);
        if(result) 
            result = this.insert(t);
        return result;
    }
}
