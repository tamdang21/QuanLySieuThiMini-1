
package quanlysieuthimini.DTO;

public class LoaiSanPhamDTO {
    int MaLoai;
    String TenLoai;
    String CachBaoQuan;
    String MoTa;

    public LoaiSanPhamDTO() {
    }

    public LoaiSanPhamDTO(int MaLoai, String TenLoai, String CachBaoQuan, String MoTa) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;      
        this.CachBaoQuan = CachBaoQuan;
        this.MoTa = MoTa;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }
    
    public String getCachBaoQuan() {
        return CachBaoQuan;
    }

    public void setCachBaoQuan(String CachBaoQuan) {
        this.CachBaoQuan = CachBaoQuan;
    }
    
    public String getMoTa() {
        return MoTa;
    }

    public void setMaLoai(String MoTa) {
        this.MoTa = MoTa;
    }
}
