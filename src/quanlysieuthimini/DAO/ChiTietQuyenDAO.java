package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.ChiTietInterface;
import quanlysieuthimini.DTO.ChiTietQuyenDTO;

public class ChiTietQuyenDAO implements ChiTietInterface<ChiTietQuyenDTO> {

    public static ChiTietQuyenDAO getInstance() {
        return new ChiTietQuyenDAO();
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "DELETE FROM chitietquyen WHERE MaQuyen = ?";
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
    public boolean insert(ArrayList<ChiTietQuyenDTO> t) {
        boolean result = false;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = ConnectionDB.openConnection();
                String sql = "INSERT INTO chitietquyen (MaQuyen,MaCN,HanhDong) VALUES (?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaQuyen());
                pst.setString(2, t.get(i).getMaCN());
                pst.setString(3, t.get(i).getHanhDong());
                result = pst.executeUpdate()>=1;
                ConnectionDB.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietQuyenDTO> getAll(int id) {
        ArrayList<ChiTietQuyenDTO> result = new ArrayList<ChiTietQuyenDTO>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM chitietquyen WHERE MaQuyen = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MaQuyen = rs.getInt("MaQuyen");
                String MaCN = rs.getString("MaCN");
                String hanhdong = rs.getString("HanhDong");
                ChiTietQuyenDTO dvt = new ChiTietQuyenDTO(MaQuyen, MaCN, hanhdong);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public boolean update(ArrayList<ChiTietQuyenDTO> t, int pk) {
        boolean result = this.delete(pk);
        if(result) 
            result = this.insert(t);
        return result;
    }
}
