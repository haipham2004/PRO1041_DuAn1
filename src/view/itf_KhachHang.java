/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.KhachHang;
import service.servicImp.KhachHangServiceImp;

/**
 *
 * @author Admin
 */
public class itf_KhachHang extends javax.swing.JInternalFrame {

    KhachHangServiceImp serviceKH = new KhachHangServiceImp();
    DefaultTableModel tblmol = new DefaultTableModel();
    DefaultComboBoxModel<KhachHang> cbxKhachHang = new DefaultComboBoxModel<>();
    int index = -1;
    int tongBanGhiKH, trangKH = 1, soTrangKH;

    /**
     * Creates new form itf_KhachHang
     */
    public itf_KhachHang() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI uI = (BasicInternalFrameUI) this.getUI();
        uI.setNorthPane(null);
        this.setSize(1300, 755);
        loadPageKH();
        sort();
        loadCboDiaChi(serviceKH.getAll());
        cboGioiTinh.setSelectedIndex(-1);
    }

    public void fillTableKH(List<KhachHang> list) {
        tblmol = (DefaultTableModel) tblKhachHang.getModel();
        tblmol.setRowCount(0);
        for (KhachHang item : list) {
            tblmol.addRow(new Object[]{
                item.getMaKhachHang(), item.getHoTen(), item.chiTietGioiTinh(),
                item.getSoDienThoai(), item.getNgaySinh(),
                item.getEmail(), item.getDiaChi()
            });
        }
    }

    public void loadCboDiaChi(List<KhachHang> list) {
        cbxKhachHang.removeAllElements();
        List<String> addedValues = new ArrayList<>();
        for (KhachHang item : list) {
            if (!addedValues.contains(item.getDiaChi())) {
                cbxKhachHang.addElement(item);
                addedValues.add(item.getDiaChi());
            }
        }
        cboDiaChi.setModel((ComboBoxModel) cbxKhachHang);
        cboDiaChi.setSelectedIndex(-1);
    }

    public void loadPageKH() {
        tongBanGhiKH = serviceKH.tongBanGhi();
        if (tongBanGhiKH % 4 == 0) {
            soTrangKH = tongBanGhiKH / 4;
        } else {
            soTrangKH = tongBanGhiKH / 4 + 1;
        }
        lblSoTrangKH.setText(trangKH + " of " + soTrangKH);
        fillTableKH(serviceKH.listPageKH(trangKH));
    }

    public void sort() {
        tblKhachHang.getTableHeader().addMouseListener(new MouseAdapter() {
            int sortType = 0; //Biến để theo dõi kiểu sắp xếp: 0 là tăng dần, 1 là giảm dần

            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tblKhachHang.columnAtPoint(e.getPoint());
                tblKhachHang.setAutoCreateRowSorter(true);
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblKhachHang.getModel());
                tblKhachHang.setRowSorter(sorter);
                // Kiểm tra kiểu sắp xếp hiện tại và cập nhật ngược lại
                if (sortType == 0) {
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(col, SortOrder.DESCENDING)));
                    sortType = 1; // Cập nhật kiểu sắp xếp ngược lại
                } else {
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(col, SortOrder.ASCENDING)));
                    sortType = 0; // Cập nhật kiểu sắp xếp ngược lại
                }
            }
        });
    }

    public void detailKH(int index) {
        txtMaKhachHang.setText(tblKhachHang.getValueAt(index, 0).toString());
        txtTenKhachHang.setText(tblKhachHang.getValueAt(index, 1).toString());
        txtSoDienThoai.setText(tblKhachHang.getValueAt(index, 3).toString());
        txtNgaySinh.setText(tblKhachHang.getValueAt(index, 4).toString());
        txtEmail.setText(tblKhachHang.getValueAt(index, 5).toString());
        txtDiaChi.setText(tblKhachHang.getValueAt(index, 6).toString());
        if (tblKhachHang.getValueAt(index, 2).toString().equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
    }

    public KhachHang getFormKH() {
        Boolean gioiTinh;
        String maKH = txtMaKhachHang.getText();
        String hoTen = txtTenKhachHang.getText();
        String sdt = txtSoDienThoai.getText();
        String diaChi = txtDiaChi.getText();
        String email = txtEmail.getText();
        Date ngaySinh = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ngaySinh = dateFormat.parse(txtNgaySinh.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rdNam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        return new KhachHang(maKH, hoTen, ngaySinh, sdt, email, gioiTinh, diaChi);
    }

    public boolean validateKH() {
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

        btngGioiTinh = new javax.swing.ButtonGroup();
        btngTrangThai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnThemTuExcel = new javax.swing.JButton();
        tbnXuatFile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboDiaChi = new javax.swing.JComboBox<>();
        lblSoTrangKH = new javax.swing.JLabel();
        btnDau = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnTien = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(940, 657));
        setPreferredSize(new java.awt.Dimension(1300, 700));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập trông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã khách hàng: ");

        jLabel2.setText("Tên khách hàng: ");

        jLabel3.setText("Giới tính: ");

        jLabel4.setText("Số điện thoại: ");

        btngGioiTinh.add(rdNam);
        rdNam.setText("Nam");

        btngGioiTinh.add(rdNu);
        rdNu.setText("Nữ");

        jLabel5.setText("Ngày sinh: ");

        jLabel7.setText("Địa chỉ: ");

        jLabel9.setText("Email: ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane3.setViewportView(txtDiaChi);

        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseClicked(evt);
            }
        });

        btnThemTuExcel.setText("Thêm từ Excel");

        tbnXuatFile.setText("Xuất file");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addGap(45, 45, 45)
                                .addComponent(rdNu)
                                .addGap(108, 108, 108)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThemTuExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tbnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(rdNam)
                                        .addComponent(rdNu))
                                    .addComponent(jLabel5))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemTuExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Giới tính", "SĐT", "Ngày sinh", "Email", "Địa chỉ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel8.setText("Giới tính: ");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", " " }));
        cboGioiTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGioiTinhItemStateChanged(evt);
            }
        });

        jLabel10.setText("Địa chỉ: ");

        cboDiaChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, 0, 177, Short.MAX_VALUE)
                    .addComponent(cboDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, 0, 177, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cboDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        lblSoTrangKH.setText("Số trang");

        btnDau.setText("<<");
        btnDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDauMouseClicked(evt);
            }
        });

        btnLui.setText("<");
        btnLui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuiMouseClicked(evt);
            }
        });

        btnTien.setText(">");
        btnTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTienMouseClicked(evt);
            }
        });

        btnCuoi.setText(">>");
        btnCuoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCuoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(btnDau)
                .addGap(33, 33, 33)
                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lblSoTrangKH)
                .addGap(44, 44, 44)
                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDau)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLui)
                        .addComponent(lblSoTrangKH))
                    .addComponent(btnTien)
                    .addComponent(btnCuoi))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        index = tblKhachHang.getSelectedRow();
        this.detailKH(index);
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
        KhachHang kh = this.getFormKH();
        if (serviceKH.getOne(kh.getMaKhachHang()) != null) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng trùng");
        } else {
            if (serviceKH.them(kh) > 0) {
                loadPageKH();
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại");
            }
        }

    }//GEN-LAST:event_btnThemMouseClicked

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked
        // TODO add your handling code here:
        index = tblKhachHang.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
        } else {
            KhachHang kh = this.getFormKH();
            String ma = tblKhachHang.getValueAt(index, 0).toString();
            if (serviceKH.sua(kh, ma) > 0) {
                loadPageKH();
                JOptionPane.showMessageDialog(this, "Sửa thông tin khách hàng thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thông tin khách hàng thất bại");
            }
        }
    }//GEN-LAST:event_btnCapNhatMouseClicked

    private void btnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtMaKhachHang.setText("");
        txtNgaySinh.setText("");
        txtSoDienThoai.setText("");
        txtTenKhachHang.setText("");
        txtTimKiem.setText("");
        btngGioiTinh.clearSelection();
        index = -1;
    }//GEN-LAST:event_btnLamMoiMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        if (!txtTimKiem.getText().equals("")) {
            String name = txtTimKiem.getText();
            fillTableKH(serviceKH.getList(name));
        } else {
            fillTableKH(serviceKH.getAll());
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDauMouseClicked
        // TODO add your handling code here:
        trangKH = 1;
        fillTableKH(serviceKH.listPageKH(trangKH));
        lblSoTrangKH.setText(trangKH + " of " + soTrangKH);
    }//GEN-LAST:event_btnDauMouseClicked

    private void btnCuoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCuoiMouseClicked
        // TODO add your handling code here:
        trangKH = soTrangKH;
        fillTableKH(serviceKH.listPageKH(trangKH));
        lblSoTrangKH.setText(trangKH + " of " + soTrangKH);
    }//GEN-LAST:event_btnCuoiMouseClicked

    private void btnTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTienMouseClicked
        // TODO add your handling code here:
        if (trangKH < soTrangKH) {
            trangKH++;
            fillTableKH(serviceKH.listPageKH(trangKH));
            lblSoTrangKH.setText(trangKH + " of " + soTrangKH);
        }
    }//GEN-LAST:event_btnTienMouseClicked

    private void btnLuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuiMouseClicked
        // TODO add your handling code here:
        if (trangKH > 1) {
            trangKH--;
            fillTableKH(serviceKH.listPageKH(trangKH));
            lblSoTrangKH.setText(trangKH + " of " + soTrangKH);
        }
    }//GEN-LAST:event_btnLuiMouseClicked

    private void cboGioiTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGioiTinhItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGioiTinhItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemTuExcel;
    private javax.swing.JButton btnTien;
    private javax.swing.ButtonGroup btngGioiTinh;
    private javax.swing.ButtonGroup btngTrangThai;
    private javax.swing.JComboBox<String> cboDiaChi;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblSoTrangKH;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JButton tbnXuatFile;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
