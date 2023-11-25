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
import quanlysieuthimini.DTO.PhieuChiDTO;

public class PhieuChiDAO implements DAOInterface<PhieuChiDTO>{
    public static PhieuChiDAO getInstance() {
        return new PhieuChiDAO();
    }
    
    @Override
    public boolean insert(PhieuChiDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT into phieuchi "
                        + "(MaPC,MaPN,MaNV,TenNguoiChi,NgayChi,LyDoChi,SoTienChi,GhiChu) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1,t.getMaPC());
            pst.setInt(2,t.getMaPN());
            pst.setInt(3,t.getMaNV());
            pst.setString(4,t.getTenNguoiChi());
            pst.setTimestamp(5, t.getNgayChi());
            pst.setString(6,t.getLyDoChi());
            pst.setDouble(7,t.getSoTienChi());
            pst.setString(8,t.getGhiChu());
            
            result = pst.executeUpdate()>=1;
            
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(PhieuChiDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();

        if (connect != null) {
            try {
                String sql = "UPDATE phieuchi SET "
                        + "MaPN=? , MaNV=? ,TenNguoiChi = ?,NgayChi =?,LyDoChi =?,SoTienChi =?, GhiChu =?"
                        + "WHERE MaPC=?";
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement pst = connect.prepareStatement(sql);
                
                pst.setInt(1,t.getMaPN());
                pst.setInt(2,t.getMaNV());
                pst.setString(3,t.getTenNguoiChi());
                pst.setTimestamp(4, t.getNgayChi());
                pst.setString(5,t.getLyDoChi());
                pst.setDouble(6,t.getSoTienChi());
                pst.setString(7,t.getGhiChu());
                pst.setInt(8,t.getMaPC());

                result = pst.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "UPDATE phieuchi SET TrangThai=0 "
                        + "WHERE MaPC=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);
                stmt.setInt(1, id);

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }

        return result;
    }

    @Override
    public ArrayList<PhieuChiDTO> getAll() {
        ArrayList<PhieuChiDTO> result = new ArrayList<>();

        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM phieuchi WHERE TrangThai=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                //Bước 3: lấy dữ liệu
                while (rs.next()) {
                    PhieuChiDTO hd = new PhieuChiDTO();
                    
                    hd.setMaPC(rs.getInt("MaPC"));
                    hd.setMaPN(rs.getInt("MaPN"));
                    hd.setMaNV(rs.getInt("MaNV"));
                    hd.setTenNguoiChi(rs.getString("TenNguoiChi"));
                    hd.setNgayChi(rs.getTimestamp("NgayChi"));
                    hd.setLyDoChi(rs.getString("LyDoChi"));
                    hd.setSoTienChi(rs.getDouble("SoTienChi"));
                    hd.setGhiChu(rs.getString("GhiChu"));
                    result.add(hd);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }

        return result;
    }

    @Override
    public PhieuChiDTO getById(int id) {
        PhieuChiDTO result = new PhieuChiDTO();

        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM phieuchi WHERE MaPC=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    result.setMaPC(rs.getInt("MaPC"));
                    result.setMaPN(rs.getInt("MaPN"));
                    result.setMaNV(rs.getInt("MaNV"));
                    result.setTenNguoiChi(rs.getString("TenNguoiChi"));
                    result.setNgayChi(rs.getTimestamp("NgayChi"));
                    result.setLyDoChi(rs.getString("LyDoChi"));
                    result.setSoTienChi(rs.getDouble("SoTienChi"));
                    result.setGhiChu(rs.getString("GhiChu"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'phieuchi'";
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
