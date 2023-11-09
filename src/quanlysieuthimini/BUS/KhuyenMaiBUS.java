package quanlysieuthimini.BUS;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DAO.NhanVienDAO;
import quanlysieuthimini.DAO.TaiKhoanDAO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.DTO.NhanVienDTO;
import quanlysieuthimini.GUI.Dialog.KhuyenMaiDialog;
import quanlysieuthimini.GUI.Dialog.NhanVienDialog;
import quanlysieuthimini.GUI.Panel.KhuyenMai;
import quanlysieuthimini.GUI.Panel.NhanVien;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class KhuyenMaiBUS implements ActionListener, DocumentListener {
    public KhuyenMai km;
    private JTextField textField;
    public ArrayList<KhuyenMaiDTO> listKm = KhuyenMaiDAO.getInstance().getAll();
    public KhuyenMaiDAO khuyenMaiDAO = KhuyenMaiDAO.getInstance();
    
    public KhuyenMaiBUS() {
        
    }
    
    public KhuyenMaiBUS(KhuyenMai km) {
        this.km = km;
    }
    public KhuyenMaiBUS(JTextField textField, KhuyenMai km) {
        this.textField = textField;
        this.km = km;
    }
    public ArrayList<KhuyenMaiDTO> getAll() {
        return this.listKm;
    }

    public KhuyenMaiDTO getByIndex(int index) {
        return this.listKm.get(index);
    }
    
    public ArrayList<Float> getAllDKKM() {
        ArrayList<Float> listDKKM = new ArrayList<>();
        for(KhuyenMaiDTO km : listKm) {
            listDKKM.add(km.getDieuKienKM());
        }
        return listDKKM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        switch (btn) {
            case "THÊM" -> {
                KhuyenMaiDialog kmthem = new KhuyenMaiDialog(this, km.owner, true, "Thêm khuyến mãi", "create");
            }
            case "SỬA" -> {
                int index = km.getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi cần sửa");
                } else {
                    KhuyenMaiDialog kmsua = new KhuyenMaiDialog(this, km.owner, true, "Sửa khuyến mãi", "update", km.getKhuyenMai());
                }
            }
            case "XÓA" -> {
                if (km.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi cần xóa");
                } else {
                    deleteKm(km.getKhuyenMai());
                }
            }
            case "CHI TIẾT" -> {
                if (km.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi cần xem chi tiết");
                } else {
                    KhuyenMaiDialog kmctiet = new KhuyenMaiDialog(this, km.owner, true, "Xem khuyến mãi", "detail", km.getKhuyenMai());
                }
            }
            case "NHẬP EXCEL" -> {
//                importExcel();
            }
            case "XUẤT EXCEL" -> {
                String[] header = new String[]{"MãKM", "Tên khuyến mãi", "Điều kiện", "Phần trăm KM", "Ngày BĐ", "Ngày KT"};
                exportExcel(listKm, header);
            }
        }
        km.loadDataTalbe(listKm);
    }
    public void insertKm(KhuyenMaiDTO km) {
        listKm.add(km);
    }
    public void loadTable() {
        km.loadDataTalbe(listKm);
    }
    public int getIndex() {
        return km.getRow();
    }
    public void searchLoadTable(ArrayList<KhuyenMaiDTO> list) {
        km.loadDataTalbe(list);
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            km.loadDataTalbe(listKm);
        } else {
            ArrayList<KhuyenMaiDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            km.loadDataTalbe(listKm);
        } else {
            ArrayList<KhuyenMaiDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    public ArrayList<KhuyenMaiDTO> search(String text) {
        String luachon = (String) km.search.cbxChoose.getSelectedItem();
        text = text.toLowerCase();
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả" -> {
                for (KhuyenMaiDTO i : this.listKm) {
                    if (i.getTenKM().toLowerCase().contains(text) || (""+i.getPhanTramKM()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên KM" -> {
                for (KhuyenMaiDTO i : this.listKm) {
                    if (i.getTenKM().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Phần trăm KM" -> {
                for (KhuyenMaiDTO i : this.listKm) {
                    if ((""+i.getPhanTramKM()).toLowerCase().contains(text)
                    ) {
                        result.add(i);
                    }
                }
            }
            default ->
                    throw new AssertionError();
        }

        return result;
    }
    public void deleteKm(KhuyenMaiDTO km) {
        KhuyenMaiDAO.getInstance().delete(km.getMaKM());
        listKm.removeIf(n -> (n.getMaKM() == km.getMaKM()));
        loadTable();
    }
    
    public String getTenKhuyenMai(int makm) {
        String name = "";
        for (KhuyenMaiDTO khachHangDTO : listKm) {
            if (khachHangDTO.getMaKM() == makm) {
                name = khachHangDTO.getTenKM();
            }
        }
        return name;
    }

    public String[] getArrTenKhuyenMai() {
        int size = listKm.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listKm.get(i).getTenKM();
        }
        return result;
    }

    public KhuyenMaiDTO getById(int makm) {
        return khuyenMaiDAO.getById(makm);
    }
    
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    private static void writeHeader(String[] list, Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        Cell cell;
        for (int i = 0; i < list.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list[i]);
            sheet.autoSizeColumn(i);
        }
    }
    private static void writeKhuyenMai(KhuyenMaiDTO km, Row row) {
        CellStyle cellStyleFormatNumber = null;
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        Cell cell = row.createCell(0);
        cell.setCellValue(km.getMaKM());

        cell = row.createCell(1);
        cell.setCellValue(km.getTenKM());

        cell = row.createCell(2);
        cell.setCellValue(km.getDieuKienKM());

        cell = row.createCell(3);
        cell.setCellValue(km.getPhanTramKM());

        cell = row.createCell(4);
        cell.setCellValue("" + km.getNgayBatDau());

        cell = row.createCell(5);
        cell.setCellValue("" + km.getNgayKetThuc());
    }
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void exportExcel(ArrayList<KhuyenMaiDTO> list, String[] header) {
        try {
            if (!list.isEmpty()) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showSaveDialog(km.owner);
                File saveFile = jFileChooser.getSelectedFile();
                if (saveFile != null) {
                    saveFile = new File(saveFile.toString() + ".xlsx");
                    Workbook wb = new XSSFWorkbook();
                    Sheet sheet = wb.createSheet("Khuyến mãi");

                    writeHeader(header, sheet, 0);
                    int rowIndex = 1;
                    for (KhuyenMaiDTO km : list) {
                        Row row = sheet.createRow(rowIndex++);
                        writeKhuyenMai(km, row);
                    }
                    FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                    wb.write(out);
                    wb.close();
                    out.close();
                    openFile(saveFile.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
