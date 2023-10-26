/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.BUS;

import java.util.ArrayList;
import quanlysieuthimini.DAO.ChiTietQuyenDAO;
import quanlysieuthimini.DAO.QuyenDAO;
import quanlysieuthimini.DTO.ChiTietQuyenDTO;
import quanlysieuthimini.DTO.QuyenDTO;

public class QuyenBUS {

    private final QuyenDAO quyenDAO = new QuyenDAO();
    private final ChiTietQuyenDAO chitietquyenDAO = new ChiTietQuyenDAO();
    private final ArrayList<QuyenDTO> listQuyen;

    public QuyenBUS() {
        this.listQuyen = quyenDAO.getAll();
    }

    public ArrayList<QuyenDTO> getAll() {
        return this.listQuyen;
    }

    public QuyenDTO getByIndex(int index) {
        return this.listQuyen.get(index);
    }

    public boolean add(String nqdto, ArrayList<ChiTietQuyenDTO> ctquyen) {
        QuyenDTO quyen = new QuyenDTO(quyenDAO.getAutoIncrement(), nqdto);
        boolean check = quyenDAO.insert(quyen);
        if (check) {
            this.listQuyen.add(quyen);
            this.addChiTietQuyen(ctquyen);
        }
        return check;
    }

    public boolean update(QuyenDTO quyen, ArrayList<ChiTietQuyenDTO> chitietquyen, int index) {
        boolean check = quyenDAO.update(quyen);
        if (check) {
            this.removeChiTietQuyen(quyen.getMaQuyen());
            this.addChiTietQuyen(chitietquyen);
            this.listQuyen.set(index, quyen);
        }
        return check;
    }

    public boolean delete(QuyenDTO quyen) {
        boolean check = quyenDAO.delete(quyen.getMaQuyen());
        if (check) {
            this.listQuyen.remove(quyen);
        }
        return check;
    }

    public ArrayList<ChiTietQuyenDTO> getChiTietQuyen(int maquyen) {
        return chitietquyenDAO.getAll(maquyen);
    }

    public boolean addChiTietQuyen(ArrayList<ChiTietQuyenDTO> listctquyen) {
        boolean check = chitietquyenDAO.insert(listctquyen);
        return check;
    }

    public boolean removeChiTietQuyen(int maquyen) {
        boolean check = chitietquyenDAO.delete(maquyen);
        return check;
    }

    public boolean checkPermisson(int maquyen, String chucnang, String hanhdong) {
        ArrayList<ChiTietQuyenDTO> ctquyen = this.getChiTietQuyen(maquyen);
        boolean check = false;
        int i = 0;
        while (i < ctquyen.size() && check==false) {
            if(ctquyen.get(i).getMaCN().equals(chucnang) && ctquyen.get(i).getHanhDong().equals(hanhdong)) {
                check = true;
            } else {
                i++;
            }
        }
        return check;
    }
    
    public ArrayList<QuyenDTO> search(String text) {
        ArrayList<QuyenDTO> result = new ArrayList<>();
        for(QuyenDTO i : this.listQuyen) {
            if(Integer.toString(i.getMaQuyen()).contains(text) || i.getTenQuyen().toLowerCase().contains(text.toLowerCase())) {
                result.add(i);
            }
        }
        return result;
    }
}
