/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.DTO.SanPhamDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.CheckListItem;
import quanlysieuthimini.GUI.Component.CheckListRenderer;
import quanlysieuthimini.GUI.Panel.TaoHoaDon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ChonMaVach extends JDialog{

    private DefaultListModel<Object> listMode;
    private ArrayList<SanPhamDTO> listSP;
    private JTextField timMaVach;
    private JList list;
    private JTextField jTextField;
    
    public ChonMaVach(JFrame owner, String title, boolean modal, TaoHoaDon taohoadon, ArrayList<SanPhamDTO> listSP){
        super(owner, title, modal);
        this.jTextField = taohoadon.txtMaVach;
        this.listSP = listSP;
        init();
        setVisible(true);
    }
    
//    public SelectImei(JFrame owner, String title, boolean modal, TaoPhieuKiemKe taoPhieuKiemKe, ArrayList<ct){
//        super(owner, title, modal);
//        this.jTextField = taoPhieuKiemKe.textAreaImei;
//        this.ct = ct;
//        init();
//        setVisible(true);
//    }
    
    public void init(){
        setSize(new Dimension(300,500));
        setLayout(new BorderLayout());
        listMode = new DefaultListModel<>();
        timMaVach = new JTextField("");
        loadImei();
        timMaVach.setPreferredSize(new Dimension(0,40));
        timMaVach.putClientProperty("JTextField.placeholderText", "Tìm kiếm mã IMEI ...");
        timMaVach.putClientProperty("JTextField.showClearButton", true);
        timMaVach.addKeyListener(new KeyAdapter(){
        @Override
            public void keyReleased(KeyEvent e) {
            loadImei();
            }
        });
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setPreferredSize(new Dimension(0,50));
        panelBottom.setBackground(Color.WHITE);
        ButtonCustom btnXacNhan = new ButtonCustom("Xác nhận", "success", 14);
        panelBottom.add(btnXacNhan);
        this.add(timMaVach,BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(list),BorderLayout.CENTER);
        this.add(panelBottom,BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        
        btnXacNhan.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    public void loadImei(){
        String search = timMaVach.getText();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : listSP) {
            if (i.getMaVach().toLowerCase().contains(search)) {
                result.add(i);
            }
        }
        listMode.setSize(0);
        for (SanPhamDTO chiTietSanPhamDTO : result) {
            CheckListItem check = new CheckListItem(chiTietSanPhamDTO.getMaVach());
            if(checkImeiArea(chiTietSanPhamDTO.getMaVach())){
                check.setSelected(true);
            }
            listMode.addElement(check);
        }
        list = new JList(listMode);
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());// Get index of item
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
                if(!item.isSelected()){
                    jTextField.setText(item.toString() + "\n");
                } else {
                    String txt = jTextField.getText().replaceAll("(" + item.toString() + ")\n", "");
                    jTextField.setText(txt);
                }
                item.setSelected(!item.isSelected()); // Toggle selected state
                list.repaint(list.getCellBounds(index, index));// Repaint cell
            }
        });
    }
    
    public boolean checkImeiArea(String maImei){
        String[] arrimei = jTextField.getText().split("\n");
        boolean check = false;
        for (int i=0;i<arrimei.length;i++){
            if(arrimei[i].equals(maImei)){
                check = true;
                return check;
            }
        }
        return check;
    }
}
