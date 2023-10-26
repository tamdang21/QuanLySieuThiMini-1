/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.sql.Timestamp;

public class PhieuChiDTO {
    private int MaPC, MaPN, MaNV;
    private String TenNguoiChi, LyDoChi, GhiChu;
    private Timestamp NgayChi;
    private double SoTienChi;

    public PhieuChiDTO() {
    }

    public PhieuChiDTO(int MaPC, int MaPN, int MaNV, String TenNguoiChi, String LyDoChi, String GhiChu, Timestamp NgayChi, double SoTienChi) {
        this.MaPC = MaPC;
        this.MaPN = MaPN;
        this.MaNV = MaNV;
        this.TenNguoiChi = TenNguoiChi;
        this.LyDoChi = LyDoChi;
        this.GhiChu = GhiChu;
        this.NgayChi = NgayChi;
        this.SoTienChi = SoTienChi;
    }

    public int getMaPC() {
        return MaPC;
    }

    public void setMaPC(int MaPC) {
        this.MaPC = MaPC;
    }

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int MaPN) {
        this.MaPN = MaPN;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNguoiChi() {
        return TenNguoiChi;
    }

    public void setTenNguoiChi(String TenNguoiChi) {
        this.TenNguoiChi = TenNguoiChi;
    }

    public String getLyDoChi() {
        return LyDoChi;
    }

    public void setLyDoChi(String LyDoChi) {
        this.LyDoChi = LyDoChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Timestamp getNgayChi() {
        return NgayChi;
    }

    public void setNgayChi(Timestamp NgayChi) {
        this.NgayChi = NgayChi;
    }

    public double getSoTienChi() {
        return SoTienChi;
    }

    public void setSoTienChi(double SoTienChi) {
        this.SoTienChi = SoTienChi;
    }
    
    
}
