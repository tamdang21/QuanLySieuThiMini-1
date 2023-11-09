package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date; 
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.PhieuNhapDTO;

public class PhieuNhapDAO implements DAOInterface<PhieuNhapDTO> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    
    
    @Override
    public boolean insert(PhieuNhapDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT INTO `phieunhap`(`MaPN`, `MaNV`, `MaNCC`, `NgayNhap`, `TongTien`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, t.getMaPN());
            pst.setInt(2, t.getMaNV());
            pst.setInt(3, t.getMaNCC());
            pst.setTimestamp(4, t.getNgayNhap());
            pst.setDouble(5, t.getTongTien());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(PhieuNhapDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE `phieunhap` SET `MaNCC`=?,`NgayNhap`=?,`TongTien`=?,`TrangThai`=? WHERE `MaPN`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, t.getMaNCC());
            pst.setTimestamp(2, t.getNgayNhap());
            pst.setDouble(3, t.getTongTien());
            pst.setInt(4, t.getTrangThai());
            pst.setInt(5, t.getMaPN());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE phieunhap SET TrangThai = 0 WHERE MaPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> getAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM phieunhap ORDER BY MaPN DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                int MaNCC = rs.getInt("MaNCC");
                int MaNV = rs.getInt("MaNV");
                Timestamp NgayNhap = rs.getTimestamp("NgayNhap");
                double TongTien = rs.getDouble("TongTien");
                int TrangThai = rs.getInt("TrangThai");
                
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(MaPN, MaNV, MaNCC, NgayNhap, TongTien, TrangThai);
                result.add(phieunhap);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public PhieuNhapDTO getById(int id) {
        PhieuNhapDTO result = null;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM phieunhap WHERE MaPN=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                int MaNCC = rs.getInt("MaNCC");
                int MaNV = rs.getInt("MaNV");
                Timestamp NgayNhap = rs.getTimestamp("NgayNhap");
                double TongTien = rs.getDouble("TongTien");
                int TrangThai = rs.getInt("TrangThai");
                
                result = new PhieuNhapDTO(MaPN, MaNV, MaNCC, NgayNhap, TongTien, TrangThai);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> statistical(long min, long max) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM phieunhap WHERE TongTien BETWEEN ? AND ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setDouble(1, min);
            pst.setDouble(2,max);

            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                int MaNCC = rs.getInt("MaNCC");
                int MaNV = rs.getInt("MaNV");
                Timestamp NgayNhap = rs.getTimestamp("NgayNhap");
                double TongTien = rs.getDouble("TongTien");
                int TrangThai = rs.getInt("TrangThai");
                
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(MaPN, MaNV, MaNCC, NgayNhap, TongTien, TrangThai);
                result.add(phieunhap);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND TABLE_NAME   = 'phieunhap'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
