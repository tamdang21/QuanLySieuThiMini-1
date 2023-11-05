/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.DAO.NhanVienDAO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Panel.TaiKhoan;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import quanlysieuthimini.DAO.KhuyenMaiDAO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.DTO.KhuyenMaiDTO;
import quanlysieuthimini.GUI.Panel.TaoHoaDon;

public class ListKhuyenMai extends JDialog implements MouseListener {

    private TaoHoaDon taoHD;
    private JTable tableKhuyenMai;
    private JScrollPane scrollTableSanPham;
    private DefaultTableModel tblModel;
    private ArrayList<KhuyenMaiDTO> listKhuyenMai = KhuyenMaiDAO.getInstance().getAll();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public ListKhuyenMai(TaoHoaDon taoHD, JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        this.taoHD=taoHD;
        init();
        loadDataTalbe(search(""));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init(){
        this.setSize(new Dimension(850,600));
        this.setLayout(new BorderLayout());
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.setSize(new Dimension(0,150));
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel jLabelSearch = new JLabel("Tìm kiếm  ");
        jLabelSearch.setSize(new Dimension(100,0));
        JTextField jTextFieldSearch = new JTextField();
        jTextFieldSearch.setFont(new Font(FlatRobotoFont.FAMILY, 0, 13));
        jTextFieldSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm khuyến mãi....");
        jTextFieldSearch.putClientProperty("JTextField.showClearButton", true);
        jTextFieldSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = jTextFieldSearch.getText();
                listKhuyenMai = search(txt);
                loadDataTalbe(listKhuyenMai);
            }
        });
        ButtonCustom buttonAdd = new ButtonCustom("Chọn khuyến mãi", "success", 14);
        buttonAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getRow()<0){
                    int input = JOptionPane.showConfirmDialog(null, 
                "Vui lòng chọn khuyến mãi!:)", "Thông báo", JOptionPane.DEFAULT_OPTION);
                } else{
                    taoHD.setKhachHang(listKhuyenMai.get(getRow()).getMaKM());
                    dispose();
                }
            }
            
        });
        panelSearch.add(jLabelSearch,BorderLayout.WEST);
        panelSearch.add(jTextFieldSearch,BorderLayout.CENTER);
        panelSearch.add(buttonAdd,BorderLayout.EAST);
        this.add(panelSearch,BorderLayout.NORTH);
        JPanel jPanelTable = new JPanel();
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        jPanelTable.setLayout(new GridLayout(1,1));
        tableKhuyenMai = new JTable();
        tableKhuyenMai.setFocusable(false);
        scrollTableSanPham = new JScrollPane();
        tableKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableKhuyenMai = new JTable();
        tableKhuyenMai.setFocusable(false);
        tableKhuyenMai.setDefaultEditor(Object.class, null);
        tableKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã KM","Tên KM","Điều kiện","% khuyến mãi","Ngày bắt đầu","Ngày kết thúc"};
        tblModel.setColumnIdentifiers(header);
        tableKhuyenMai.setDefaultRenderer(Object.class, centerRenderer);
        tableKhuyenMai.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableKhuyenMai);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKhuyenMai.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableKhuyenMai.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        jPanelTable.add(scrollTableSanPham);
        this.add(jPanelTable,BorderLayout.CENTER);
        
    }
    
    public int getRow(){
        return tableKhuyenMai.getSelectedRow();
    }
    
    public void loadDataTalbe(ArrayList<KhuyenMaiDTO> list) {
        listKhuyenMai = list;
        tblModel.setRowCount(0);
        for (KhuyenMaiDTO kh : listKhuyenMai) {
            tblModel.addRow(new Object[]{
                kh.getMaKM(),
                kh.getTenKM(),
                kh.getDieuKienKM(),
                kh.getPhanTramKM(),
                kh.getNgayBatDau(),
                kh.getNgayKetThuc()
            });
        }
    }
    
    public ArrayList<KhuyenMaiDTO> search(String text) {
        if(text.length()>0){
            text = text.toLowerCase();
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        for(KhuyenMaiDTO i : listKhuyenMai) {
           if(i.getTenKM().toLowerCase().contains(text) 
                   || String.valueOf(i.getDieuKienKM()).toLowerCase().contains(text)
                   || String.valueOf(i.getPhanTramKM()).toLowerCase().contains(text)
                   || String.valueOf(i.getNgayBatDau()).toLowerCase().contains(text)
                   || String.valueOf(i.getNgayKetThuc()).toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
        } else {
            return KhuyenMaiDAO.getInstance().getAll();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
