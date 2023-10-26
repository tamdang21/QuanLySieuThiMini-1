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
import quanlysieuthimini.DAO.DAOInterface.ChiTietInterface;
import quanlysieuthimini.DTO.ChiTietPhieuNhapDTO;

public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO>{
    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "DELETE FROM chitietphieunhap WHERE MaPN = ?";
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
    public boolean insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        boolean result = false;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = ConnectionDB.openConnection();
                String sql = "INSERT INTO chitietphieunhap (MaPN,MaSP,DonGia,SoLuong,ThanhTien) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.setInt(1, t.get(i).getMaPN());
                pst.setInt(2, t.get(i).getMaSP());
                pst.setDouble(3, t.get(i).getDonGia());
                pst.setInt(4, t.get(i).getSoLuong());
                pst.setDouble(5, t.get(i).getThanhTien());
                
                result = pst.executeUpdate()>=1;
                ConnectionDB.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> getAll(int id) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<ChiTietPhieuNhapDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM chitietphieunhap WHERE MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                int MaSP = rs.getInt("MaSP");
                double DonGia = rs.getDouble("DonGia");
                int SoLuong = rs.getInt("SoLuong");
                double ThanhTien = rs.getDouble("ThanhTien");
                
                ChiTietPhieuNhapDTO dvt = new ChiTietPhieuNhapDTO(MaPN, MaSP, DonGia, SoLuong, ThanhTien);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public boolean update(ArrayList<ChiTietPhieuNhapDTO> t, int pk) {
        boolean result = this.delete(pk);
        if(result) 
            result = this.insert(t);
        return result;
    }
}
