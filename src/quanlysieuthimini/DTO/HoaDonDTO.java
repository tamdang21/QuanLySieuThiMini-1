/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.sql.Timestamp;

public class HoaDonDTO {
    private int MaHD, MaKH, MaKM, MaNV, TrangThai;
    private Timestamp NgayLap;
    private double TongTien;
    public HoaDonDTO() {
    }

    public HoaDonDTO(int MaHD, int MaKH, int MaKM, int MaNV, Timestamp NgayLap, double TongTien, int TrangThai) {
        this.MaHD = MaHD;
        this.MaKH = MaKH;
        this.MaKM = MaKM;
        this.MaNV = MaNV;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public Timestamp getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Timestamp NgayLap) {
        this.NgayLap = NgayLap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
    
}
