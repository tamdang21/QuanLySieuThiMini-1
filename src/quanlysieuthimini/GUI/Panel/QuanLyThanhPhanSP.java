package quanlysieuthimini.GUI.Panel;

import quanlysieuthimini.GUI.Component.IntegratedSearch;
import quanlysieuthimini.GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysieuthimini.GUI.Component.PanelBorderRadius;
import quanlysieuthimini.GUI.Component.itemTaskbar;
//import quanlysieuthimini.GUI.Dialog.DungLuongRamDialog;
import quanlysieuthimini.GUI.Dialog.DonViDialog;
//import quanlysieuthimini.GUI.Dialog.HeDieuHanhDialog;
//import quanlysieuthimini.GUI.Dialog.MauSacDialog;
import quanlysieuthimini.GUI.Dialog.LoaiSanPhamDialog;
import quanlysieuthimini.GUI.Dialog.HangSanXuatDialog;
import quanlysieuthimini.GUI.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyThanhPhanSP extends JPanel {

    private final int n = 20;
    JPanel box[], pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    JLabel lbl[], lblIcon[], info;
    JScrollPane scrPane;
    LoaiSanPhamDialog th;
    HangSanXuatDialog xs;
//    HeDieuHanhDialog hdh;
//    DungLuongRamDialog dlram;
    DonViDialog dlrom;
//    MauSacDialog mausac;
    Main m;
    public itemTaskbar[] listitem;

    String iconst[] = {"brand_100px.svg", "factory_100px.svg", "ram_100px.svg"};

    String header[] = {"Loại Sản Phẩm", "Hãng Sản Xuất", "Đơn Vị"};
    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    
    public QuanLyThanhPhanSP(Main m) {
        this.m = m;
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[header.length];

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new GridLayout(3, 2, 20, 20));

//        scrPane = new JScrollPane(contentCenter);
//        scrPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(contentCenter, BorderLayout.CENTER);

        box = new JPanel[n];
        lbl = new JLabel[n];
        lblIcon = new JLabel[n];
        for (int i = 0; i < header.length; i++) {
            listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
            contentCenter.add(listitem[i]);
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                th = new LoaiSanPhamDialog(owner, QuanLyThanhPhanSP.this, "Quản lý Loại Sản Phẩm", true,m.user.getMaQuyen());
                th.setVisible(true);
            }
        });
        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                xs = new HangSanXuatDialog(owner, QuanLyThanhPhanSP.this, "Quản lý Hãng Sản Xuất Sản Phẩm", true,m.user.getMaQuyen());
                xs.setVisible(true);
            }
        });

        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                dlrom = new DonViDialog(owner, QuanLyThanhPhanSP.this, "Quản lý Đơn Vị Sản Phẩm", true,m.user.getMaQuyen());
                dlrom.setVisible(true);
            }
        });
    }

    public void Mouseopress(MouseEvent evt) {
        for (int i = 0; i < listitem.length; i++) {
            if (evt.getSource() == listitem[i]) {

            }
        }
    }

    

    public void initPadding() {

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 40));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 40));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(40, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(40, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

    }

}
