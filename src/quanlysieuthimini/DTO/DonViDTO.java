
package quanlysieuthimini.DTO;

public class DonViDTO {
    int MaDV;
    String TenDV;
    
    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public DonViDTO() {
    }

    public DonViDTO(int MaDV, String TenDV) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;       
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public int getMaDV() {
        return MaDV;
    }

    public void setMaDV(int MaDV) {
        this.MaDV = MaDV;
    }

}
