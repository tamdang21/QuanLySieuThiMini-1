package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.CaLamViecBUS;
import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.DAO.CaLamViecDAO;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DTO.CaLamViecDTO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputDate;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaLamViecDialog extends JDialog {
    private CaLamViecBUS clv;
    private HeaderTitle titlePage;
    private JPanel main, bottom;
    private ButtonCustom btnAdd, btnEdit, btnExit;
    private JComboBox<String> name;
    private InputForm gioBD;
    private InputForm gioKT;
    private InputForm luongtheoca;

    private CaLamViecDTO caLamViec;
    DateTimeFormatter formatter ;
    public CaLamViecDialog(CaLamViecBUS clv, JFrame owner, boolean modal, String title, String type) {


        super(owner, title, modal);
        this.clv = clv;
        init(title, type);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public CaLamViecDialog(CaLamViecBUS clv, JFrame owner, boolean modal, String title, String type, CaLamViecDTO caLamViec) {

        super(owner, title, modal);
        this.clv = clv;
        this.caLamViec = caLamViec;
        init(title, type);
        formatter  = DateTimeFormatter.ofPattern("HH'h'mm");
        String formattedTimeBD = caLamViec.getGioBatDau().format(formatter);
        String formattedTimeKT = caLamViec.getGioKetThuc().format(formatter);

        name.setSelectedItem(caLamViec.getTenCa());
        System.out.println(formattedTimeBD);
        gioBD.setText(formattedTimeBD);
        gioKT.setText(formattedTimeKT);
        luongtheoca.setText(String.valueOf(caLamViec.getLuongTheoCa()));


//        name.setText(nhanVien.getTenNV());
//        sdt.setText(nhanVien.getSDT());
//        email.setText(nhanVien.getEmail());
//        if (nhanVien.getGioiTinh()== 1) {
//            male.setSelected(true);
//        } else {
//            female.setSelected(true);
//        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void init(String title, String type) {
        this.setSize(new Dimension(450, 590));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        String[] tenca = {"Sáng", "Chiều", "Tối", "Khuya"};
        
        name = new JComboBox<>(tenca);
        gioBD = new InputForm("Giờ bắt đầu");
        gioKT = new InputForm("Giờ kết thúc");
        luongtheoca = new InputForm("Lương theo ca");

        main.add(name);
        main.add(gioBD);
        main.add(gioKT);
        main.add(luongtheoca);

        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm ca làm việc", "success", 14);
        btnEdit = new ButtonCustom("Lưu thông tin", "success", 14);
        btnExit = new ButtonCustom("Hủy bỏ", "danger", 14);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // done
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ValidationInput()) {
//                        if(checkEmail(email.getText())){
                        try {
//                                int txt_gender = -1;
//                                if (male.isSelected()) {
//                                    System.out.println("Nam");
//                                    txt_gender = 1;
//                                } else if (female.isSelected()) {
//                                    System.out.println("Nữ");
//                                    txt_gender = 0;
//                                }
                            int maclv = CaLamViecDAO.getInstance().getAutoIncrement();
                            String txtName = name.getSelectedItem().toString();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
                            String txtGioBD = gioBD.getText();
                            String txtGioKT = gioKT.getText();
                            if (txtGioBD.length() == 4) {
                                txtGioBD = "0" + txtGioBD; // Thêm "0" vào trước giờ
                            }
                            if (txtGioKT.length() == 4) {
                                txtGioKT = "0" + txtGioKT; // Thêm "0" vào trước giờ
                            }
                            LocalTime timeBD = LocalTime.parse(txtGioBD,formatter);
                            LocalTime timeKT = LocalTime.parse(txtGioKT,formatter);
                            Double luong = Double.parseDouble(luongtheoca.getText());

                            CaLamViecDTO cLV = new CaLamViecDTO(maclv, txtName, timeBD, timeKT, luong);
                            CaLamViecDAO.getInstance().insert(cLV);
                            clv.insertClv(cLV);
                            clv.loadTable();
                            dispose();
                        } catch (Exception ex) {
                            Logger.getLogger(CaLamViecDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(KhuyenMaiDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // chưa sửa
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ValidationInput()) {
                        try {
//                            int txt_gender = -1;
//                            if (male.isSelected()) {
//                                System.out.println("Nam");
//                                txt_gender = 1;
//                            } else if (female.isSelected()) {
//                                System.out.println("Nữ");
//                                txt_gender = 0;
//                            }
                            int maclv = CaLamViecDAO.getInstance().getAutoIncrement();
                            String txtName = name.getSelectedItem().toString();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
                            String txtGioBD = gioBD.getText();
                            String txtGioKT = gioKT.getText();
                            if (txtGioBD.length() == 4) {
                                txtGioBD = "0" + txtGioBD; // Thêm "0" vào trước giờ
                            }
                            if (txtGioKT.length() == 4) {
                                txtGioKT = "0" + txtGioKT; // Thêm "0" vào trước giờ
                            }
                            LocalTime timeBD = LocalTime.parse(txtGioBD,formatter);
                            LocalTime timeKT = LocalTime.parse(txtGioKT,formatter);
                            Double luong = Double.parseDouble(luongtheoca.getText());

                            CaLamViecDTO cLV = new CaLamViecDTO(maclv, txtName, timeBD, timeKT, luong);
                            CaLamViecDAO.getInstance().update(caLamViec);
                            System.out.println("Index:" +clv.getIndex());
                            clv.listClv.set(clv.getIndex(), cLV);
                            clv.loadTable();
                            dispose();
                        } catch (Exception ex) {
                            Logger.getLogger(CaLamViecDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(CaLamViecDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        switch (type) {
            case "create" ->
                    bottom.add(btnAdd);
            case "update" ->
                    bottom.add(btnEdit);
            case "detail" -> {
                name.setEditable(false);
                gioBD.setDisable();
                gioKT.setDisable();
                luongtheoca.setDisable();
            }
            default ->
                    throw new AssertionError();
        }

        bottom.add(btnExit);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

    }
    boolean ValidationInput() throws ParseException {
        String timePattern = "^(0[0-9]|1[0-9]|2[0-3])h(0[0-9]|[1-5][0-9])$";

        String txtGioBD = gioBD.getText();
        String txtGioKT = gioKT.getText();
        if (txtGioBD.length() == 4) {
            txtGioBD = "0" + txtGioBD; // Thêm "0" vào trước giờ
        }
        if (txtGioKT.length() == 4) {
            txtGioKT = "0" + txtGioKT; // Thêm "0" vào trước giờ
        }
        Pattern pattern = Pattern.compile(timePattern);
        Matcher matcher = pattern.matcher(txtGioBD);
        Matcher matcher2 = pattern.matcher(txtGioKT);
         if (Validation.isEmpty(txtGioBD) ) {
            JOptionPane.showMessageDialog(this, "Giờ bắt dầu không được để rỗng ", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!matcher.matches()) {
             JOptionPane.showMessageDialog(this, "Giờ bắt dầu  không đúng định dạng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
             return false;
         } else if (Validation.isEmpty(txtGioKT)) {
            JOptionPane.showMessageDialog(this, "Giờ kết thúc không được để rỗng hoặc không đúng định dạng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!matcher2.matches()) {
             JOptionPane.showMessageDialog(this, "Giờ kết thúc  không đúng định dạng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
             return false;
         }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
        LocalTime timeBD = LocalTime.parse(txtGioBD,formatter);
        LocalTime timeKT = LocalTime.parse(txtGioKT,formatter);
//         if (timeBD.isAfter(timeKT) || timeBD.equals(timeKT)) {
//             JOptionPane.showMessageDialog(this, "Giờ bắt đầu phải sớm hơn giờ kết thúc", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
//             return false;
//         }
         if (Validation.isEmpty(luongtheoca.getText())) {
            JOptionPane.showMessageDialog(this, "Lương không được để rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}
