/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.sql.Timestamp;

public class PhieuNhapDTO {
    private int MaPN, MaNV, MaNCC, TrangThai;
    private Timestamp NgayNhap;
    private double TongTien;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int MaPN, int MaNV, int MaNCC, Timestamp NgayNhap, double TongTien, int TrangThai) {
        this.MaPN = MaPN;
        this.MaNV = MaNV;
        this.MaNCC = MaNCC;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
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

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }

    public Timestamp getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Timestamp NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
