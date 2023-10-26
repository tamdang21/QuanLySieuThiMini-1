/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.sql.Timestamp;

public class ChiTietThanhToanDTO {
    private int MaHD, MaHTTT;
    private double SoTien;
    private Timestamp NgayThanhToan;

    public ChiTietThanhToanDTO() {
    }

    public ChiTietThanhToanDTO(int MaHD, int MaHTTT, double SoTien, Timestamp NgayThanhToan) {
        this.MaHD = MaHD;
        this.MaHTTT = MaHTTT;
        this.SoTien = SoTien;
        this.NgayThanhToan = NgayThanhToan;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaHTTT() {
        return MaHTTT;
    }

    public void setMaHTTT(int MaHTTT) {
        this.MaHTTT = MaHTTT;
    }

    public double getSoTien() {
        return SoTien;
    }

    public void setSoTien(double SoTien) {
        this.SoTien = SoTien;
    }

    public Timestamp getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Timestamp NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }
    
    
}
