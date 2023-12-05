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
import model.ChiTietSanPham;
import model.DoiHangChiTiet;
import model.KichThuoc;
import model.MauSac;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.DoiHangChiTietServiceImp;
import service.servicImp.KichThuocServiceImp;
import service.servicImp.MauSacServiceImp;

/**
 *
 * @author Admin
 */
public class ChonChiTietSanPhamView extends javax.swing.JPanel {

    DefaultTableModel tblmol = new DefaultTableModel();
    DefaultComboBoxModel<KichThuoc> cbxKichThuocLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> cbxMauSacLoc = new DefaultComboBoxModel<>();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    DoiHangChiTietServiceImp serviceDHCT = new DoiHangChiTietServiceImp();
    DoiHangView doiHangView = new DoiHangView();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    static int indexCTSP = -1;
    static String tenSP = null;
    static String chatLieu = null;
    static int soLuongSP = 0;
    static String maDHCT = null;
    static String maCTSPCu = null;
    int trangCTSP = 1, soTrangCTSP, tongBanGhiCTSP, index = 0;

    /**
     * Creates new form ChonCTSP
     */
    public ChonChiTietSanPhamView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageCTSP();
        loadLocKichThuoc(serviceKT.getAll());
        loadLocMauSac(serviceMS.getAll());
        tenSP = doiHangView.getTenSP();
        chatLieu = doiHangView.getMaCL();
        soLuongSP = doiHangView.getSoLuongSP();
        maDHCT = doiHangView.getMaDHCT();
        maCTSPCu = doiHangView.getMaCTSPCu();
        doiHangView.setIndexHDDH(doiHangView.getIndexHDDH());
    }

    public void quayLaiDoiHang() {
        pnlTong.removeAll();
        pnlTong.add(new DoiHangView());
        pnlTong.repaint();
        pnlTong.revalidate();
    }

    public void fillTableChiTietSanPham(List<ChiTietSanPham> list) {
        tblmol = (DefaultTableModel) tblChiTietSanPham.getModel();
        tblmol.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            tblmol.addRow(new Object[]{
                chiTietSanPham.getMaChiTietSanPham(),
                chiTietSanPham.getSoLuong(), chiTietSanPham.getGia(),
                chiTietSanPham.getSanPham(),
                chiTietSanPham.getChatLieu(), chiTietSanPham.getMauSac(),
                chiTietSanPham.getKichThuoc(), chiTietSanPham.isTrangThai() ? "Còn hàng" : "Hết hàng",});
        }
    }

    public void loadLocMauSac(List<MauSac> list) {
        cbxMauSacLoc.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSacLoc.addElement(mauSac);
        }
        cboLocMau.setModel((ComboBoxModel) cbxMauSacLoc);
        cboLocMau.setSelectedIndex(-1);
    }

    public void loadLocKichThuoc(List<KichThuoc> list) {
        cbxKichThuocLoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuocLoc.addElement(kichThuoc);
        }
        cboLocKich.setModel((ComboBoxModel) cbxKichThuocLoc);
        cboLocKich.setSelectedIndex(-1);
    }

    public void loadPageCTSP() {
        String tenPage = new DoiHangView().getTenSP();
        chatLieu = new DoiHangView().getMaCL();
        tongBanGhiCTSP = serviceCTSP.tongBanGhi(tenPage);
        if (tongBanGhiCTSP % 20 == 0) {
            soTrangCTSP = tongBanGhiCTSP / 20;
        } else {
            soTrangCTSP = tongBanGhiCTSP / 20 + 1;
        }
        lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
        fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenPage, chatLieu));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTong = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnChonHoaDon = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        btnDau = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        btnTien = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cboLocKich = new javax.swing.JComboBox<>();
        cboLocMau = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();

        pnlTong.setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnChonHoaDon.setText("Chọn");
        btnChonHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonHoaDonMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Danh sách chi tiết sản phẩm");

        btnQuayLai.setText("Quay lại");
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLaiMouseClicked(evt);
            }
        });

        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Số lượng tồn", "Giá", "Tên sản phẩm", "Chất liệu", "Màu sắc ", "Kích thước", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblChiTietSanPham);

        btnDau.setText("Pre");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        btnLui.setText("Lùi");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        lbSoTrang.setText("Số trang");

        btnTien.setText("Tiến");
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        btnCuoi.setText("Next");
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cboLocKich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocKich.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kích thước", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cboLocKich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocKichMouseClicked(evt);
            }
        });
        cboLocKich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocKichActionPerformed(evt);
            }
        });

        cboLocMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocMau.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Màu sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cboLocMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocMauMouseClicked(evt);
            }
        });
        cboLocMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocMauActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboLocMau, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(cboLocKich, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboLocKich, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(cboLocMau, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lý do đổi hàng"));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChonHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(btnDau)
                        .addGap(33, 33, 33)
                        .addComponent(btnLui)
                        .addGap(41, 41, 41)
                        .addComponent(lbSoTrang)
                        .addGap(44, 44, 44)
                        .addComponent(btnTien)
                        .addGap(38, 38, 38)
                        .addComponent(btnCuoi)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChonHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuoi)
                    .addComponent(btnTien)
                    .addComponent(lbSoTrang)
                    .addComponent(btnLui)
                    .addComponent(btnDau))
                .addGap(51, 51, 51))
        );

        pnlTong.add(jPanel2, "card2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1181, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );

        pnlTong.add(jPanel4, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonHoaDonMouseClicked
        // TODO add your handling code here:
        if (indexCTSP != -1) {
            String input = JOptionPane.showInputDialog(this, "Mời nhập số lượng: ");
            if (input == null || input.isEmpty()) {
                return;
            }
            try {
                int soLuongDoi = Integer.parseInt(input);
                int soLuongTon = Integer.parseInt(tblChiTietSanPham.getValueAt(indexCTSP, 1).toString());
                if (soLuongDoi > soLuongSP) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng nhập lại");
                    return;
                } else if (soLuongDoi > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng nhập lại");
                    return;
                } else if (soLuongDoi < 1) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng nhập lại");
                    return;
                }
                if (maCTSPCu.equals(tblChiTietSanPham.getValueAt(indexCTSP, 0).toString())) {
                    JOptionPane.showMessageDialog(this, "Không thể đổi lại hàng khách đã mua, vui lòng kiểm tra lại");
                    return;
                } else {
                    String maCTSP = tblChiTietSanPham.getValueAt(indexCTSP, 0).toString();
                    ChiTietSanPham ctsp = new ChiTietSanPham(maCTSP);
                    String moTa = txtMoTa.getText();
                    DoiHangChiTiet dhct = new DoiHangChiTiet(maDHCT, ctsp, soLuongDoi, moTa);
                    serviceDHCT.sua(dhct, maDHCT);
                    serviceCTSP.tangSoLuong(soLuongDoi, maCTSPCu);
                    serviceCTSP.giamSoLuong(soLuongDoi, maCTSP);
                    quayLaiDoiHang();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn nhập sai định dạng");
                return;
            }
        }else{
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để đổi");
            return;
        }
    }//GEN-LAST:event_btnChonHoaDonMouseClicked

    private void btnQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMouseClicked
        // TODO add your handling code here:
        quayLaiDoiHang();
    }//GEN-LAST:event_btnQuayLaiMouseClicked

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        indexCTSP = tblChiTietSanPham.getSelectedRow();
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        // TODO add your handling code here:
        trangCTSP = 1;
        fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenSP, chatLieu));
        lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        // TODO add your handling code here:
        if (trangCTSP > 1) {
            trangCTSP--;
            fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenSP, chatLieu));
            lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        // TODO add your handling code here:
        if (trangCTSP < soTrangCTSP) {
            trangCTSP++;
            fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenSP, chatLieu));
            lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        // TODO add your handling code here:
        trangCTSP = soTrangCTSP;
        fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenSP, chatLieu));
        lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void cboLocKichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocKichMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocKichMouseClicked

    private void cboLocMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocMauMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocMauMouseClicked

    private void cboLocKichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocKichActionPerformed
        // TODO add your handling code here:
        String name2;
        String name3;
        if (cboLocKich.getSelectedIndex() != -1) {
            name2 = cboLocKich.getSelectedItem().toString();
            if (cboLocMau.getSelectedIndex() == -1) {
                fillTableChiTietSanPham(serviceCTSP.getListLocMauSacOrKichThuoc(tenSP, chatLieu, "KT.TenKichThuoc", name2));
                lbSoTrang.setText(1 + " of " + 1);
            } else {
                name3 = cboLocMau.getSelectedItem().toString();
                fillTableChiTietSanPham(serviceCTSP.getListLoc(tenSP, chatLieu, name3, name2));
                lbSoTrang.setText(1 + " of " + 1);
            }
        } else {
            loadPageCTSP();
        }
    }//GEN-LAST:event_cboLocKichActionPerformed

    private void cboLocMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocMauActionPerformed
        // TODO add your handling code here:

        String name2;
        String name3;
        if (cboLocMau.getSelectedIndex() != -1) {
            name3 = cboLocMau.getSelectedItem().toString();
            if (cboLocKich.getSelectedIndex() == -1) {
                fillTableChiTietSanPham(serviceCTSP.getListLocMauSacOrKichThuoc(tenSP, chatLieu, "MS.TenMauSac", name3));
                lbSoTrang.setText(1 + " of " + 1);
            } else {
                name2 = cboLocKich.getSelectedItem().toString();
                fillTableChiTietSanPham(serviceCTSP.getListLoc(tenSP, chatLieu, name3, name2));
                lbSoTrang.setText(1 + " of " + 1);
            }
        } else {
            loadPageCTSP();
        }
    }//GEN-LAST:event_cboLocMauActionPerformed

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        cboLocKich.setSelectedIndex(-1);
        cboLocMau.setSelectedIndex(-1);
    }//GEN-LAST:event_btnResetMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonHoaDon;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTien;
    private javax.swing.JComboBox<String> cboLocKich;
    private javax.swing.JComboBox<String> cboLocMau;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTextArea txtMoTa;
    // End of variables declaration//GEN-END:variables
}