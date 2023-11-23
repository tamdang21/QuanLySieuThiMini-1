/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysieuthimini.GUI.Dialog;

import quanlysieuthimini.DAO.QuyenDAO;
import quanlysieuthimini.DAO.TaiKhoanDAO;
import quanlysieuthimini.DTO.QuyenDTO;
import quanlysieuthimini.DTO.TaiKhoanDTO;
import quanlysieuthimini.GUI.Component.ButtonCustom;
import quanlysieuthimini.GUI.Component.HeaderTitle;
import quanlysieuthimini.GUI.Component.InputForm;
import quanlysieuthimini.GUI.Component.SelectForm;
import quanlysieuthimini.GUI.Panel.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import quanlysieuthimini.BUS.CaLamViecBUS;
import quanlysieuthimini.DAO.CaLamViecDAO;
import quanlysieuthimini.DAO.PhanCongCaDAO;
import quanlysieuthimini.DTO.CaLamViecDTO;
import quanlysieuthimini.DTO.PhanCongCaDTO;
import quanlysieuthimini.GUI.Panel.PhanCongCa;

public class ListCaLamViecPCC extends JDialog {

    private PhanCongCa pcc;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnHuyBo;
    private SelectForm cbxTenCa;
    private SelectForm cbxThu;
    private SelectForm cbxTrangThai;
    int manv;
    //private ArrayList<QuyenDTO> listNq = QuyenDAO.getInstance().getAll();
    private CaLamViecBUS clvBUS = new CaLamViecBUS();
    private String[] listTenCa = clvBUS.getArrTenCa();
    //private ArrayList<TaiKhoanDTO> listTK = TaiKhoanDAO.getInstance().getAll();
    private ArrayList<PhanCongCaDTO> listPCC = PhanCongCaDAO.getInstance().getAllNotId();

    public ListCaLamViecPCC(PhanCongCa pcc, JFrame owner, String title, boolean modal, String type, int manv) {
        super(owner, title, modal);
        init(title, type);
        this.manv = manv;
        this.pcc = pcc;
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init(String title, String type) {
        this.setSize(new Dimension(500, 530));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(3, 1, 5, 0));
        pnmain.setBackground(Color.white);
        
        cbxTenCa = new SelectForm("Tên ca", listTenCa);
        String[] listThu = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật"};
        cbxThu = new SelectForm("Ngày làm", listThu);
        cbxTrangThai = new SelectForm("Trạng thái", new String[]{"Ngưng hoạt động", "Hoạt động"});
        
        pnmain.add(cbxTenCa);
        pnmain.add(cbxThu);
        pnmain.add(cbxTrangThai);
        
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm lịch làm việc", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    int maca = clvBUS.getByIndex(cbxTenCa.getSelectedIndex()).getMaCa();
                    String thu = (String) cbxThu.getSelectedItem();
                    PhanCongCaDTO pccDTO = new PhanCongCaDTO(maca, manv, thu);
                    clvBUS.insertPCC(pccDTO);
                    
                    listPCC = clvBUS.getAllNotId();
                    
                    JOptionPane.showMessageDialog(null, "Lịch làm việc được thêm thành công !!", "Thêm thành công!", JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                    pcc.loadDataTalbe(listPCC);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Lịch làm việc của nhân viên này đã tồn tại, vui lòng chọn ngày khác !!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        switch (type) {
            case "create" ->
                pnbottom.add(btnThem);
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
    }
    
    public boolean validateInput() {
        for(PhanCongCaDTO pcc : listPCC) {
            if(pcc.getMaNV() == manv && pcc.getThu().equals(cbxThu.getCbb().getSelectedItem()) && clvBUS.getById(pcc.getMaCa()).getTenCa().equals(cbxTenCa.getCbb().getSelectedItem()) ) {
                return false;
            }
        }
        
        return true;
    }
}
