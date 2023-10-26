/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class HangSanXuatDTO {
    int MaHang;
    String TenHang, TruSo;

    public HangSanXuatDTO() {
    }

    public HangSanXuatDTO(int MaHang, String TenHang, String TruSo) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.TruSo = TruSo;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public int getMaHang() {
        return MaHang;
    }

    public void setMaHang(int MaHang) {
        this.MaHang = MaHang;
    }

    public String getTruSo() {
        return TruSo;
    }

    public void setTruSo(String TruSo) {
        this.TruSo = TruSo;
    }
}
