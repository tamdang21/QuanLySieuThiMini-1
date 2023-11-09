/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.DAO.KhachHangThanThietDAO;
import quanlysieuthimini.DTO.KhachHangThanThietDTO;
import quanlysieuthimini.GUI.Panel.KhachHangThanThiet;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Component.NumericDocumentFilter;
import quanlysieuthimini.helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

public class KhachHangThanThietDialog extends JDialog implements ActionListener {

    private KhachHangThanThiet panelKHTT;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenKH;
    private InputForm diachi;
    private InputForm sodienthoai;
    private InputForm diemtichluy;
    private InputForm chietkhau;
    private KhachHangThanThietDTO khttDTO;

    public KhachHangThanThietDialog(KhachHangThanThiet panelKHTT, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.panelKHTT = panelKHTT;
        initComponents(title, type);
    }

    public KhachHangThanThietDialog(KhachHangThanThiet panelKHTT, JFrame owner, String title, boolean modal, String type, KhachHangThanThietDTO khttDTO) {
        super(owner, title, modal);
        this.panelKHTT = panelKHTT;
        this.khttDTO = khttDTO;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(900, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 3, 20, 0));
        pnmain.setBackground(Color.white);
        tenKH = new InputForm("Tên khách hàng");
        diachi = new InputForm("Địa chỉ");
        diemtichluy = new InputForm("Điểm tích lũy");
        sodienthoai = new InputForm("Số điện thoại");
        chietkhau = new InputForm("Chiết khấu");
        diemtichluy.setEditable(false);
        chietkhau.setEditable(false);
        PlainDocument phonex = (PlainDocument)sodienthoai.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));

        pnmain.add(tenKH);
        pnmain.add(diemtichluy);
        pnmain.add(chietkhau);
        pnmain.add(diachi);
        pnmain.add(sodienthoai);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm khách hàng", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuyBo.addActionListener(this);

        switch (type) {
            case "create" -> {
                diemtichluy.setText("1");
                chietkhau.setText("0.005");
                pnbottom.add(btnThem);
            }
            case "update" -> {
                pnbottom.add(btnCapNhat);
                initInfo();
            }
            case "view" -> {
                initInfo();
                initView();
            }
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initInfo() {
        tenKH.setText(khttDTO.getTenKH());
        diachi.setText(khttDTO.getDiaChi());
        diemtichluy.setText(String.valueOf(khttDTO.getDiemTichLuy()));
        sodienthoai.setText(khttDTO.getSDT());
        chietkhau.setText(String.valueOf(khttDTO.getChietKhauTheoDiem()));
    }

    public void initView() {
        tenKH.setEditable(false);
        diachi.setEditable(false);
        sodienthoai.setEditable(false);
        diemtichluy.setEditable(false);
        chietkhau.setEditable(false);

    }
    public boolean Validation(){
         if (Validation.isEmpty(tenKH.getText())) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         else  if (Validation.isEmpty(diachi.getText())) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         else if (Validation.isEmpty(sodienthoai.getText()) || !Validation.isNumber(sodienthoai.getText()) && sodienthoai.getText().length()!=10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
          return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem && Validation()) {
            int maKH = KhachHangThanThietDAO.getInstance().getAutoIncrement();
            panelKHTT.khttBUS.add(new KhachHangThanThietDTO(maKH, tenKH.getText(), diachi.getText(), sodienthoai.getText(), Integer.parseInt(diemtichluy.getText()), Double.valueOf(chietkhau.getText())));
            panelKHTT.loadDataTable(panelKHTT.listKHTT);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && Validation()) {
            panelKHTT.khttBUS.update(new KhachHangThanThietDTO(khttDTO.getMaKH(), tenKH.getText(), diachi.getText(), sodienthoai.getText(), Integer.parseInt(diemtichluy.getText()), Double.valueOf(chietkhau.getText())));
            panelKHTT.loadDataTable(panelKHTT.listKHTT);
            dispose();
        }
    }
}
