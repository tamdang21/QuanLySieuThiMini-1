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
import quanlysieuthimini.DTO.NhanVienDTO;

public class NhanVienDAO implements DAOInterface<NhanVienDTO>{
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }

    @Override
    public boolean insert(NhanVienDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT INTO `nhanvien`(`TenNV`, `DiaChi`, `SDT`,`NgaySinh`,`GioiTinh`,`Email`,`Luong`,`HinhAnh`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenNV());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getSDT());
            pst.setDate(4, (Date) (t.getNgaySinh()));
            pst.setInt(5, t.getGioiTinh());
            pst.setString(6, t.getEmail());
            pst.setDouble(7, t.getLuong());
            pst.setString(8, t.getHinhAnh());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(NhanVienDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "UPDATE `nhanvien` SET `TenNV`=?, `DiaChi`=?, `SDT`=?,`NgaySinh`=?,`GioiTinh`=?,`Email`=?,`Luong`=?,`HinhAnh`=?,`TrangThai`=?  WHERE `MaNV`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenNV());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getSDT());
            pst.setDate(4, (Date) (t.getNgaySinh()));
            pst.setInt(5, t.getGioiTinh());
            pst.setString(6, t.getEmail());
            pst.setDouble(7, t.getLuong());
            pst.setString(8, t.getHinhAnh());
            pst.setInt(9, t.getTrangThai());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "Update nhanvien set `TrangThai` = 0 WHERE MaNV = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> getAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhanvien WHERE TrangThai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                int GioiTinh = rs.getInt("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");
                int TrangThai = rs.getInt("TrangThai");
                String Email = rs.getString("Email");
                String DiaChi = rs.getString("DiaChi");
                Double Luong = rs.getDouble("Luong");
                String HinhAnh = rs.getString("HinhAnh");
                
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, DiaChi, SDT, Email, NgaySinh, GioiTinh, Luong, HinhAnh, TrangThai);
                result.add(nv);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                int GioiTinh = rs.getInt("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");
                int TrangThai = rs.getInt("TrangThai");
                String Email = rs.getString("Email");
                String DiaChi = rs.getString("DiaChi");
                Double Luong = rs.getDouble("Luong");
                String HinhAnh = rs.getString("HinhAnh");
                
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, DiaChi, SDT, Email, NgaySinh, GioiTinh, Luong, HinhAnh, TrangThai);
                result.add(nv);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<NhanVienDTO> selectAllNV() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhanvien nv where nv.TrangThai = 1 and not EXISTS(SELECT * FROM taikhoan tk WHERE nv.MaNV=tk.MaNV)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                int GioiTinh = rs.getInt("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");
                int TrangThai = rs.getInt("TrangThai");
                String Email = rs.getString("Email");
                String DiaChi = rs.getString("DiaChi");
                Double Luong = rs.getDouble("Luong");
                String HinhAnh = rs.getString("HinhAnh");
                
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, DiaChi, SDT, Email, NgaySinh, GioiTinh, Luong, HinhAnh, TrangThai);
                result.add(nv);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    

    @Override
    public NhanVienDTO getById(int id) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                int GioiTinh = rs.getInt("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");
                int TrangThai = rs.getInt("TrangThai");
                String Email = rs.getString("Email");
                String DiaChi = rs.getString("DiaChi");
                Double Luong = rs.getDouble("Luong");
                String HinhAnh = rs.getString("HinhAnh");
                
                result = new NhanVienDTO(MaNV, TenNV, DiaChi, SDT, Email, NgaySinh, GioiTinh, Luong, HinhAnh, TrangThai);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public NhanVienDTO selectByEmail(String t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhanvien WHERE Email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MaNV = rs.getInt("MaNV");
                String TenNV = rs.getString("TenNV");
                int GioiTinh = rs.getInt("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String SDT = rs.getString("SDT");
                int TrangThai = rs.getInt("TrangThai");
                String Email = rs.getString("Email");
                String DiaChi = rs.getString("DiaChi");
                Double Luong = rs.getDouble("Luong");
                String HinhAnh = rs.getString("HinhAnh");
                
                result = new NhanVienDTO(MaNV, TenNV, DiaChi, SDT, Email, NgaySinh, GioiTinh, Luong, HinhAnh, TrangThai);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'nhanvien'";
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
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

//    @Override
//    public ArrayList<NhanVienDTO> getByCondition(String condition) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
