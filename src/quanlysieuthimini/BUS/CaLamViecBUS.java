package quanlysieuthimini.BUS;

import quanlysieuthimini.DAO.CaLamViecDAO;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DTO.CaLamViecDTO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.GUI.Dialog.CaLamViecDialog;
import quanlysieuthimini.GUI.Dialog.KhuyenMaiDialog;
import quanlysieuthimini.GUI.Panel.CaLamViec;
import quanlysieuthimini.GUI.Panel.KhuyenMai;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CaLamViecBUS implements ActionListener, DocumentListener {
    public CaLamViec clv;
    private JTextField textField;
    public ArrayList<CaLamViecDTO> listClv = CaLamViecDAO.getInstance().getAll();
    
    public CaLamViecBUS() {
        
    }
    
    public CaLamViecBUS(CaLamViec clv) {
        this.clv = clv;
    }
    
    public CaLamViecBUS(JTextField textField, CaLamViec clv) {
        this.textField = textField;
        this.clv = clv;
    }
    public ArrayList<CaLamViecDTO> getAll() {
        return this.listClv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        switch (btn) {
            case "THÊM" -> {
                CaLamViecDialog clvthem = new CaLamViecDialog(this, clv.owner, true, "Thêm khuyến mãi", "create");
            }
            case "SỬA" -> {
                int index = clv.getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ca làm việc cần sửa");
                } else {
                    CaLamViecDialog clvsua = new CaLamViecDialog(this, clv.owner, true, "Sửa ca làm việc", "update", clv.getCaLamViec());
                }
            }
            case "XÓA" -> {
                if (clv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ca làm việc cần xóa");
                } else {
                    deleteClv(clv.getCaLamViec());
                }
            }
            case "CHI TIẾT" -> {
                if (clv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ca làm việc cần xem chi tiết");
                } else {
                    CaLamViecDialog clvctiet = new CaLamViecDialog(this, clv.owner, true, "Xem ca làm việc", "detail", clv.getCaLamViec());
                }
            }
//            case "NHẬP EXCEL" -> {
////                importExcel();
//            }
//            case "XUẤT EXCEL" -> {
//                String[] header = new String[]{"MãKM", "Tên khuyến mãi", "Điều kiện", "Phần trăm KM", "Ngày BĐ", "Ngày KT"};
//                exportExcel(listKm, header);
//            }
        }
        clv.loadDataTalbe(listClv);
    }
    public void deleteClv(CaLamViecDTO clv) {
        CaLamViecDAO.getInstance().delete(clv.getMaCa());
        listClv.removeIf(n -> (n.getMaCa() == clv.getMaCa()));
        loadTable();
    }

    public void insertClv(CaLamViecDTO clv) {
        listClv.add(clv);
    }

    public int getIndex() {
        return clv.getRow();
    }
    public void loadTable() {
        clv.loadDataTalbe(listClv);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            clv.loadDataTalbe(listClv);
        } else {
            ArrayList<CaLamViecDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            clv.loadDataTalbe(listClv);
        } else {
            ArrayList<CaLamViecDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    public void searchLoadTable(ArrayList<CaLamViecDTO> list) {
        clv.loadDataTalbe(list);
    }
    public ArrayList<CaLamViecDTO> search(String text) {
        String luachon = (String) clv.search.cbxChoose.getSelectedItem();
        text = text.toLowerCase();
        ArrayList<CaLamViecDTO> result = new ArrayList<>();
        switch (luachon) {
            case "Tên Ca" -> {
                for (CaLamViecDTO i : this.listClv) {
                    if (i.getTenCa().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }

            default ->
                    throw new AssertionError();
        }

        return result;
    }
}
