package quanlysieuthimini.GUI.Component;

import quanlysieuthimini.BUS.QuyenBUS;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public final class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btn = new HashMap<>();
    private final QuyenBUS nhomquyenBus = new QuyenBUS();

    public MainFunction(int manhomquyen, String chucnang, String[] listBtn) {
        initData();
        initComponent(manhomquyen, chucnang, listBtn);
    }

    public void initData() {
        btn.put("create", new ButtonToolBar("THÊM", "add.svg", "create"));
        btn.put("add", new ButtonToolBar("TẠO PHIẾU", "add.svg", "create"));
        btn.put("delete", new ButtonToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ButtonToolBar("SỬA", "edit.svg", "update"));
        btn.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "cancel.svg", "delete"));
        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", "view"));
        btn.put("import", new ButtonToolBar("NHẬP EXCEL", "import_excel.svg", "create"));
        btn.put("export", new ButtonToolBar("XUẤT EXCEL", "export_excel.svg", "view"));
        btn.put("phieuchi", new ButtonToolBar("PHIẾU CHI", "payment.svg", "view"));
        btn.put("phancongca", new ButtonToolBar("PHÂN CÔNG", "exchange.svg", "create"));
        btn.put("quaylai", new ButtonToolBar("TRỞ VỀ", "back2.svg", "view"));
    }

    private void initComponent(int manhomquyen, String chucnang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String btnn : listBtn) {
            this.add(btn.get(btnn));
            if (!nhomquyenBus.checkPermisson(manhomquyen, chucnang, btn.get(btnn).getPermisson())) {
                btn.get(btnn).setEnabled(false);
            }
        }
    }
}
