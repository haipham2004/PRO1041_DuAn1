/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import service.servicImp.NhanVienServiceImp;

/**
 *
 * @author Admin
 */
public class itf_NhanVien extends javax.swing.JInternalFrame {

    DefaultTableModel mol = new DefaultTableModel();
    DefaultTableModel mol1 = new DefaultTableModel();
    DefaultComboBoxModel<NhanVien> cbxNhanVien = new DefaultComboBoxModel<>();
    NhanVienServiceImp serviceNV = new NhanVienServiceImp();
    String anhNV;
    int index = -1;

    /**
     * Creates new form itf_NhanVien
     */
    public itf_NhanVien() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI uI = (BasicInternalFrameUI) this.getUI();
        uI.setNorthPane(null);
        this.setSize(950, 655);
        fillTableNV(serviceNV.getAll());
        loadCboDiaChi(serviceNV.getAll());
        String today = toString().valueOf(LocalDate.now());
        txtNgayVaoLam.setText(today);
    }

    public void detailNV(int index) {
        txtMaNV.setText(tblNV0.getValueAt(index, 0).toString());
        txtMaTK.setText(tblNV0.getValueAt(index, 1).toString());
        txtHoTen.setText(tblNV0.getValueAt(index, 2).toString());
        if (tblNV0.getValueAt(index, 3).toString().equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(tblNV0.getValueAt(index, 4).toString());
        txtSDT.setText(tblNV0.getValueAt(index, 5).toString());
        txtCCCD.setText(tblNV0.getValueAt(index, 6).toString());
        txtNgayVaoLam.setText(tblNV0.getValueAt(index, 7).toString());
        rdoNghiViec.setSelected(true);
        ImageIcon icon = new ImageIcon(tblNV0.getValueAt(index, 8).toString());
        Image image = icon.getImage().getScaledInstance(146, 206, Image.SCALE_SMOOTH);
        lblAnhNV.setIcon(new ImageIcon(image));
    }

    public void detailNV1(int index) {
        txtMaNV.setText(tblNV1.getValueAt(index, 0).toString());
        txtMaTK.setText(tblNV1.getValueAt(index, 1).toString());
        txtHoTen.setText(tblNV1.getValueAt(index, 2).toString());
        if (tblNV1.getValueAt(index, 3).toString().equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(tblNV1.getValueAt(index, 4).toString());
        txtSDT.setText(tblNV1.getValueAt(index, 5).toString());
        txtCCCD.setText(tblNV1.getValueAt(index, 6).toString());
        txtNgayVaoLam.setText(tblNV1.getValueAt(index, 7).toString());
        rdoDangLamViec.setSelected(true);
        ImageIcon icon = new ImageIcon(tblNV1.getValueAt(index, 8).toString());
        Image image = icon.getImage().getScaledInstance(146, 206, Image.SCALE_SMOOTH);
        lblAnhNV.setIcon(new ImageIcon(image));
    }

    public void fillTableNV(List<NhanVien> list) {
        mol = (DefaultTableModel) tblNV0.getModel();
        mol1 = (DefaultTableModel) tblNV1.getModel();
        mol.setRowCount(0);
        mol1.setRowCount(0);
        for (NhanVien nv : list) {
            if (!nv.isTrangThai()) {
                mol.addRow(new Object[]{
                    nv.getMaNhanVien(), nv.getTaiKhoan().getMaTaiKhoan(), nv.getHoTen(),
                    nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(), nv.getSoDienThoai(),
                    nv.getCCCD(), nv.getNgayVaoLam(), nv.getAnh()
                });
            } else {
                mol1.addRow(new Object[]{
                    nv.getMaNhanVien(), nv.getTaiKhoan().getMaTaiKhoan(), nv.getHoTen(),
                    nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(), nv.getSoDienThoai(),
                    nv.getCCCD(), nv.getNgayVaoLam(), nv.getAnh()
                });
            }
        }
    }

    public void loadCboDiaChi(List<NhanVien> list) {
        cbxNhanVien.removeAllElements();
        List<String> addedValues = new ArrayList<>();
        for (NhanVien nv : list) {
            if (!addedValues.contains(nv.getDiaChi())) {
                cbxNhanVien.addElement(nv);
                addedValues.add(nv.getDiaChi());
            }
        }
        cboDiaChi.setModel((ComboBoxModel) cbxNhanVien);
    }

    public NhanVien getFormNV() {
        String maNV = txtMaNV.getText();
        String maTK = txtMaTK.getText();
        String hoTen = txtHoTen.getText();
        Boolean gt = rdoNam.isSelected() ? true : false;
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        String cccd = txtCCCD.getText();
        String ngay = txtNgayVaoLam.getText();
        Date ngayVaoLam = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ngayVaoLam = dateFormat.parse(ngay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean trangThai = rdoDangLamViec.isSelected() ? true : false;
        String anh = anhNV;
        return new NhanVien(maNV, maTK, hoTen, gt, diaChi, sdt, cccd, ngayVaoLam, trangThai, anh);
    }

    public void themNV() {
        NhanVien nv = this.getFormNV();
        try {
            serviceNV.them(nv);
            fillTableNV(serviceNV.getAll());
            loadCboDiaChi(serviceNV.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinhNV = new javax.swing.ButtonGroup();
        btnTrangThaiNV = new javax.swing.ButtonGroup();
        pnlChiTietNhanVien = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtMaTK = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtNgayVaoLam = new javax.swing.JTextField();
        rdoDangLamViec = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnMoiNV = new javax.swing.JButton();
        lblAnhNV = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        cboDiaChi = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        txtTimNV = new javax.swing.JTextField();
        btnTimNV = new javax.swing.JButton();
        cboTimKiemNV = new javax.swing.JComboBox<>();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNV1 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNV0 = new javax.swing.JTable();

        jPanel61.setBorder(javax.swing.BorderFactory.createTitledBorder("Thiết lập thông tin"));

        jLabel27.setText("Mã NV");

        jLabel28.setText("Mã TK");

        jLabel29.setText("Họ tên");

        jLabel30.setText("Giới tính");

        jLabel31.setText("Địa chỉ");

        btgGioiTinhNV.add(rdoNam);
        rdoNam.setText("Nam");

        btgGioiTinhNV.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel32.setText("SĐT");

        jLabel33.setText("CCCD");

        jLabel34.setText("Ngày vào làm");

        jLabel35.setText("Trạng thái");

        jLabel36.setText("Ảnh");

        txtNgayVaoLam.setEditable(false);

        btnTrangThaiNV.add(rdoDangLamViec);
        rdoDangLamViec.setText("Đang làm việc");

        btnTrangThaiNV.add(rdoNghiViec);
        rdoNghiViec.setText("Nghỉ việc");

        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setText("Sửa");

        btnMoiNV.setText("Mới");
        btnMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiNVActionPerformed(evt);
            }
        });

        lblAnhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNVMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel30)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNu))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(txtMaNV))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel61Layout.createSequentialGroup()
                                        .addComponent(rdoDangLamViec)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNghiViec))
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayVaoLam))))
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(btnThemNV)
                                .addGap(18, 18, 18)
                                .addComponent(btnSuaNV)
                                .addGap(18, 18, 18)
                                .addComponent(btnMoiNV))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(txtNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(jLabel35)
                            .addComponent(rdoDangLamViec)
                            .addComponent(rdoNghiViec))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNV)
                            .addComponent(btnSuaNV)
                            .addComponent(btnMoiNV)))
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Bộ lọc"));

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        cboDiaChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(cboDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        btnTimNV.setText("Tìm");

        cboTimKiemNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã NV", "Họ tên", "SĐT", "CCCD" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtTimNV)
                .addGap(18, 18, 18)
                .addComponent(btnTimNV)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimNV)
                    .addComponent(cboTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tblNV1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Mã TK", "Họ tên", "Giới tính", "Địa chỉ", "SĐT", "CCCD", "Ngày vào làm", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNV1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNV1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Đang làm việc", jPanel11);

        tblNV0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Mã TK", "Họ tên", "Giới tính", "Địa chỉ", "SĐT", "CCCD", "Ngày vào làm", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNV0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNV0MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblNV0);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Nghỉ việc", jPanel14);

        javax.swing.GroupLayout pnlChiTietNhanVienLayout = new javax.swing.GroupLayout(pnlChiTietNhanVien);
        pnlChiTietNhanVien.setLayout(pnlChiTietNhanVienLayout);
        pnlChiTietNhanVienLayout.setHorizontalGroup(
            pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTabbedPane3)
        );
        pnlChiTietNhanVienLayout.setVerticalGroup(
            pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 867, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChiTietNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChiTietNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        this.themNV();
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiNVActionPerformed
        String today = toString().valueOf(LocalDate.now());
        txtNgayVaoLam.setText(today);
        txtMaNV.setText("");
        txtMaTK.setText("");
        txtHoTen.setText("");
        btgGioiTinhNV.clearSelection();
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtCCCD.setText("");
        rdoDangLamViec.setSelected(true);
        lblAnhNV.setIcon(null);
        btnThemNV.setEnabled(true);
        txtMaNV.setEnabled(true);
        tblNV1.clearSelection();
        tblNV0.clearSelection();
    }//GEN-LAST:event_btnMoiNVActionPerformed

    private void lblAnhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNVMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
                Image originalImage = originalIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(146, 206, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                lblAnhNV.setIcon(resizedIcon);
                anhNV = selectedFile.getAbsolutePath();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_lblAnhNVMouseClicked

    private void tblNV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNV1MouseClicked
        index = tblNV1.getSelectedRow();
        detailNV1(index);
        btnThemNV.setEnabled(false);
        txtMaNV.setEnabled(false);
    }//GEN-LAST:event_tblNV1MouseClicked

    private void tblNV0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNV0MouseClicked
        index = tblNV0.getSelectedRow();
        detailNV(index);
        btnThemNV.setEnabled(false);
        txtMaNV.setEnabled(false);
    }//GEN-LAST:event_tblNV0MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinhNV;
    private javax.swing.JButton btnMoiNV;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnTimNV;
    private javax.swing.ButtonGroup btnTrangThaiNV;
    private javax.swing.JComboBox<String> cboDiaChi;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboTimKiemNV;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JPanel pnlChiTietNhanVien;
    private javax.swing.JRadioButton rdoDangLamViec;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNV0;
    private javax.swing.JTable tblNV1;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JTextField txtNgayVaoLam;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables
}
