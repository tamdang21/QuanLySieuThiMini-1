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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.DonViDTO;

public class DonViDAO implements DAOInterface<DonViDTO>{
    public static DonViDAO getInstance() {
        return new DonViDAO();
    }

    @Override
    public boolean insert(DonViDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "INSERT INTO `donvi`(`MaDV`, `TenDV`, `TrangThai`) VALUES (?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, t.getMaDV());
            pst.setString(2, t.getTenDV());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(DonViDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE `donvi` SET `TenDV`=? WHERE `MaDV`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, t.getTenDV());
            pst.setInt(5, t.getMaDV());
            
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
            String sql = "UPDATE donvi SET TrangThai = 0 WHERE MaDV = ?";
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
    public ArrayList<DonViDTO> getAll() {
        ArrayList<DonViDTO> result = new ArrayList<>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM donvi WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("MaDV");
                String tenncc = rs.getString("TenDV");
                DonViDTO ncc = new DonViDTO(mancc, tenncc);
                result.add(ncc);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public DonViDTO getById(int id) {
        DonViDTO result = null;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM donvi WHERE MaDV=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("MaDV");
                String tenncc = rs.getString("TenDV");
                
                result = new DonViDTO(mancc,tenncc);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<DonViDTO> getByCondition(String condition) {
        ArrayList<DonViDTO> result = new ArrayList<>();
        
        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM donvi WHERE TrangThai=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maDV = rs.getInt("MaDV");
                    String tenDV = rs.getString("TenDV");
                    
                    DonViDTO s = new DonViDTO(maDV, tenDV);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }
        
        return result;
    }
    
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'donvi'";
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
            Logger.getLogger(DonViDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
