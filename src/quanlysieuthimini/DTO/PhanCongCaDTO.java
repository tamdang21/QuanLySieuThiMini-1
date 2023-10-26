/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.DTO;

public class PhanCongCaDTO {
    int MaCa, MaNV;
    String Thu;

    public PhanCongCaDTO() {
    }

    public PhanCongCaDTO(int MaCa, int MaNV, String Thu) {
        this.MaCa = MaCa;
        this.MaNV = MaNV;
        this.Thu = Thu;
    }

    public int getMaCa() {
        return MaCa;
    }

    public void setMaCa(int MaCa) {
        this.MaCa = MaCa;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String Thu) {
        this.Thu = Thu;
    }
    
    
}
