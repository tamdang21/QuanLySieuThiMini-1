/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class ChiTietQuyenDTO {
    private int MaQuyen;
    private String MaCN;
    private String HanhDong;

    public ChiTietQuyenDTO() {
    }

    public ChiTietQuyenDTO(int MaQuyen, String MaCN, String HanhDong) {
        this.MaQuyen = MaQuyen;
        this.MaCN = MaCN;
        this.HanhDong = HanhDong;
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getMaCN() {
        return MaCN;
    }

    public void setMaCN(String MaCN) {
        this.MaCN = MaCN;
    }

    public String getHanhDong() {
        return HanhDong;
    }

    public void setHanhDong(String HanhDong) {
        this.HanhDong = HanhDong;
    }
    
}
