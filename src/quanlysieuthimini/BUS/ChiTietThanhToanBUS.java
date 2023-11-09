/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.ChiTietThanhToanDAO;
import quanlysieuthimini.DTO.ChiTietThanhToanDTO;

/**
 *
 * @author domin
 */
public class ChiTietThanhToanBUS {
    private final ChiTietThanhToanDAO ctttDAO = new ChiTietThanhToanDAO();
    
    public boolean add(ArrayList<ChiTietThanhToanDTO> listCTHD){
        boolean check = ctttDAO.insert(listCTHD);
        return check;
    }
}
