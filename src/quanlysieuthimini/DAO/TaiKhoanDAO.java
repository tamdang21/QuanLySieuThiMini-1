package quanlysieuthimini.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlysieuthimini.DAO.DAOInterface.DAOInterface;
import quanlysieuthimini.DTO.TaiKhoanDTO;

public class TaiKhoanDAO implements DAOInterface<TaiKhoanDTO>{
    
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    @Override
    public boolean insert(TaiKhoanDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "INSERT INTO taikhoan (MaNV,MaQuyen,TenTK,MatKhau,TrangThai) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, t.getMaNV());
            pst.setInt(2, t.getMaQuyen());
            pst.setString(3, t.getTenTK());
            pst.setString(4, t.getMatKhau());
            pst.setInt(5, t.getTrangThai());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
            
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(TaiKhoanDTO t) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE taikhoan SET TenTK=?, TrangThai=?, MaQuyen=? WHERE MaNV=?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, t.getTenTK());
            pst.setInt(2, t.getTrangThai());
            pst.setInt(3, t.getMaQuyen());
            pst.setInt(4, t.getMaNV());
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void updatePass(String email, String password){
        int result;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE taikhoan tk join nhanvien nv on tk.MaNV=nv.MaNV SET MatKhau=? WHERE Email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, password);
            pst.setString(2, email);
            
            result = pst.executeUpdate();
            
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TaiKhoanDTO selectByEmail(String t) {
        TaiKhoanDTO tk = null;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM taikhoan tk join nhanvien nv on tk.MaNV=nv.MaNV where nv.Email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1,t);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("MaNV");
                String tendangnhap = rs.getString("TenTK");
                String matkhau = rs.getString("MatKhau");
                int trangthai = rs.getInt("TrangThai");
                int maquyen = rs.getInt("MaQuyen");
                
                tk = new TaiKhoanDTO(manv, maquyen, tendangnhap, matkhau, trangthai);
                return tk;
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
            
        }
        return tk;
    }
    
    public void sendOTP(String email, String opt){
        int result;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE taikhoan tk join nhanvien nv on tk.MaNV=nv.MaNV SET OTP=? WHERE Email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, opt);
            pst.setString(2, email);
            
            result = pst.executeUpdate();
            
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkOTP(String email, String otp){
        boolean check = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM taikhoan tk join nhanvien nv on tk.MaNV=nv.MaNV where nv.Email = ? and tk.OTP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, email);
            pst.setString(2, otp);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                check = true;
                return check;
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "UPDATE taikhoan SET TrangThai=0 where MaNV = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            result = pst.executeUpdate()>=1;
            ConnectionDB.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> getAll() {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM taikhoan WHERE TrangThai=1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                int manv = rs.getInt("MaNV");
                String tentk = rs.getString("TenTK");
                String matkhau = rs.getString("MatKhau");
                int maQuyen = rs.getInt("MaQuyen");
                int trangthai = rs.getInt("TrangThai");
                
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, maQuyen, tentk, matkhau, trangthai);
                result.add(tk);
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public TaiKhoanDTO getById(int id) {
        TaiKhoanDTO result = null;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM taikhoan WHERE MaNV=?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("MaNV");
                String tentk = rs.getString("TenTK");
                String matkhau = rs.getString("MatKhau");
                int maQuyen = rs.getInt("MaQuyen");
                int trangthai = rs.getInt("TrangThai");
                
                result = new TaiKhoanDTO(manv, maQuyen, tentk, matkhau, trangthai);
                return result;
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public TaiKhoanDTO selectByUser(String t) {
        TaiKhoanDTO result = null;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT * FROM taikhoan WHERE TenTK=?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, t);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("MaNV");
                String tentk = rs.getString("TenTK");
                String matkhau = rs.getString("MatKhau");
                int maQuyen = rs.getInt("MaQuyen");
                int trangthai = rs.getInt("TrangThai");
                
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, maQuyen, tentk, matkhau, trangthai);
                result = tk;
            }
            ConnectionDB.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = ConnectionDB.openConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND TABLE_NAME = 'taikhoan'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

//    @Override
//    public ArrayList<TaiKhoanDTO> getByCondition(String condition) {
//        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
//        
//        Connection connect = ConnectionDB.openConnection();
//        if (connect != null) {
//            try {
//                String sql = "SELECT * FROM taikhoan WHERE TrangThai=1 AND " + condition;
//
//                //Bước 2: tạo đối tượng preparedStatement
//                PreparedStatement stmt = connect.prepareStatement(sql); 
//
//                ResultSet rs = stmt.executeQuery();
//                
//                //Bước 3: lấy dữ liệu
//                while(rs.next()){
//                    TaiKhoanDTO user = new TaiKhoanDTO();
//                    user.setTenTK(rs.getString("TenTK"));
//                    user.setMaNV(rs.getInt("MaNV"));
//                    user.setMaQuyen(rs.getInt("MaQuyen"));
//                    user.setMatKhau(rs.getString("MatKhau"));
//                    user.setTrangThai(rs.getInt("TrangThai"));
//                    result.add(user);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                ConnectionDB.closeConnection(connect);
//            }
//        }
//        
//        return result;
//    }
}
