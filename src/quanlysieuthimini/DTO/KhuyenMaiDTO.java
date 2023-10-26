/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

import java.time.LocalDate;

public class KhuyenMaiDTO {
    private int MaKM;
    private String TenKM;
    private float PhanTramKM, DieuKienKM;
    private LocalDate NgayBatDau, NgayKetThuc;

    public KhuyenMaiDTO() {
    }

    public KhuyenMaiDTO(int MaKM, String TenKM, float DieuKienKM, float PhanTramKM, LocalDate NgayBatDau, LocalDate NgayKetThuc) {
        this.MaKM = MaKM;
        this.PhanTramKM = PhanTramKM;
        this.TenKM = TenKM;
        this.DieuKienKM = DieuKienKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }
    
    public String getTrangThai() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(this.NgayBatDau)) {
            return "Chưa bắt đầu";
        } else if (now.isAfter(this.NgayKetThuc)) {
            return "Đã kết thúc";
        } else {
            return "Đang diễn ra";
        }
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public LocalDate getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(LocalDate NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(LocalDate NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public float getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(float PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }

    public float getDieuKienKM() {
        return DieuKienKM;
    }

    public void setDieuKienKM(float DieuKienKM) {
        this.DieuKienKM = DieuKienKM;
    }
    
    
}
