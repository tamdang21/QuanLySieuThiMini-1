/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class QuyenDTO {
    private int MaQuyen;
    private String TenQuyen;

    public QuyenDTO() {
    }

    public QuyenDTO(int MaQuyen, String TenQuyen) {
        this.MaQuyen = MaQuyen;
        this.TenQuyen = TenQuyen;
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String TenQuyen) {
        this.TenQuyen = TenQuyen;
    }

    
    
}
