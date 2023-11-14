package quanlysieuthimini.BUS;

import quanlysieuthimini.DAO.CaLamViecDAO;
import quanlysieuthimini.DTO.CaLamViecDTO;
import quanlysieuthimini.GUI.Dialog.CaLamViecDialog;
import quanlysieuthimini.GUI.Panel.CaLamViec;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import quanlysieuthimini.DAO.PhanCongCaDAO;
import quanlysieuthimini.DTO.PhanCongCaDTO;
import quanlysieuthimini.GUI.Panel.PhanCongCa;

public class CaLamViecBUS implements ActionListener, DocumentListener {
    public CaLamViec clv;
    private JTextField textField;
    public ArrayList<CaLamViecDTO> listClv = CaLamViecDAO.getInstance().getAll();
    public PhanCongCaDAO phancongcaDAO = PhanCongCaDAO.getInstance();
    public NhanVienBUS nvBUS = new NhanVienBUS();
    
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
    
    public ArrayList<PhanCongCaDTO> getAllNotId() {
        return phancongcaDAO.getAllNotId();
    }
    
    public CaLamViecDTO getById(int maca) {
        return CaLamViecDAO.getInstance().getById(maca);
    }
    
    public ArrayList<PhanCongCaDTO> selectPCC(int maca) {
        return phancongcaDAO.getAll(maca);
    }
    
    public String getTenCa(int maca) {
        String name = "";
        for (CaLamViecDTO clv : listClv) {
            if (clv.getMaCa()== maca) {
                name = clv.getTenCa();
            }
        }
        return name;
    }
    
    public void insertPCC(PhanCongCaDTO pcc) {
        ArrayList<PhanCongCaDTO> arrPCC = new ArrayList<>();
        arrPCC.add(pcc);
        phancongcaDAO.insert(arrPCC);
    }
    
    public String[] getArrTenCa() {
        int size = listClv.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listClv.get(i).getTenCa();
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        switch (btn) {
            case "THÊM" -> {
                CaLamViecDialog clvthem = new CaLamViecDialog(this, clv.owner, true, "Thêm ca mới", "create");

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
            case "PHÂN CÔNG" -> {
                if (clv.getTaiKhoan().getMaQuyen() != 1) {
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                } else {
                    PhanCongCa phancong = new PhanCongCa(clv.getMain(), clv.getTaiKhoan());
                    clv.getMain().setPanel(phancong);
                }
                
            }
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
    
    public CaLamViecDTO getByIndex(int index) {
        return this.listClv.get(index);
    }
    
    public ArrayList<PhanCongCaDTO> fillerPCC(int type, String input, int maca, int manv, String thu) {
        ArrayList<PhanCongCaDTO> result = new ArrayList<>();
        for (PhanCongCaDTO pcc : getAllNotId()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (getTenCa(pcc.getMaCa()).toLowerCase().contains(input)
                        || nvBUS.getNameById(pcc.getMaNV()).toLowerCase().contains(input)) {
                        
                        match = true;
                    }
                }
                case 1 -> {
                    if (getTenCa(pcc.getMaCa()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nvBUS.getNameById(pcc.getMaNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match && (manv == 0 || pcc.getMaNV() == manv) 
                      && (maca == 0 || pcc.getMaCa()== maca)) {
                
                result.add(pcc);
            }
        }

        return result;
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
