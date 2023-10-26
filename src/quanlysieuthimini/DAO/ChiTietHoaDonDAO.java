/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.ChiTietInterface;

import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.ChiTietHoaDonDTO;
import quanlysieuthimini.DTO.HoaDonDTO;
import quanlysieuthimini.DTO.KhachHangDTO;

public class ChiTietHoaDonDAO implements ChiTietInterface<ChiTietHoaDonDTO> {

    public static ChiTietHoaDonDAO getInstance() {
        return new ChiTietHoaDonDAO();
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "DELETE FROM chitiethoadon WHERE MaHD = ?";
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
    public boolean insert(ArrayList<ChiTietHoaDonDTO> t) {
        boolean result = false;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = ConnectionDB.openConnection();
                String sql = "INSERT INTO chitiethoadon (MaHD,MaSP,DonGia,SoLuong,ThanhTien) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.setInt(1, t.get(i).getMaHD());
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
    public ArrayList<ChiTietHoaDonDTO> getAll(int id) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<ChiTietHoaDonDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM chitiethoadon WHERE MaHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                int MaSP = rs.getInt("MaSP");
                double DonGia = rs.getDouble("DonGia");
                int SoLuong = rs.getInt("SoLuong");
                double ThanhTien = rs.getDouble("ThanhTien");
                
                ChiTietHoaDonDTO dvt = new ChiTietHoaDonDTO(MaHD, MaSP, DonGia, SoLuong, ThanhTien);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public boolean update(ArrayList<ChiTietHoaDonDTO> t, int pk) {
        boolean result = this.delete(pk);
        if(result) 
            result = this.insert(t);
        return result;
    }
    
}
