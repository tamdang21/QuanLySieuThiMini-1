/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class KhachHangThanThietDTO {
    private int MaKH, DiemTichLuy;
    private String TenKH, DiaChi, SDT;
    private double ChietKhauTheoDiem;

    public KhachHangThanThietDTO() {
    }

    public KhachHangThanThietDTO(int MaKH, String TenKH, String DiaChi, String SDT, int DiemTichLuy, double ChietKhauTheoDiem) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.DiemTichLuy = DiemTichLuy;
        this.ChietKhauTheoDiem = ChietKhauTheoDiem;
    }

    public int getDiemTichLuy() {
        return DiemTichLuy;
    }

    public void setDiemTichLuy(int DiemTichLuy) {
        this.DiemTichLuy = DiemTichLuy;
    }

    public double getChietKhauTheoDiem() {
        return ChietKhauTheoDiem;
    }

    public void setChietKhauTheoDiem(double ChietKhauTheoDiem) {
        this.ChietKhauTheoDiem = ChietKhauTheoDiem;
    }
    
    

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
}
