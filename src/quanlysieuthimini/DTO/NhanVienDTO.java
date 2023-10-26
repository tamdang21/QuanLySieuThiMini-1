/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.util.Date;

public class NhanVienDTO {
    private int MaNV, TrangThai, GioiTinh;
    private String TenNV, DiaChi, SDT, Email, HinhAnh;
    private Date NgaySinh;
    private double Luong;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int MaNV, String TenNV, String DiaChi, String SDT, String Email, Date NgaySinh, int GioiTinh, double Luong, String HinhAnh, int TrangThai) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Luong = Luong;
        this.TrangThai = TrangThai;
        this.HinhAnh = HinhAnh;
    }
    
    public NhanVienDTO(int MaNV, String TenNV, String HinhAnh) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.HinhAnh = HinhAnh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double Luong) {
        this.Luong = Luong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
