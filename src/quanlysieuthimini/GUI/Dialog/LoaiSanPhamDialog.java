package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.BUS.QuyenBUS;
import quanlysieuthimini.BUS.LoaiSanPhamBUS;
import quanlysieuthimini.DAO.LoaiSanPhamDAO;
import quanlysieuthimini.DTO.LoaiSanPhamDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Panel.QuanLyThanhPhanSP;
import quanlysieuthimini.helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import quanlysieuthimini.BUS.SanPhamBUS;
import quanlysieuthimini.DTO.SanPhamDTO;

public final class LoaiSanPhamDialog extends JDialog implements MouseListener {

    HeaderTitle headTite;
    JPanel top, main, bottom;
    InputForm ms, ms1, ms2;
    DefaultTableModel tblModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }
        
    };
    JTable table;
    ButtonCustom add, del, update;
    SanPhamBUS spBUS = new SanPhamBUS();
    ArrayList<SanPhamDTO> arrSP = spBUS.getAll();
    LoaiSanPhamBUS thBUS = new LoaiSanPhamBUS();
    ArrayList<LoaiSanPhamDTO> list = thBUS.getAll();
    QuanLyThanhPhanSP qltt;
    private final QuyenBUS nhomquyenBus = new QuyenBUS();

    public LoaiSanPhamDialog(JFrame owner, QuanLyThanhPhanSP qlttsp, String title, boolean modal, int nhomquyen) {
        super(owner, title, modal);
        initComponent(qlttsp);
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
        this.setSize(new Dimension(425, 600));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTite = new HeaderTitle("LOẠI SẢN PHẨM");
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
        ms = new InputForm("Tên Loại");
        ms.setPreferredSize(new Dimension(250, 70));
        
        ms1 = new InputForm("Cách Bảo Quản");
        ms1.setPreferredSize(new Dimension(250, 70));
        
        ms2 = new InputForm("Mô Tả Chi Tiết");
        ms2.setPreferredSize(new Dimension(250, 70));
        
        
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);

        table.setFocusable(false);
        String[] header = new String[]{"Mã Loại", "Tên Loại","Bảo Quản", "Mô Tả"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        
        
        
        main.add(ms);
        main.add(ms1);
        main.add(ms2);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 200));
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

    public void loadDataTable(ArrayList<LoaiSanPhamDTO> result) {
        tblModel.setRowCount(0);
        for (LoaiSanPhamDTO th : result) {
            tblModel.addRow(new Object[]{
                th.getMaLoai(), 
                th.getTenLoai(), 
                th.getCachBaoQuan(), 
                th.getMoTa()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (Validation.isEmpty(ms.getText()) || Validation.isEmpty(ms1.getText()) || Validation.isEmpty(ms2.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thương hiệu mới");
            } else {
                String tenLoai = ms.getText();
                String CachBQ = ms1.getText();
                String MoTa = ms2.getText();
                if (!thBUS.checkDup(tenLoai)) {
                    int id = LoaiSanPhamDAO.getInstance().getAutoIncrement();
                    if(id != -1){
                        thBUS.add(new LoaiSanPhamDTO(id, tenLoai, CachBQ, MoTa));
                        loadDataTable(list);
                        ms.setText("");
                        ms1.setText("");
                        ms2.setText("");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Loại Sản Phẩm đã tồn tại !");
                }
            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            boolean check = true;
            if (index != -1) {
                for(SanPhamDTO sp : arrSP){
                    if(sp.getMaLoai() == list.get(index).getMaLoai())
                        check = false;
                }
                if(check){
                    thBUS.delete(list.get(index),index);
                    loadDataTable(list);
                    ms.setText("");
                    ms1.setText("");
                    ms2.setText("");
                }else
                    JOptionPane.showMessageDialog(this, "Vi phạm ràng buộc!! (Có sản phẩm thuộc loại này không thể xóa)");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                if (Validation.isEmpty(ms.getText()) || Validation.isEmpty(ms1.getText()) || Validation.isEmpty(ms2.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập Loại Sản Phẩm mới");
                } else {
                    String tenLoai = ms.getText();
                    String CachBQ = ms1.getText();
                    String MoTa = ms2.getText();
//                    if (!thBUS.checkDup(tenLoai)) {
                        thBUS.update(new LoaiSanPhamDTO(list.get(index).getMaLoai(),tenLoai, CachBQ, MoTa));
                        loadDataTable(list);
                        ms.setText("");
                        ms1.setText("");
                        ms2.setText("");
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Loại Sản Phẩm đã tồn tại !");
//                    }
                }
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(list.get(index).getTenLoai());
            ms1.setText(list.get(index).getCachBaoQuan());
            ms2.setText(list.get(index).getMoTa());
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thương hiệu!");
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
