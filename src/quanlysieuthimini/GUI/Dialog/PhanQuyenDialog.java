/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.QuyenBUS;
import quanlysieuthimini.DAO.ChiTietQuyenDAO;
import quanlysieuthimini.DAO.ChucNangDAO;
import quanlysieuthimini.DAO.QuyenDAO;
import quanlysieuthimini.DTO.ChiTietQuyenDTO;
import quanlysieuthimini.DTO.ChucNangDTO;
import quanlysieuthimini.DTO.QuyenDTO;
import quanlysieuthimini.GUI.Panel.PhanQuyen;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public final class PhanQuyenDialog extends JDialog implements ActionListener {

    private JLabel lblTennhomquyen;
    private JTextField txtTennhomquyen;
    private JPanel jpTop, jpLeft, jpCen, jpBottom;
    private JCheckBox[][] listCheckBox;
    private ButtonCustom btnAddQuyen, btnUpdateQuyen,btnHuybo;
    private PhanQuyen jpPhanQuyen;
    private int sizeDmCn, sizeHanhdong;
    private ArrayList<ChucNangDTO> dmcn;
    String[] mahanhdong = {"view", "create", "update", "delete"};
    private ArrayList<ChiTietQuyenDTO> ctQuyen;
    private QuyenDTO nhomquyenDTO;
    private QuyenBUS nhomquyenBUS;
    int index;

    public void initComponents(String type) {
        dmcn = ChucNangDAO.getInstance().getAll();

        String[] hanhdong = {"Xem", "Tạo mới", "Cập nhật", "Xoá"};

        this.setSize(new Dimension(1000, 580));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        // Hiển thị tên nhóm quyền
        jpTop = new JPanel(new BorderLayout(20, 10));
        jpTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpTop.setBackground(Color.WHITE);
        lblTennhomquyen = new JLabel("Tên nhóm quyền");
        txtTennhomquyen = new JTextField();
        txtTennhomquyen.setPreferredSize(new Dimension(150, 35));
        jpTop.add(lblTennhomquyen, BorderLayout.WEST);
        jpTop.add(txtTennhomquyen, BorderLayout.CENTER);

        // Hiển thị danh mục chức năng
        jpLeft = new JPanel(new GridLayout(dmcn.size() + 1, 1));
        jpLeft.setBackground(Color.WHITE);
        jpLeft.setBorder(new EmptyBorder(0, 20, 0, 14));
        JLabel dmcnl = new JLabel("Danh mục chức năng ");
        dmcnl.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 15));
        jpLeft.add(dmcnl);
        for (ChucNangDTO i : dmcn) {
            JLabel lblTenchucnang = new JLabel(i.getTenCN());
            jpLeft.add(lblTenchucnang);
        }
        // Hiển thị chức năng CRUD        
        sizeDmCn = dmcn.size();
        sizeHanhdong = hanhdong.length;
        jpCen = new JPanel(new GridLayout(sizeDmCn + 1, sizeHanhdong));
        jpCen.setBackground(Color.WHITE);
        listCheckBox = new JCheckBox[sizeDmCn][sizeHanhdong];
        for (int i = 0; i < sizeHanhdong; i++) {
            JLabel lblhanhdong = new JLabel(hanhdong[i]);
            lblhanhdong.setHorizontalAlignment(SwingConstants.CENTER);
            jpCen.add(lblhanhdong);
        }
        for (int i = 0; i < sizeDmCn; i++) {
            for (int j = 0; j < sizeHanhdong; j++) {
                listCheckBox[i][j] = new JCheckBox();
                listCheckBox[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jpCen.add(listCheckBox[i][j]);
            }
        }

        // Hiển thị nút thêm
        jpBottom = new JPanel(new FlowLayout());
        jpBottom.setBackground(Color.white);
        jpBottom.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        switch (type) {
            case "create" -> {
                btnAddQuyen = new ButtonCustom("Thêm nhóm quyền", "success", 14);
                btnAddQuyen.addActionListener(this);
                jpBottom.add(btnAddQuyen);
            }
            case "update" -> {
                btnUpdateQuyen = new ButtonCustom("Cập nhật nhóm quyền", "success", 14);
                btnUpdateQuyen.addActionListener(this);
                jpBottom.add(btnUpdateQuyen);
                initUpdate();
            }
            case "view" -> {
                initUpdate();
            }
            default -> throw new AssertionError();
        }
        
        
        btnHuybo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuybo.addActionListener(this);
        
        jpBottom.add(btnHuybo);

        this.add(jpTop, BorderLayout.NORTH);
        this.add(jpLeft, BorderLayout.WEST);
        this.add(jpCen, BorderLayout.CENTER);
        this.add(jpBottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public PhanQuyenDialog(QuyenBUS buss,PhanQuyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.nhomquyenBUS = buss;
        this.jpPhanQuyen = jpPhanQuyen;
        initComponents(type);
    }
    
    public PhanQuyenDialog(QuyenBUS buss,PhanQuyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type, QuyenDTO nhomquyendto) {
        super(owner, title, modal);
        this.nhomquyenBUS = buss;
        this.jpPhanQuyen = jpPhanQuyen;
        this.nhomquyenDTO = nhomquyendto;
        this.index = this.nhomquyenBUS.getAll().indexOf(this.nhomquyenDTO);
        this.ctQuyen = ChiTietQuyenDAO.getInstance().getAll(nhomquyendto.getMaQuyen());
        initComponents(type);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddQuyen) {
            int id = QuyenDAO.getInstance().getAutoIncrement();
            ctQuyen = this.getListChiTietQuyen(id);
            System.out.println(ctQuyen);
            if(txtTennhomquyen.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Tên nhóm quyền không được bỏ trống");
                return;
            }
            if(!ctQuyen.isEmpty()){
                nhomquyenBUS.add(txtTennhomquyen.getText(),ctQuyen);
                this.jpPhanQuyen.loadDataTalbe(nhomquyenBUS.getAll());
                dispose();
            }else
                JOptionPane.showMessageDialog(this,"Vui lòng chọn ít nhất 1 quyền");
        } else if(e.getSource() == btnUpdateQuyen){
            ctQuyen = this.getListChiTietQuyen(this.nhomquyenDTO.getMaQuyen());
            QuyenDTO nhomquyen = new QuyenDTO(this.nhomquyenDTO.getMaQuyen(),txtTennhomquyen.getText());
            nhomquyenBUS.update(nhomquyen,ctQuyen,index);
            this.jpPhanQuyen.loadDataTalbe(nhomquyenBUS.getAll());
            dispose();
        } else if (e.getSource() == btnHuybo) {
            dispose();
        }
    }

    public ArrayList<ChiTietQuyenDTO> getListChiTietQuyen(int manhomquyen) {
        ArrayList<ChiTietQuyenDTO> result = new ArrayList<>();
        for (int i = 0; i < sizeDmCn; i++) {
            for (int j = 0; j < sizeHanhdong; j++) {
                if (listCheckBox[i][j].isSelected()) {
                    result.add(new ChiTietQuyenDTO(manhomquyen, dmcn.get(i).getMaCN(), mahanhdong[j]));
                }
            }
        }
        return result;
    }
    
    public void initUpdate() {
        this.txtTennhomquyen.setText(nhomquyenDTO.getTenQuyen());
        System.out.println(ctQuyen);
        for (ChiTietQuyenDTO k : ctQuyen) {
            for (int i = 0; i < sizeDmCn; i++) {
                for (int j = 0; j < sizeHanhdong; j++) {
                    if(k.getHanhDong().equals(mahanhdong[j]) && k.getMaCN().equals(dmcn.get(i).getMaCN())) {
                        listCheckBox[i][j].setSelected(true);
                    }
                }
            }
        }
    }
}
