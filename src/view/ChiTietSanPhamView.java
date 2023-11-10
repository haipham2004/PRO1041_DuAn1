/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.SanPham;
import service.servicImp.ChatLieuServiceImp;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.KichThuocServiceImp;
import service.servicImp.MauSacServiceImp;
import service.servicImp.SanPhamServiceImp;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamView extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    DefaultComboBoxModel<LoaiSanPham> cbxLoaiSanPham = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> cbxMauSac = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbxChatLieu = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<KichThuoc> cbxKichThuoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<SanPham> cbxSanPham = new DefaultComboBoxModel<>();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChatLieuServiceImp serviceCl = new ChatLieuServiceImp();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    SanPhamServiceImp serviceSP = new SanPhamServiceImp();
    int trangCTSP = 1, soTrangCTSP, tongBanGhiCTSP, index = -1;

    /**
     * Creates new form CTSPView
     */
    public ChiTietSanPhamView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageCTSP();
        loadCbxSanPham(serviceSP.getAll());
        loadCbxKichThuoc(serviceKT.getAll());
        loadCbxMauSac(serviceMS.getAll());
        loadCbxChatLieu(serviceCl.getAll());
    }

    public void fillTableChiTietSanPham(List<ChiTietSanPham> list) {
        mol = (DefaultTableModel) tblChiTietSanPham.getModel();
        mol.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            mol.addRow(new Object[]{
                chiTietSanPham.getMaChiTietSanPham(),
                chiTietSanPham.getSoLuong(), chiTietSanPham.getGia(),
                chiTietSanPham.getSanPham(),
                chiTietSanPham.getChatLieu(), chiTietSanPham.getMauSac(),
                chiTietSanPham.getKichThuoc(), chiTietSanPham.isTrangThai() ? "Còn hàng" : "Hết hàng",
                chiTietSanPham.getSanPham().getTenSanPham()
            });
        }
    }

    public ChiTietSanPham savesCTSP() {
        String maCTSP;
        int soLuong;
        double gia;
        boolean trangThai;
        maCTSP = txtMaCTSP.getText();
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText());
            gia = Double.parseDouble(txtGia.getText());
        } catch (Exception e) {
            return null;
        }
        SanPham sp = (SanPham) cbxSanPham.getSelectedItem();
        ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
        MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
        KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
        if (rdConhang1.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new ChiTietSanPham(maCTSP, sp, ms, cl, kt, soLuong, gia, trangThai);

    }

    public void deltailChiTietSanPham(int index) {
        txtMaCTSP.setText(tblChiTietSanPham.getValueAt(index, 0).toString());
        txtSoLuong.setText(tblChiTietSanPham.getValueAt(index, 1).toString());
        txtGia.setText(tblChiTietSanPham.getValueAt(index, 2).toString());
        SanPham sp = (SanPham) tblChiTietSanPham.getValueAt(index, 3);
        cbxSanPham.setSelectedItem(sp);
        ChatLieu cl = (ChatLieu) tblChiTietSanPham.getValueAt(index, 4);
        cbxChatLieu.setSelectedItem(cl);
        MauSac ms = (MauSac) tblChiTietSanPham.getValueAt(index, 5);
        cbxMauSac.setSelectedItem(ms);
        KichThuoc kt = (KichThuoc) tblChiTietSanPham.getValueAt(index, 6);
        cbxKichThuoc.setSelectedItem(kt);
        if (tblChiTietSanPham.getValueAt(index, 7).toString().equals("Còn hàng")) {
            rdConhang1.setSelected(true);
        } else {
            rdHethang1.setSelected(true);
        }

    }

    public void loadPageCTSP() {
        tongBanGhiCTSP = serviceCTSP.tongBanGhi();
        if (tongBanGhiCTSP % 4 == 0) {
            soTrangCTSP = tongBanGhiCTSP / 4;
        } else {
            soTrangCTSP = tongBanGhiCTSP / 4 + 1;
        }
        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP));
    }
    
    public void loadCbxSanPham(List<SanPham> list) {
        cbxSanPham.removeAllElements();
        for (SanPham sanPham : list) {
            cbxSanPham.addElement(sanPham);
        }
        cboMaSP.setModel((ComboBoxModel) cbxSanPham);
    }
    
     public void loadCbxMauSac(List<MauSac> list) {
        cbxMauSac.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSac.addElement(mauSac);
        }
        cboMauSac.setModel((ComboBoxModel) cbxMauSac);
    }

    public void loadCbxKichThuoc(List<KichThuoc> list) {
        cbxKichThuoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuoc.addElement(kichThuoc);
        }
        cboKichThuoc.setModel((ComboBoxModel) cbxKichThuoc);
    }

    public void loadCbxChatLieu(List<ChatLieu> list) {
        cbxChatLieu.removeAllElements();
        for (ChatLieu chatLieu : list) {
            cbxChatLieu.addElement(chatLieu);
        }
        cboChatLieu.setModel((ComboBoxModel) cbxChatLieu);
    }

    public boolean validateCTSPs() {
        if (txtMaCTSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trống");
            return false;
        }
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng trống");
            return false;
        } else {
            try {
                int sl = Integer.parseInt(txtSoLuong.getText());
                if (sl < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lơn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
                return false;
            }
        }

        if (txtGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá trống");
            return false;
        } else {
            try {
                double gia = Double.parseDouble(txtGia.getText());
                if (gia < 0) {
                    JOptionPane.showMessageDialog(this, "Giáphải lơn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá phải là số");
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtMaCTSP = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        rdConhang1 = new javax.swing.JRadioButton();
        rdHethang1 = new javax.swing.JRadioButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTienMin = new javax.swing.JTextField();
        txtTienMax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSearchGia = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtSanPhamCT = new javax.swing.JTextField();
        btnSearch2a = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        cboMaSP = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnThemCTSP = new javax.swing.JButton();
        btnSuaCTSP = new javax.swing.JButton();
        btnClearCTSP = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnDau2 = new javax.swing.JButton();
        btnLui2 = new javax.swing.JButton();
        btnTien2 = new javax.swing.JButton();
        btnCuoi2 = new javax.swing.JButton();
        lbSoTrang2 = new javax.swing.JLabel();

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jLabel45.setText("Mã CTSP");

        jLabel46.setText("Tên SP");

        jLabel47.setText("Màu sắc");

        jLabel48.setText("Chất liệu");

        jLabel49.setText("Kích thước");

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel50.setText("Số lượng");

        jLabel51.setText("Giá");

        jLabel52.setText("Trạng thái");

        rdConhang1.setText("Còn hàng");

        rdHethang1.setText("Hết hàng");

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá tiền"));
        jPanel10.setToolTipText("");

        jLabel4.setText("Từ");

        jLabel7.setText("Đến");

        btnSearchGia.setText("Search");
        btnSearchGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchGiaActionPerformed(evt);
            }
        });

        jLabel8.setText("VND");

        jLabel9.setText("VND");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTienMin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnSearchGia))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienMax, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTienMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchGia)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel11.setToolTipText("");

        btnSearch2a.setText("Search");
        btnSearch2a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2aActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSanPhamCT, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch2a)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSanPhamCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch2a))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Số lượng", "Giá", "Mã SP", "Chất liệu", "Màu sắc ", "Kích thước", "Trạng thái"
            }
        ));
        tblChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblChiTietSanPham);

        cboMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("VND");

        jLabel6.setText("Cái");

        jButton3.setText("Quay lại");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnThemCTSP.setText("Thêm");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        btnSuaCTSP.setText("Sửa");
        btnSuaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTSPActionPerformed(evt);
            }
        });

        btnClearCTSP.setText("Clear");
        btnClearCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCTSPActionPerformed(evt);
            }
        });

        jButton4.setText("Xuất file");

        btnDau2.setText("Pre");
        btnDau2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDau2ActionPerformed(evt);
            }
        });

        btnLui2.setText("Lùi");
        btnLui2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLui2ActionPerformed(evt);
            }
        });

        btnTien2.setText("Tiến");
        btnTien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTien2ActionPerformed(evt);
            }
        });

        btnCuoi2.setText("Next");
        btnCuoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoi2ActionPerformed(evt);
            }
        });

        lbSoTrang2.setText("Số trang");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnThemCTSP))
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5)))
                                        .addGap(47, 47, 47)))))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                            .addComponent(jLabel47)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(287, 287, 287))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(btnSuaCTSP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnClearCTSP))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdConhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rdHethang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4))
                                .addGap(233, 233, 233)))))
                .addGap(34, 34, 34))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(btnDau2)
                        .addGap(38, 38, 38)
                        .addComponent(btnLui2)
                        .addGap(15, 15, 15)
                        .addComponent(lbSoTrang2)
                        .addGap(18, 18, 18)
                        .addComponent(btnTien2)
                        .addGap(33, 33, 33)
                        .addComponent(btnCuoi2))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(rdConhang1)
                            .addComponent(rdHethang1)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49)
                                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel50))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))))
                .addGap(21, 21, 21)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCTSP)
                    .addComponent(btnSuaCTSP)
                    .addComponent(btnClearCTSP)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDau2)
                        .addComponent(btnLui2)
                        .addComponent(lbSoTrang2))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTien2)
                        .addComponent(btnCuoi2)))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGiaActionPerformed
        // TODO add your handling code here:
        double giaMin = Double.parseDouble(txtTienMin.getText());
        double giaMax = Double.parseDouble(txtTienMax.getText());
        fillTableChiTietSanPham(serviceCTSP.getListGia(giaMin, giaMax));
    }//GEN-LAST:event_btnSearchGiaActionPerformed

    private void btnSearch2aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2aActionPerformed
        // TODO add your handling code here
        String tenSP = txtSanPhamCT.getText();
        tenSP = '%' + tenSP + '%';
        fillTableChiTietSanPham(serviceCTSP.getList(tenSP));
    }//GEN-LAST:event_btnSearch2aActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();
        txtMaCTSP.setEnabled(false);
        deltailChiTietSanPham(index);
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        fillTableChiTietSanPham(serviceCTSP.getAll());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        // TODO add your handling code here:
        ChiTietSanPham ctsp = savesCTSP();
        if (validateCTSPs()) {
            if (serviceCTSP.getOne(ctsp.getMaChiTietSanPham()) != null) {
                JOptionPane.showMessageDialog(this, "Mã chi tiết sản phẩm trùng");
                return;
            } else {
                if (serviceCTSP.them(ctsp) > 0) {
                    loadPageCTSP();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void btnSuaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTSPActionPerformed
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
            return;
        } else {
            ChiTietSanPham ctsp = savesCTSP();
            String ma = tblChiTietSanPham.getValueAt(index, 0).toString();
            if (serviceCTSP.sua(ctsp, ma) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thành công");
                loadPageCTSP();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thất bại");
            }
        }

    }//GEN-LAST:event_btnSuaCTSPActionPerformed

    private void btnClearCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCTSPActionPerformed
        // TODO add your handling code here:
        txtMaCTSP.setEnabled(true);
        txtMaCTSP.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        cboMaSP.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearCTSPActionPerformed

    private void btnDau2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDau2ActionPerformed
        // TODO add your handling code here:
        trangCTSP = 1;
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP));
        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
    }//GEN-LAST:event_btnDau2ActionPerformed

    private void btnLui2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui2ActionPerformed
        // TODO add your handling code here:
        if (trangCTSP > 1) {
            trangCTSP--;
            fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP));
            lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnLui2ActionPerformed

    private void btnTien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien2ActionPerformed
        // TODO add your handling code here:
        if (trangCTSP < soTrangCTSP) {
            trangCTSP++;
            fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP));
            lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnTien2ActionPerformed

    private void btnCuoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoi2ActionPerformed
        // TODO add your handling code here:
        trangCTSP = soTrangCTSP;
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP));
        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
    }//GEN-LAST:event_btnCuoi2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearCTSP;
    private javax.swing.JButton btnCuoi2;
    private javax.swing.JButton btnDau2;
    private javax.swing.JButton btnLui2;
    private javax.swing.JButton btnSearch2a;
    private javax.swing.JButton btnSearchGia;
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnTien2;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbSoTrang2;
    private javax.swing.JRadioButton rdConhang1;
    private javax.swing.JRadioButton rdHethang1;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaCTSP;
    private javax.swing.JTextField txtSanPhamCT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTienMax;
    private javax.swing.JTextField txtTienMin;
    // End of variables declaration//GEN-END:variables
}
