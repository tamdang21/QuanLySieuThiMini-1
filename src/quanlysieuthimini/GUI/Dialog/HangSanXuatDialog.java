/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.HangSanXuatBUS;
import quanlysieuthimini.BUS.QuyenBUS;
import quanlysieuthimini.DAO.HangSanXuatDAO;
import quanlysieuthimini.DTO.HangSanXuatDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Panel.QuanLyThanhPhanSP;
import com.formdev.flatlaf.FlatIntelliJLaf;
import quanlysieuthimini.helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author 84907
 */
public class HangSanXuatDialog extends JDialog implements MouseListener {

    HeaderTitle headTite;
    JPanel top, main, bottom, all;
    InputForm ms, ms1;
    DefaultTableModel tblModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }
        
    };
    JTable table;
    JScrollPane scrollTable;
    ButtonCustom add, del, update;
    HangSanXuatBUS msBUS = new HangSanXuatBUS();
    ArrayList<HangSanXuatDTO> list = msBUS.getAll();
    
    QuanLyThanhPhanSP qltt;
    private final QuyenBUS nhomquyenBus = new QuyenBUS();

    public HangSanXuatDialog(JFrame owner, QuanLyThanhPhanSP qltt, String title, boolean modal, int nhomquyen) {
        super(owner, title, modal);
        initComponent(qltt);
        loadQuyen(nhomquyen);
        loadDataTable(list);
    }

    public void loadQuyen(int nhomquyen) {
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thanhphan", "create")) {
            add.setVisible(false);
        }
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thanhphan", "delete")) {
            del.setVisible(false);
        }
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thanhphan", "update")) {
            update.setVisible(false);
        }
    }

    public void initComponent(QuanLyThanhPhanSP qltt) {

        this.qltt = qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTite = new HeaderTitle("HÃNG SẢN XUẤT SẢN PHẨM");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();

        top.setLayout(new GridLayout(1, 1));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 70));
        top.add(headTite);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        ms = new InputForm("Tên Hãng");
        ms.setPreferredSize(new Dimension(250, 70));
        
        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        ms1 = new InputForm("Trụ Sở");
        ms1.setPreferredSize(new Dimension(250, 70));
        
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane(table);
        scrollTable.setBackground(Color.WHITE);
        String[] header = new String[]{"Mã Hãng", "Tên Hãng", "Trụ Sở"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 250));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);

        main.add(ms);
        main.add(ms1);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel", 15, 100, 40);
        add.addMouseListener(this);
        del = new ButtonCustom("Xóa", "danger", 15, 100, 40);
        del.addMouseListener(this);
        update = new ButtonCustom("Sửa", "success", 15, 100, 40);
        update.addMouseListener(this);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1, 20, 20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<HangSanXuatDTO> result) {
        tblModel.setRowCount(0);
        for (HangSanXuatDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMaHang(), ncc.getTenHang(), ncc.getTruSo()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (Validation.isEmpty(ms.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin Hãng Sản Xuất mới");
            } else {
                String tenHang = ms.getText();
                String TruSo = ms1.getText();
                if (!msBUS.checkDup(tenHang)) {
                    int id = HangSanXuatDAO.getInstance().getAutoIncrement();
                    msBUS.add(new HangSanXuatDTO(id, tenHang,TruSo));
                    loadDataTable(list);
                    ms.setText("");
                    ms1.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Hãng Sản Xuất đã tồn tại !");
                }
            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                msBUS.delete(list.get(index), index);
                loadDataTable(list);
                ms.setText("");
                ms1.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                if (Validation.isEmpty(ms.getText()) || Validation.isEmpty(ms1.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin Hãng sản Xuất mới");
                } else {
                    String tenHang = ms.getText();
                    String TruSo = ms1.getText();
//                        HangSanXuatDTO hsx = new HangSanXuatDTO(list.get(index).getMaHang(), tenHang, TruSo);
//                        msBUS.update(hsx);
//                        loadDataTable(list);
//                        ms.setText("");
//                        ms1.setText("");
//                        dispose();
                    
//                    if (!msBUS.checkDup(tenHang)) {
                        msBUS.update(new HangSanXuatDTO(list.get(index).getMaHang(), tenHang, TruSo));
                        loadDataTable(list);
                        ms.setText("");
                        ms1.setText("");
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Hãng Sản Xuất đã tồn tại !");
//                    }
                }
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(list.get(index).getTenHang());
            ms1.setText(list.get(index).getTruSo());
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Hãng Sản Xuất!");
        }
        return index;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
}
