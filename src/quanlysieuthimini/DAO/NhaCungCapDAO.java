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
import quanlysieuthimini.DTO.NhaCungCapDTO;

public class NhaCungCapDAO implements DAOInterface<NhaCungCapDTO>{
    public static NhaCungCapDAO getInstance(){
        return new NhaCungCapDAO();
    }

    @Override
    public boolean insert(NhaCungCapDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "INSERT INTO `nhacungcap`(`MaNCC`, `TenNCC`, `DiaChi`, `Email`, `SDT`, `TrangThai`) VALUES (?,?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaNCC());
            pst.setString(2, t.getTenNCC());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getSDT());
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(NhaCungCapDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE `nhacungcap` SET `TenNCC`=?,`DiaChi`=?,`Email`=?,`SDT`=? WHERE `MaNCC`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenNCC());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getSDT());
            pst.setInt(5, t.getMaNCC());
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE nhacungcap SET TrangThai = 0 WHERE MaNCC = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> getAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhacungcap WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("MaNCC");
                String tenncc = rs.getString("TenNCC");
                String DiaChi = rs.getString("DiaChi");
                String Email = rs.getString("Email");
                String SDT = rs.getString("SDT");
                NhaCungCapDTO ncc = new NhaCungCapDTO(mancc, tenncc, DiaChi, Email, SDT);
                result.add(ncc);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NhaCungCapDTO getById(int id) {
        NhaCungCapDTO result = null;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM nhacungcap WHERE MaNCC=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("MaNCC");
                String tenncc = rs.getString("TenNCC");
                String DiaChi = rs.getString("DiaChi");
                String Email = rs.getString("Email");
                String SDT = rs.getString("SDT");
                
                result = new NhaCungCapDTO(mancc,tenncc,DiaChi,Email,SDT);
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
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanLysieuthimini' AND   TABLE_NAME   = 'nhacungcap'";
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
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

//    @Override
//    public ArrayList<NhaCungCapDTO> getByCondition(String condition) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
