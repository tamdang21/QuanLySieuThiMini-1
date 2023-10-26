
package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import quanlysieuthimini.DTO.ChucNangDTO;

public class ChucNangDAO {

    public static ChucNangDAO getInstance() {
        return new ChucNangDAO();
    }

    public ArrayList<ChucNangDTO> getAll() {
        ArrayList<ChucNangDTO> result = new ArrayList<>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM chucnang";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String machucnang = rs.getString("MaCN");
                String tenchucnang = rs.getString("TenCN");
                
                ChucNangDTO dvt = new ChucNangDTO(machucnang, tenchucnang);
                result.add(dvt);
            }
            ConnectionDB.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
