/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.KichThuoc;
import model.MauSac;
import service.servicImp.ChatLieuServiceImp;
import service.servicImp.KichThuocServiceImp;
import service.servicImp.MauSacServiceImp;

/**
 *
 * @author Admin
 */
public class ThuocTinhView extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChatLieuServiceImp serviceCl = new ChatLieuServiceImp();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    int index = -1;
    int trangMS = 1, soTrangMS, tongBanGhiMS;
    int trangCL = 1, soTrangCL, tongBanGhiCL;
    int trangKT = 1, soTrangKT, tongBanGhiKT;

    /**
     * Creates new form ThuocTinhView
     */
    public ThuocTinhView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageCL();
        loadPageMS();
        loadPageKT();
    }

    public ChatLieu savesCL() {
        String maCL, tenCL;
        boolean trangThai;
        maCL = txtTenMa.getText();
        tenCL = txtTenThuocTinh.getText();
        if (rdConhang2.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new ChatLieu(maCL, tenCL, trangThai);
    }

    public MauSac savesMS() {
        String maMS, tenMS;
        boolean trangThai;
        maMS = txtTenMa.getText();
        tenMS = txtTenThuocTinh.getText();
        if (rdConhang2.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new MauSac(maMS, tenMS, trangThai);
    }

    public KichThuoc savesKT() {
        String maKT, tenKT;
        boolean trangThai;
        maKT = txtTenMa.getText();
        tenKT = txtTenThuocTinh.getText();
        if (rdConhang2.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new KichThuoc(maKT, tenKT, trangThai);
    }

    public void fillChatLieu(List<ChatLieu> list) {
        mol = (DefaultTableModel) tblChatLieu.getModel();
        mol.setRowCount(0);
        for (ChatLieu chatLieu : list) {
            mol.addRow(new Object[]{
                chatLieu.getMaChatLieu(), chatLieu.getTenChatLieu(), chatLieu.isTrangThai() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void fillMauSac(List<MauSac> list) {
        mol = (DefaultTableModel) tblMauSac.getModel();
        mol.setRowCount(0);
        for (MauSac mauSac : list) {
            mol.addRow(new Object[]{
                mauSac.getMaMauSac(), mauSac.getTenMauSac(), mauSac.isTrangThai() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void fillKichThuoc(List<KichThuoc> list) {
        mol = (DefaultTableModel) tblKichThuoc.getModel();
        mol.setRowCount(0);
        for (KichThuoc kichThuoc : list) {
            mol.addRow(new Object[]{
                kichThuoc.getMaKichThuoc(), kichThuoc.getTenKichThuoc(), kichThuoc.isTrangThai() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void detailThuocTinh(int index) {
        txtTenMa.setText(tblChatLieu.getValueAt(index, 0).toString());
        txtTenThuocTinh.setText(tblChatLieu.getValueAt(index, 1).toString());
        if (tblChatLieu.getValueAt(index, 2).toString().equals("Còn hàng")) {
            rdConhang2.setSelected(true);
        } else {
            rdHethang2.setSelected(true);
        }
    }

    public void loadPageMS() {
        tongBanGhiMS = serviceMS.tongBanGhi();
        if (tongBanGhiMS % 4 == 0) {
            soTrangMS = tongBanGhiMS / 4;
        } else {
            soTrangMS = tongBanGhiMS / 4 + 1;
        }
        lbSoTrang.setText(trangMS + " of " + soTrangMS);
        fillMauSac(serviceMS.listPageMS(trangMS));
    }

    public void loadPageCL() {
        tongBanGhiCL = serviceCl.tongBanGhi();
        if (tongBanGhiCL % 4 == 0) {
            soTrangCL = tongBanGhiCL / 4;
        } else {
            soTrangCL = tongBanGhiCL / 4 + 1;
        }
        lbSoTrang.setText(trangCL + " of " + soTrangCL);
        fillChatLieu(serviceCl.listPageCL(trangCL));
    }

    public void loadPageKT() {
        tongBanGhiKT = serviceKT.tongBanGhi();
        if (tongBanGhiKT % 4 == 0) {
            soTrangKT = tongBanGhiKT / 4;
        } else {
            soTrangKT = tongBanGhiKT / 4 + 1;
        }
        lbSoTrang.setText(trangKT + " of " + soTrangKT);
        fillKichThuoc(serviceKT.listPageKT(trangKT));
    }

    public boolean validateTT() {
        if (txtTenMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trống");
            return false;
        }

        if (txtTenThuocTinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên trống");
            return false;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenMa = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        rdConhang2 = new javax.swing.JRadioButton();
        rdHethang2 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        rdChatLieu = new javax.swing.JRadioButton();
        rdKichThuoc = new javax.swing.JRadioButton();
        rdMauSac = new javax.swing.JRadioButton();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnClearThuocTinh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnDau3 = new javax.swing.JButton();
        btnCuoi3 = new javax.swing.JButton();
        btnLui3 = new javax.swing.JButton();
        btnTien3 = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        pnlThuocTinh = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý thuộc tính"));

        jLabel1.setText("Mã");

        jLabel2.setText("tên");

        jLabel3.setText("Trạng thái");

        rdConhang2.setText("Còn hàng");

        rdHethang2.setText("Hết hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMa, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rdConhang2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdHethang2)
                        .addGap(43, 43, 43))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdConhang2)
                    .addComponent(rdHethang2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        buttonGroup1.add(rdChatLieu);
        rdChatLieu.setText("Chất liệu");
        rdChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdChatLieuMouseClicked(evt);
            }
        });
        rdChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChatLieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdKichThuoc);
        rdKichThuoc.setText("Kích thước");
        rdKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKichThuocActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdMauSac);
        rdMauSac.setText("Màu sắc");
        rdMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(rdChatLieu)
                .addGap(18, 18, 18)
                .addComponent(rdMauSac)
                .addGap(35, 35, 35)
                .addComponent(rdKichThuoc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdKichThuoc)
                    .addComponent(rdChatLieu)
                    .addComponent(rdMauSac))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnClearThuocTinh.setText("Clear");
        btnClearThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearThuocTinhActionPerformed(evt);
            }
        });

        jButton1.setText("Xuất file");

        btnDau3.setText("Đầu ");
        btnDau3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDau3ActionPerformed(evt);
            }
        });

        btnCuoi3.setText("Cuối");
        btnCuoi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoi3ActionPerformed(evt);
            }
        });

        btnLui3.setText("Lùi");
        btnLui3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLui3ActionPerformed(evt);
            }
        });

        btnTien3.setText("Tiến");
        btnTien3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTien3ActionPerformed(evt);
            }
        });

        lbSoTrang.setText("Số trang");

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Trạng thái"
            }
        ));
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChatLieu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThuocTinh.addTab("Chất liệu", jPanel2);

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Trạng thái"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMauSac);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pnlThuocTinh.addTab("Màu Sắc", jPanel3);

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Trạng thái"
            }
        ));
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKichThuoc);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );

        pnlThuocTinh.addTab("Kích Thước", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnThemThuocTinh)
                                .addGap(56, 56, 56)
                                .addComponent(btnSuaThuocTinh)
                                .addGap(18, 18, 18)
                                .addComponent(btnClearThuocTinh))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(btnDau3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLui3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSoTrang)
                        .addGap(22, 22, 22)
                        .addComponent(btnTien3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCuoi3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlThuocTinh)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThuocTinh)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnClearThuocTinh)
                    .addComponent(jButton1))
                .addGap(39, 39, 39)
                .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDau3)
                    .addComponent(btnCuoi3)
                    .addComponent(btnLui3)
                    .addComponent(btnTien3)
                    .addComponent(lbSoTrang))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChatLieuActionPerformed
        // TODO add your handling code here:
        loadPageCL();
    }//GEN-LAST:event_rdChatLieuActionPerformed

    private void rdKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKichThuocActionPerformed
        // TODO add your handling code here:
        loadPageKT();
    }//GEN-LAST:event_rdKichThuocActionPerformed

    private void rdMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMauSacActionPerformed
        // TODO add your handling code here:
        loadPageMS();
    }//GEN-LAST:event_rdMauSacActionPerformed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        // TODO add your handling code here:
        if (rdChatLieu.isSelected()) {
            ChatLieu cl = savesCL();
            if (validateTT()) {
                if (serviceCl.getOne(cl.getMaChatLieu()) != null) {
                    JOptionPane.showMessageDialog(this, "Mã chất liệu trùng");
                    return;
                } else {
                    if (serviceCl.them(cl) > 0) {
                        fillChatLieu(serviceCl.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm sản chất liệu thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm chất liệu thất bại");
                    }
                }
            }
        } else if (rdMauSac.isSelected()) {
            MauSac ms = savesMS();
            if (validateTT()) {
                if (serviceMS.getOne(ms.getMaMauSac()) != null) {
                    JOptionPane.showMessageDialog(this, "Mã màu sắc trùng");
                    return;
                } else {
                    if (serviceMS.them(ms) > 0) {
                        fillMauSac(serviceMS.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm màu sắc thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm màu sắc thất bại");
                    }
                }
            }
        } else {
            KichThuoc kt = savesKT();
            if (validateTT()) {
                if (serviceKT.getOne(kt.getMaKichThuoc()) != null) {
                    JOptionPane.showMessageDialog(this, "Mã kích thước trùng");
                    return;
                } else {
                    if (serviceKT.them(kt) > 0) {
                        fillKichThuoc(serviceKT.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm kích thước thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm kích thước thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        // TODO add your handling code here:
        index = tblChatLieu.getSelectedRow();
        if (rdChatLieu.isSelected()) {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            } else {
                ChatLieu cl = savesCL();
                String ma = tblChatLieu.getValueAt(index, 0).toString();
                if (serviceCl.sua(cl, ma) > 0) {
                    fillChatLieu(serviceCl.getAll());
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thất bại");
                }
            }
        } else if (rdMauSac.isSelected()) {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            } else {
                MauSac ms = savesMS();
                String ma = tblChatLieu.getValueAt(index, 0).toString();
                if (serviceMS.sua(ms, ma) > 0) {
                    fillMauSac(serviceMS.getAll());
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thất bại");
                }
            }
        } else {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            } else {
                KichThuoc kt = savesKT();
                String ma = tblChatLieu.getValueAt(index, 0).toString();
                if (serviceKT.sua(kt, ma) > 0) {
                    fillKichThuoc(serviceKT.getAll());
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnClearThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearThuocTinhActionPerformed
        // TODO add your handling code here:
        txtTenMa.setEnabled(true);
        txtTenMa.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnClearThuocTinhActionPerformed

    private void btnDau3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDau3ActionPerformed
        // TODO add your handling code here:
        if (rdMauSac.isSelected()) {
            trangMS = 1;
            fillMauSac(serviceMS.listPageMS(trangMS));
            lbSoTrang.setText(trangMS + " of " + soTrangMS);
        } else if (rdChatLieu.isSelected()) {
            trangCL = 1;
            fillChatLieu(serviceCl.listPageCL(trangCL));
            lbSoTrang.setText(trangCL + " of " + soTrangCL);
        } else {
            trangKT = 1;
            fillKichThuoc(serviceKT.listPageKT(trangKT));
            lbSoTrang.setText(trangKT + " of " + soTrangKT);
        }
    }//GEN-LAST:event_btnDau3ActionPerformed

    private void btnCuoi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoi3ActionPerformed
        // TODO add your handling code here:
        if (rdMauSac.isSelected()) {
            trangMS = soTrangMS;
            fillMauSac(serviceMS.listPageMS(trangMS));
            lbSoTrang.setText(trangMS + " of " + soTrangMS);
        } else if (rdChatLieu.isSelected()) {
            trangCL = soTrangCL;
            fillChatLieu(serviceCl.listPageCL(trangCL));
            lbSoTrang.setText(trangCL + " of " + soTrangCL);
        } else {
            trangKT = soTrangKT;
            fillKichThuoc(serviceKT.listPageKT(trangKT));
            lbSoTrang.setText(trangKT + " of " + soTrangKT);
        }
    }//GEN-LAST:event_btnCuoi3ActionPerformed

    private void btnLui3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui3ActionPerformed
        // TODO add your handling code here:
        if (rdMauSac.isSelected()) {
            if (trangMS > 1) {
                trangMS--;
                fillMauSac(serviceMS.listPageMS(trangMS));
                lbSoTrang.setText(trangMS + " of " + soTrangMS);
            }
        } else if (rdChatLieu.isSelected()) {
            if (trangCL > 1) {
                trangCL--;
                fillChatLieu(serviceCl.listPageCL(trangCL));
                lbSoTrang.setText(trangCL + " of " + soTrangCL);
            }
        } else {
            if (trangKT > 1) {
                trangKT--;
                fillChatLieu(serviceCl.listPageCL(trangCL));
                lbSoTrang.setText(trangCL + " of " + soTrangCL);
            }
        }
    }//GEN-LAST:event_btnLui3ActionPerformed

    private void btnTien3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien3ActionPerformed
        // TODO add your handling code here:
        if (rdMauSac.isSelected()) {
            if (trangMS < soTrangMS) {
                trangMS++;
                fillMauSac(serviceMS.listPageMS(trangMS));
                lbSoTrang.setText(trangMS + " of " + soTrangMS);
            }
        } else if (rdChatLieu.isSelected()) {
            if (trangCL < soTrangCL) {
                trangCL++;
                fillChatLieu(serviceCl.listPageCL(trangCL));
                lbSoTrang.setText(trangCL + " of " + soTrangCL);
            }
        } else {
            if (trangKT < soTrangKT) {
                trangKT++;
                fillKichThuoc(serviceKT.listPageKT(trangKT));
                lbSoTrang.setText(trangKT + " of " + soTrangKT);
            }
        }
    }//GEN-LAST:event_btnTien3ActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        // TODO add your handling code here:
        index = tblChatLieu.getSelectedRow();
        txtTenMa.setEnabled(false);
        detailThuocTinh(index);
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        index = tblMauSac.getSelectedRow();
        txtTenMa.setEnabled(false);
        detailThuocTinh(index);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        // TODO add your handling code here:
        index = tblKichThuoc.getSelectedRow();
        txtTenMa.setEnabled(false);
        detailThuocTinh(index);
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void rdChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdChatLieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdChatLieuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearThuocTinh;
    private javax.swing.JButton btnCuoi3;
    private javax.swing.JButton btnDau3;
    private javax.swing.JButton btnLui3;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnTien3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JTabbedPane pnlThuocTinh;
    private javax.swing.JRadioButton rdChatLieu;
    private javax.swing.JRadioButton rdConhang2;
    private javax.swing.JRadioButton rdHethang2;
    private javax.swing.JRadioButton rdKichThuoc;
    private javax.swing.JRadioButton rdMauSac;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTextField txtTenMa;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables
}
