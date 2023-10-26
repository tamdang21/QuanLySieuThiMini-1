/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.HoaDonDTO;
import quanlysieuthimini.DTO.NhaCungCapDTO;

public class HoaDonDAO implements DAOInterface<HoaDonDTO> {

    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }
    
    @Override
    public boolean insert(HoaDonDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT into hoadon "
                        + "(MaKH,MaKM,MaNV,NgayLap,TongTien) "
                        + "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1,t.getMaKH());
            pst.setInt(2,t.getMaKM());
            pst.setInt(3,t.getMaNV());
            pst.setTimestamp(4, t.getNgayLap());
            pst.setDouble(5,t.getTongTien());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(HoaDonDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();

        if (connect != null) {
            try {
                String sql = "UPDATE hoadon SET "
                        + "MaKH=? , MaKM=? ,MaNV = ?,NgayLap =?,TongTien =?,TrangThai =?"
                        + "WHERE MaHD=?";
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);
                stmt.setInt(1,t.getMaKH());
                stmt.setInt(2,t.getMaKM());
                stmt.setInt(3,t.getMaNV());
                stmt.setTimestamp(4, t.getNgayLap());
                stmt.setDouble(5,t.getTongTien());
                stmt.setInt(6,t.getTrangThai());
                stmt.setInt(7,t.getMaHD());

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
    public boolean delete(int id) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "UPDATE hoadon SET TrangThai=0 "
                        + "WHERE MaHD=?";

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
    public ArrayList<HoaDonDTO> getAll() {
        ArrayList<HoaDonDTO> result = new ArrayList<>();

        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM hoadon WHERE TrangThai=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                //Bước 3: lấy dữ liệu
                while (rs.next()) {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setMaHD(rs.getInt(1));
                    hd.setMaKH(rs.getInt(2));
                    hd.setMaKM(rs.getInt(3));
                    hd.setMaNV(rs.getInt(4));
                    hd.setNgayLap(rs.getTimestamp(5));
                    hd.setTongTien(rs.getDouble(6));
                    hd.setTrangThai(rs.getInt(7));
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
    public HoaDonDTO getById(int id) {
        HoaDonDTO result = new HoaDonDTO();

        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM hoadon WHERE TrangThai=1 AND MaHD=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    result.setMaHD(rs.getInt(1));
                    result.setMaKH(rs.getInt(2));
                    result.setMaKM(rs.getInt(3));
                    result.setMaNV(rs.getInt(4));
                    result.setNgayLap(rs.getTimestamp(5));
                    result.setTongTien(rs.getDouble(6));
                    result.setTrangThai(rs.getInt(7));
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'hoadon'";
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

//    @Override
//    public ArrayList<HoaDonDTO> getByCondition(String condition) {
//        ArrayList<HoaDonDTO> result = new ArrayList<>();
//
//        Connection connect = ConnectionDB.openConnection();
//        if (connect != null) {
//
//            try {
//                String sql = "SELECT * FROM hoadon WHERE TrangThai=1 AND " + condition;
//
//                //Bước 2: tạo đối tượng preparedStatement
//                PreparedStatement stmt = connect.prepareStatement(sql);
//
//                ResultSet rs = stmt.executeQuery();
//
//                //Bước 3: lấy dữ liệu
//                while(rs.next()) {
//                    HoaDonDTO hd = new HoaDonDTO();
//                    hd.setMaHD(rs.getInt(1));
//                    hd.setMaKH(rs.getInt(2));
//                    hd.setMaKM(rs.getInt(3));
//                    hd.setMaNV(rs.getInt(4));
//                    hd.setNgayLap(rs.getDate(5).toLocalDate());
//                    hd.setTongTien(rs.getDouble(6));
//                    hd.setTrangThai(rs.getBoolean(7));
//                    result.add(hd);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                ConnectionDB.closeConnection(connect);
//            }
//        }
//
//        return result;    
//    }
    
}
