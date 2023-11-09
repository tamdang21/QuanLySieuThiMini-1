package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.KhuyenMaiBUS;
import quanlysieuthimini.BUS.NhanVienBUS;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DAO.NhanVienDAO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.DTO.NhanVienDTO;
import quanlysieuthimini.GUI.Component.*;
import quanlysieuthimini.helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiDialog extends JDialog {
    private KhuyenMaiBUS km;
    private HeaderTitle titlePage;
    private JPanel main, bottom;
    private ButtonCustom btnAdd, btnEdit, btnExit;
    private InputForm name;
    private InputForm dieukien;
    private InputForm phanTramKm;
    private InputDate ngayBD;
    private InputDate ngayKT;

    private KhuyenMaiDTO khuyenMai;
    public KhuyenMaiDialog(KhuyenMaiBUS km, JFrame owner, boolean modal, String title, String type) {
        super(owner, title, modal);
        this.km = km;
        init(title, type);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public KhuyenMaiDialog(KhuyenMaiBUS km, JFrame owner, boolean modal, String title, String type, KhuyenMaiDTO khuyenMai) {
        super(owner, title, modal);
        this.km = km;
        this.khuyenMai = khuyenMai;
        init(title, type);
        name.setText(khuyenMai.getTenKM());
        dieukien.setText(String.valueOf(khuyenMai.getDieuKienKM()));
        phanTramKm.setText(String.valueOf(khuyenMai.getPhanTramKM()));
        ngayBD.setDate(Date.from(khuyenMai.getNgayBatDau().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ngayKT.setDate(Date.from(khuyenMai.getNgayKetThuc().atStartOfDay(ZoneId.systemDefault()).toInstant()));

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
        name = new InputForm("Tên Khuyến Mãi");
//        sdt = new InputForm("Số điện thoại");
//        PlainDocument phonex = (PlainDocument) sdt.getTxtForm().getDocument();
//        phonex.setDocumentFilter((new NumericDocumentFilter()));
        dieukien = new InputForm("Điều kiện");
        phanTramKm = new InputForm("Phần trăm khuyến mãi");
//        JPanel jpanelG = new JPanel(new GridLayout(2, 1, 0, 2));
//        jpanelG.setBackground(Color.white);
//        jpanelG.setBorder(new EmptyBorder(10, 10, 10, 10));
//        JPanel jgender = new JPanel(new GridLayout(1, 2));
//        jgender.setSize(new Dimension(500, 80));
//        jgender.setBackground(Color.white);
//        jgender.add(male);
//        jgender.add(female);
//        JLabel labelGender = new JLabel("Giới tính");
//        jpanelG.add(labelGender);
//        jpanelG.add(jgender);
        // batdau
//        JPanel jpanelBD = new JPanel();
//        jpanelBD.setBorder(new EmptyBorder(10, 10, 10, 10));
//        JLabel lbBd = new JLabel("Ngày bắt đầu");
//        lbBd.setSize(new Dimension(100, 100));
//        jpanelBD.setSize(new Dimension(500, 100));
//        jpanelBD.setLayout(new FlowLayout(FlowLayout.LEFT));
//        jpanelBD.setBackground(Color.white);
        ngayBD = new InputDate("Ngày bắt đầu");
//        ngayBD.setSize(new Dimension(100, 100));
//        jpanelBD.add(lbBd);
//        jpanelBD.add(ngayBD);
        // ketthuc
//        JPanel jpanelKT = new JPanel();
//        jpanelKT.setBorder(new EmptyBorder(10, 10, 10, 10));
//        JLabel lbKt = new JLabel("Ngày kết thúc");
//        lbKt.setSize(new Dimension(100, 100));
//        jpanelKT.setSize(new Dimension(500, 100));
//        jpanelKT.setLayout(new FlowLayout(FlowLayout.LEFT));
//        jpanelKT.setBackground(Color.white);
        ngayKT = new InputDate("Ngày kết thúc");
//        ngayKT.setSize(new Dimension(100, 100));

//        jpanelBD.add(lbBd);
//        jpanelBD.add(ngayBD);
//
//        jpanelKT.add(lbKt);
//        jpanelKT.add(ngayKT);

        main.add(name);
        main.add(dieukien);
        main.add(phanTramKm);
        main.add(ngayBD);
        main.add(ngayKT);

        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm khuyến mãi", "success", 14);
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
                                int makm = KhuyenMaiDAO.getInstance().getAutoIncrement();
                                String txtName = name.getText();
                                float vlDieuKien = Float.parseFloat(dieukien.getText());
                                float vlPhanTram = Float.parseFloat(phanTramKm.getText());
                                Date bd = ngayBD.getDate();
                                java.sql.Date sqlDatebd = new java.sql.Date(bd.getTime());

                                Date kt = ngayKT.getDate();
                                java.sql.Date sqlDatekt = new java.sql.Date(kt.getTime());
//                                String txtSdt = sdt.getText();
//                                String txtEmail = email.getText();
//                                Date birthDay = jcBd.getDate();
//                                java.sql.Date sqlDate = new java.sql.Date(birthDay.getTime());

                                KhuyenMaiDTO kM = new KhuyenMaiDTO(makm, txtName, vlDieuKien, vlPhanTram, sqlDatebd.toLocalDate(), sqlDatekt.toLocalDate(), 1);
                                KhuyenMaiDAO.getInstance().insert(kM);
                                km.insertKm(kM);
                                km.loadTable();
                                dispose();
                            } catch (ParseException ex) {
                                Logger.getLogger(KhuyenMaiDialog.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(KhuyenMaiDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //
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
                            int makm = KhuyenMaiDAO.getInstance().getAutoIncrement();
//                            String txtName = name.getText();
//                            String txtSdt = sdt.getText();
//                            String txtEmail = email.getText();
//                            Date birthDay = jcBd.getDate();
//                            java.sql.Date sqlDate = new java.sql.Date(birthDay.getTime());

                            String txtName = name.getText();
                            float vlDieuKien = Float.parseFloat(dieukien.getText());
                            float vlPhanTram = Float.parseFloat(phanTramKm.getText());
                            Date bd = ngayBD.getDate();
                            java.sql.Date sqlDatebd = new java.sql.Date(bd.getTime());
                            Date kt = ngayKT.getDate();
                            java.sql.Date sqlDatekt = new java.sql.Date(kt.getTime());
                            KhuyenMaiDTO kM = new KhuyenMaiDTO(khuyenMai.getMaKM(), txtName, vlDieuKien, vlPhanTram, sqlDatebd.toLocalDate(), sqlDatekt.toLocalDate(), 1);
                            KhuyenMaiDAO.getInstance().update(kM);
                            System.out.println("Index:" +km.getIndex());
                            km.listKm.set(km.getIndex(), kM);
                            km.loadTable();
                            dispose();
                        } catch (ParseException ex) {
                            Logger.getLogger(NhanVienDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        switch (type) {
            case "create" ->
                    bottom.add(btnAdd);
            case "update" ->
                    bottom.add(btnEdit);
            case "detail" -> {
                name.setDisable();
                dieukien.setDisable();
                phanTramKm.setDisable();
                ngayBD.setDisable();
                ngayKT.setDisable();
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
        if (Validation.isEmpty(name.getText())) {
            JOptionPane.showMessageDialog(this, "Tên khuyến mãi không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (Validation.isEmpty(dieukien.getText())) {
            JOptionPane.showMessageDialog(this, "Điều kiện không được để rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (Float.parseFloat(dieukien.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Điều kiện không hợp lệ", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (Validation.isEmpty(phanTramKm.getText())) {
            JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi không được để rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if (Float.parseFloat(phanTramKm.getText()) > 1.0 || Float.parseFloat(phanTramKm.getText()) < 0.0) {
            JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi không hợp lệ", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if(ngayBD.getDate()==null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu!");
            return false;
        } else if(ngayKT.getDate()==null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc!");
            return false;
        } else if(ngayBD.getDate().compareTo(ngayKT.getDate()) > 0){
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải trùng hoặc sau ngày bắt đầu");
            return false;
        }

        return true;
    }
}
