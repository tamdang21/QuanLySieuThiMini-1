/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class HinhThucThanhToanDTO {
    private int MaHTTT;
    private String TenHTTT;

    public HinhThucThanhToanDTO() {
    }

    public HinhThucThanhToanDTO(int MaHTTT, String TenHTTT) {
        this.MaHTTT = MaHTTT;
        this.TenHTTT = TenHTTT;
    }

    public int getMaHTTT() {
        return MaHTTT;
    }

    public void setMaHTTT(int MaHTTT) {
        this.MaHTTT = MaHTTT;
    }

    public String getTenHTTT() {
        return TenHTTT;
    }

    public void setTenHTTT(String TenHTTT) {
        this.TenHTTT = TenHTTT;
    }
    
}
