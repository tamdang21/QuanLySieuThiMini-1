package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.QuyenDTO;

public class QuyenDAO implements DAOInterface<QuyenDTO> {

    public static QuyenDAO getInstance() {
        return new QuyenDAO();
    }

    @Override
    public boolean insert(QuyenDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "INSERT INTO quyen (TenQuyen,TrangThai) VALUES (?,1)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, t.getTenQuyen());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(QuyenDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE quyen SET TenQuyen=? WHERE MaQuyen=?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, t.getTenQuyen());
            pst.setInt(2, t.getMaQuyen());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE quyen SET TrangThai = 0 WHERE MaQuyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<QuyenDTO> getAll() {
        ArrayList<QuyenDTO> result = new ArrayList<QuyenDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM quyen WHERE TrangThai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maquyen = rs.getInt("MaQuyen");
                String tenquyen = rs.getString("TenQuyen");
                
                QuyenDTO quyen = new QuyenDTO(maquyen, tenquyen);
                result.add(quyen);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public QuyenDTO getById(int id) {
        QuyenDTO result = null;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM quyen WHERE MaQuyen=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maquyen = rs.getInt("MaQuyen");
                String tenquyen = rs.getString("TenQuyen");
                result = new QuyenDTO(maquyen, tenquyen);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND TABLE_NAME = 'quyen'";
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
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

//    @Override
//    public ArrayList<QuyenDTO> getByCondition(String condition) {
//        ArrayList<QuyenDTO> result = new ArrayList<>();
//        
//        Connection connect = ConnectionDB.openConnection();
//        if (connect != null) {
//            try {
//                String sql = "SELECT * FROM quyen WHERE TrangThai=1 AND " + condition;
//
//                //Bước 2: tạo đối tượng preparedStatement
//                PreparedStatement stmt = connect.prepareStatement(sql); 
//
//                ResultSet rs = stmt.executeQuery();
//                
//                //Bước 3: lấy dữ liệu
//                while(rs.next()){
//                    QuyenDTO i = new QuyenDTO();
//                    i.setMaQuyen(rs.getInt(1));
//                    i.setTenQuyen(rs.getString(2));
//                    result.add(i);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                ConnectionDB.closeConnection(connect);
//            }
//        }
//        
//        return result;
//    }
}
