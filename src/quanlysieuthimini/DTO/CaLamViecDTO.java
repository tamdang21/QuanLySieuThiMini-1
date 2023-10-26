/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class CaLamViecDTO {
    private int MaCa;
    private String TenCa;
    private LocalTime GioBatDau, GioKetThuc;
    private double LuongTheoCa;

    public CaLamViecDTO() {
    }

    public CaLamViecDTO(int MaCa, String TenCa, LocalTime GioBatDau, LocalTime GioKetThuc, double LuongTheoCa) {
        this.MaCa = MaCa;
        this.TenCa = TenCa;
        this.GioBatDau = GioBatDau;
        this.GioKetThuc = GioKetThuc;
        this.LuongTheoCa = LuongTheoCa;
    }

    public String getTenCa() {
        return TenCa;
    }

    public void setTenCa(String TenCa) {
        this.TenCa = TenCa;
    }

    public int getMaCa() {
        return MaCa;
    }

    public void setMaCa(int MaCa) {
        this.MaCa = MaCa;
    }

    public LocalTime getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(LocalTime GioBatDau) {
        this.GioBatDau = GioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(LocalTime GioKetThuc) {
        this.GioKetThuc = GioKetThuc;
    }

    public double getLuongTheoCa() {
        return LuongTheoCa;
    }

    public void setLuongTheoCa(double LuongTheoCa) {
        this.LuongTheoCa = LuongTheoCa;
    }
    
    
}
