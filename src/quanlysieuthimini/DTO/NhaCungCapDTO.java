
package quanlysieuthimini.DTO;

import java.util.Objects;

public class NhaCungCapDTO {
    private int MaNCC;
    private String TenNCC;
    private String DiaChi;
    private String Email;
    private String SDT;

    public NhaCungCapDTO() {
        
    }

    public NhaCungCapDTO(int MaNCC, String TenNCC, String DiaChi, String Email, String SDT) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SDT = SDT;
    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.MaNCC;
        hash = 67 * hash + Objects.hashCode(this.TenNCC);
        hash = 67 * hash + Objects.hashCode(this.DiaChi);
        hash = 67 * hash + Objects.hashCode(this.Email);
        hash = 67 * hash + Objects.hashCode(this.SDT);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhaCungCapDTO other = (NhaCungCapDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "MaNCC=" + MaNCC + ", TenNCC=" + TenNCC + ", DiaChi=" + DiaChi + ", Email=" + Email + ", SDT=" + SDT + '}';
    }
    
}
