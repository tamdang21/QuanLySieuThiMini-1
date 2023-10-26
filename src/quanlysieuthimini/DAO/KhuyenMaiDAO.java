/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.KhachHangDTO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;

public class KhuyenMaiDAO implements DAOInterface<KhuyenMaiDTO> {

    public static KhuyenMaiDAO getInstance() {
        return new KhuyenMaiDAO();
    }
    
    @Override
    public boolean insert(KhuyenMaiDTO t) {
        boolean result = false;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "INSERT into khuyenmai "
                        + "(TenKM,DieuKienKM,PhanTramKM,NgayBatDau,NgayKetThuc) "
                        + "VALUES (?,?,?,?,?)";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            
                stmt.setString(1,t.getTenKM());
                stmt.setFloat(2,t.getDieuKienKM());
                stmt.setFloat(3,t.getPhanTramKM());
                stmt.setDate(4, Date.valueOf(t.getNgayBatDau()));
                stmt.setDate(5, Date.valueOf(t.getNgayKetThuc()));
            
            result = stmt.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(KhuyenMaiDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectionDB.openConnection();

        if (connect != null) {
            try {
                String sql = "UPDATE khuyenmai SET "
                        + "PhanTramKM=?, TenKM=?, NgayBatDau=?, NgayKetThuc=?, DieuKienKM=?"
                        + "WHERE MaKM=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                stmt.setFloat(1,t.getPhanTramKM());
                stmt.setString(2,t.getTenKM());
                stmt.setDate(3, Date.valueOf(t.getNgayBatDau()));
                stmt.setDate(4, Date.valueOf(t.getNgayKetThuc()));
                stmt.setFloat(5,t.getDieuKienKM());
                stmt.setInt(6,t.getMaKM());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE khuyenmai SET TrangThai=0 "
                        + "WHERE MaKM=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> getAll() {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();

        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {

            try {
                String sql = "SELECT * FROM khuyenmai WHERE TrangThai=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    KhuyenMaiDTO s = new KhuyenMaiDTO();
                    s.setMaKM(rs.getInt("MaKM"));
                    s.setTenKM(rs.getString("TenKM"));
                    s.setDieuKienKM(rs.getFloat("DieuKienKM"));
                    s.setPhanTramKM(rs.getFloat("PhanTramKM"));
                    s.setNgayBatDau(rs.getDate("NgayBatDau").toLocalDate());
                    s.setNgayKetThuc(rs.getDate("NgayKetThuc").toLocalDate());

                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }

        return result;    }

    @Override
    public KhuyenMaiDTO getById(int id) {
        KhuyenMaiDTO s = new KhuyenMaiDTO();

        Connection connect = ConnectionDB.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM khuyenmai WHERE TrangThai=1 AND MaKM= " + id  ;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    s.setMaKM(rs.getInt("MaKM"));
                    s.setTenKM(rs.getString("TenKM"));
                    s.setDieuKienKM(rs.getFloat("DieuKienKM"));
                    s.setPhanTramKM(rs.getFloat("PhanTramKM"));
                    s.setNgayBatDau(rs.getDate("NgayBatDau").toLocalDate());
                    s.setNgayKetThuc(rs.getDate("NgayKetThuc").toLocalDate());
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionDB.closeConnection(connect);
            }
        }

        return s;    
    }
    
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'khuyenmai'";
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
//    public ArrayList<KhuyenMaiDTO> getByCondition(String condition) {
//        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
//
//        Connection connect = ConnectionDB.openConnection();
//        if (connect != null) {
//
//            try {
//                String sql = "SELECT * FROM khuyenmai WHERE TrangThai=1 AND " + condition;
//
//                //Bước 2: tạo đối tượng preparedStatement
//                PreparedStatement stmt = connect.prepareStatement(sql);
//
//                ResultSet rs = stmt.executeQuery();
//
//                //Bước 3: lấy dữ liệu
//                while(rs.next()) {
//                    KhuyenMaiDTO s = new KhuyenMaiDTO();
//                    s.setMaKM(rs.getInt(1));
//                    s.setTenKM(rs.getString(2));
//                    s.setDieuKienKM(rs.getFloat(3));
//                    s.setPhanTramKM(rs.getFloat(4));
//                    s.setNgayBatDau(rs.getDate(5).toLocalDate());
//                    s.setNgayKetThuc(rs.getDate(6).toLocalDate());
//
//                    result.add(s);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                ConnectionDB.closeConnection(connect);
//            }
//        }
//
//        return result;
//    }
    
}
