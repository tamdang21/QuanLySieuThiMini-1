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
import quanlysieuthimini.DTO.KhachHangThanThietDTO;

public class KhachHangThanThietDAO implements DAOInterface<KhachHangThanThietDTO> {

    public static KhachHangThanThietDAO getInstance() {
        return new KhachHangThanThietDAO();
    }

    @Override
    public boolean insert(KhachHangThanThietDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT INTO `khachhang`(`MaKH`, `TenKH`, `DiaChi`,`SDT`,`DiemTichLuy`, `ChietKhauTheoDiem`, `TrangThai`) VALUES (?,?,?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, t.getMaKH());
            pst.setString(2, t.getTenKH());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getSDT());
            pst.setInt(5, t.getDiemTichLuy());
            pst.setDouble(6, t.getChietKhauTheoDiem());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangThanThietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(KhachHangThanThietDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE `khachhang` SET `TenKH`=?,`DiaChi`=?,`SDT`=?, DiemTichLuy=?, ChietKhauTheoDiem=? WHERE MaKH=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenKH());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getSDT());
            pst.setInt(4, t.getDiemTichLuy());
            pst.setDouble(5, t.getChietKhauTheoDiem());
            pst.setInt(6,t.getMaKH());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangThanThietDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(KhachHangThanThietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhachHangThanThietDTO> getAll() {
        ArrayList<KhachHangThanThietDTO> result = new ArrayList<KhachHangThanThietDTO>();
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
                int DiemTichLuy = rs.getInt("DiemTichLuy");
                double ChietKhauTheoDiem = rs.getDouble("ChietKhauTheoDiem");
                
                KhachHangThanThietDTO kh = new KhachHangThanThietDTO(MaKH, TenKH, DiaChi, SDT, DiemTichLuy, ChietKhauTheoDiem);
                result.add(kh);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public KhachHangThanThietDTO getById(int id) {
        KhachHangThanThietDTO result = null;
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
                int DiemTichLuy = rs.getInt("DiemTichLuy");
                double ChietKhauTheoDiem = rs.getDouble("ChietKhauTheoDiem");
                
                result = new KhachHangThanThietDTO(MaKH, TenKH, DiaChi, SDT, DiemTichLuy, ChietKhauTheoDiem);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public boolean upDiemTichLuy (int maKH, int diemCong){
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String query = "UPDATE khachhang SET DiemTichLuy = DiemTichLuy + ? WHERE MaKH = ?";
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
            preparedStatement.setInt(1, diemCong);
            preparedStatement.setInt(2, maKH);
            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            Logger.getLogger(KhachHangThanThietDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
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
            Logger.getLogger(KhachHangThanThietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
