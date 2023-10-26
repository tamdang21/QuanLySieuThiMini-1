/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class TaiKhoanDTO {
    private int MaNV, MaQuyen;
    private String TenTK, MatKhau;
    private int TrangThai;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(int MaNV, int MaQuyen, String TenTK, String MatKhau, int TrangThai) {
        this.MaNV = MaNV;
        this.MaQuyen = MaQuyen;
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.TrangThai = TrangThai;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}
