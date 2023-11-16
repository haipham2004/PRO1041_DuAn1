/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.KichThuoc;
import model.MauSac;
import model.SanPham;
import service.servicImp.ChiTietSanPhamServiceImp;
import javax.swing.JTable;
import model.HoaDon;
import service.servicImp.HoaDonServiceImp;

/**
 *
 * @author Admin
 */
public class BanHangView extends javax.swing.JPanel {

    DefaultTableModel molCTSP = new DefaultTableModel();
    DefaultTableModel molGH = new DefaultTableModel();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    static int indexHoaDonCho = -1;
    int index = -1;
    HoaDonServiceImp serviceHD = new HoaDonServiceImp();
    DefaultTableModel molHDC = new DefaultTableModel();
    AdamStoreView adamStoreView = new AdamStoreView();

    int so = serviceHD.countHoaDon();

    /**
     * Creates new form BanHangView
     */
    public BanHangView() {
        initComponents();
        this.setSize(1300, 755);
        fillTableChiTietSanPham(serviceCTSP.getAll());
        fillTableHDC(serviceHD.getHoaDonCho());
    }

    //Quân
    public String maTangTuDong(String HD) {
        so++;
        String maHD = HD + String.format("%04d", so);;
        return maHD;
    }

    public void fillTableHDC(List<HoaDon> list) {
        molHDC = (DefaultTableModel) tblHoaDonCho.getModel();
        molHDC.setRowCount(0);
        for (HoaDon item : list) {
            molHDC.addRow(new Object[]{
                this.tblHoaDonCho.getRowCount() + 1, item.getMaHoaDon(), item.getNhanVien().getHoTen(),
                item.getNgayTao(), item.chiTietTrangThai()
            });
        }
    }

    public void fillTableHDC2() {
        DangNhapView dangNhapView = new DangNhapView();
        String maHD = maTangTuDong("HD");
        LocalDate ngayTao = LocalDate.now();
        molHDC.addRow(new Object[]{
            this.tblHoaDonCho.getRowCount() + 1, maHD, dangNhapView.getTenNV(), ngayTao, "Chờ thanh toán"
        });
    }

//    public void luuGioHangVaoFile(int indexHoaDonCho, String maHD) {
//        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("invoice1.ser"))) {
//            ChiTietSanPham gioHang = new ChiTietSanPham(FILE_PATH, sanPham, mauSac, chatLieu, kichThuoc, so, so, true);
//            oos.writeObject(invoice1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void luuGioHangVaoFile(String maHD, String parentDirectory, String newDirectoryName) {
        molGH = (DefaultTableModel) tblGioHang.getModel();
        // Tạo đường dẫn đến thư mục cha
        String parentPath = parentDirectory + File.separator;

        // Tạo đối tượng File để đại diện cho thư mục cha
        File parentDir = new File(parentPath);
        if (!parentDir.exists()) {
            // Nếu thư mục cha không tồn tại, tạo mới
            parentDir.mkdirs();
        }

        // Tạo đối tượng File để đại diện cho thư mục con với tên mới
        File childDir = new File(parentPath + newDirectoryName);
        if (!childDir.exists()) {
            // Nếu thư mục con không tồn tại, tạo mới
            childDir.mkdirs();
        }

        String fileName = "GioHang_" + maHD + ".csv";

        // Tạo đường dẫn đến tệp tin trong thư mục con
        String filePath = childDir.getPath() + File.separator + fileName;

        try ( FileWriter fileWriter = new FileWriter(filePath)) {
            // Ghi tiêu đề cột vào tệp tin
            for (int i = 0; i < molGH.getColumnCount(); i++) {
                fileWriter.append(molGH.getColumnName(i));
                if (i < molGH.getColumnCount() - 1) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");

            // Ghi dữ liệu từ mô hình vào tệp tin
            for (int row = 0; row < molGH.getRowCount(); row++) {
                for (int col = 0; col < molGH.getColumnCount(); col++) {
                    fileWriter.append(molGH.getValueAt(row, col).toString());
                    if (col < molGH.getColumnCount() - 1) {
                        fileWriter.append(",");
                    }
                }
                fileWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTableDataFromFile(String directory, String fileName) {
        molGH = (DefaultTableModel) tblGioHang.getModel();
        molGH.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

        // Tạo đường dẫn đến tệp tin
        String filePath = directory + "/" + fileName;

        try ( BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                molGH.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hải
    public void fillTableChiTietSanPham(List<ChiTietSanPham> list) {
        molCTSP = (DefaultTableModel) tblChiTietSanPham.getModel();
        molCTSP.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            molCTSP.addRow(new Object[]{
                chiTietSanPham.getMaChiTietSanPham(),
                chiTietSanPham.getSoLuong(), chiTietSanPham.getGia(),
                chiTietSanPham.getSanPham(),
                chiTietSanPham.getChatLieu(), chiTietSanPham.getMauSac(),
                chiTietSanPham.getKichThuoc()
            });
        }
    }

    public void fillTableGioHang(List<ChiTietSanPham> list) {
        molGH = (DefaultTableModel) tblChiTietSanPham.getModel();
        molGH.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            molGH.addRow(new Object[]{
                chiTietSanPham.getMaChiTietSanPham(),
                chiTietSanPham.getSoLuong(), chiTietSanPham.getGia(),
                chiTietSanPham.getSoLuong() * chiTietSanPham.getGia(),
                chiTietSanPham.getSanPham().getTenSanPham()
            });
        }
    }

    // Hải
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnQR = new javax.swing.JButton();
        btnNhapMaSPCT = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnXoaTatCaSP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        btnThemGioHang = new javax.swing.JButton();
        txtTimKiemCTSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnTaoHoaDonCho = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên nhân viên", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonCho);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Số lượng", "Giá", "Thành tiền", "TênSP"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        btnQR.setText("Quét QR");

        btnNhapMaSPCT.setText("Nhập mã");

        btnXoaSP.setText("Xóa sản phẩm");

        btnXoaTatCaSP.setText("Xóa tất cả");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhapMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaTatCaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTatCaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Số lượng tồn", "Giá", "Tên SP", "Chất liệu", "Màu sắc", "Kích thước"
            }
        ));
        tblChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietSanPham);

        btnThemGioHang.setText("Thêm vào giỏ hàng");
        btnThemGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGioHangActionPerformed(evt);
            }
        });

        txtTimKiemCTSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemCTSPKeyReleased(evt);
            }
        });

        jLabel1.setText("Nhập tên sản phẩm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimKiemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnTaoHoaDonCho.setText("Tạo hóa đơn");
        btnTaoHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoHoaDonChoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDonCho)
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnTaoHoaDonCho)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 247, Short.MAX_VALUE)))
                .addGap(50, 50, 50)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGioHangActionPerformed
        int indexs = tblChiTietSanPham.getSelectedRow();
        molGH = (DefaultTableModel) tblGioHang.getModel();
        int indexGioHang = -1;
        if (indexs == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm");
            return;
        }
        String input = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
        if (input == null || input.isEmpty()) {
            return;
        }
        try {
            int soLuongMua = Integer.parseInt(input);
            int soLuongTon = Integer.parseInt(tblChiTietSanPham.getValueAt(indexs, 1).toString());
            if (soLuongMua > soLuongTon) {
                JOptionPane.showMessageDialog(this, "Số lượng không đủ");
                return;
            }
            String ma = tblChiTietSanPham.getValueAt(indexs, 0).toString();
            int soLuong = Integer.parseInt(tblChiTietSanPham.getValueAt(indexs, 1).toString());
            double gia = Double.parseDouble(tblChiTietSanPham.getValueAt(indexs, 2).toString());
            String ten = tblChiTietSanPham.getValueAt(indexs, 3).toString();
            double thanhTien = gia * soLuongMua;

            molGH.insertRow(0, new Object[]{
                ma, soLuongMua, gia, thanhTien, ten
            });

//            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
//                if (tblGioHang.getValueAt(i, 0).equals(ma)) {
//                    indexGioHang = i;
//                    break;
//                }
//            }
//            if (indexGioHang != -1) {
//                String check = tblGioHang.getValueAt(indexGioHang, 0).toString();
//                if (ma.equals(check)) {
//                    int soLuongMuaTruocKhiSua = Integer.parseInt(String.valueOf(tblGioHang.getValueAt(indexGioHang, 1).toString()));
//                    int soLuongMuaSau = Integer.parseInt(input) + Integer.parseInt(String.valueOf(tblGioHang.getValueAt(indexGioHang, 1).toString()));
//                    double thanhTienMuaSau = Integer.parseInt(tblGioHang.getValueAt(indexGioHang, 1).toString()) * Double.parseDouble(tblGioHang.getValueAt(indexGioHang, 2).toString());
//                    molGH.insertRow(0, new Object[]{
//                        ma, soLuongMuaSau, gia, thanhTienMuaSau, ten
//                    });
//                } else {
//                    JOptionPane.showMessageDialog(this, "Haha");
//                }
//            }
            fillTableChiTietSanPham(serviceCTSP.getAll());
            soLuongTon = soLuongTon - soLuongMua;
            tblChiTietSanPham.setValueAt(soLuongTon, indexs, 1);

        } catch (NumberFormatException e) {
            return;
        }

    }//GEN-LAST:event_btnThemGioHangActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();


    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void txtTimKiemCTSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCTSPKeyReleased
        // TODO add your handling code here:
        if (!txtTimKiemCTSP.getText().equals("")) {
            String name = txtTimKiemCTSP.getText();
            fillTableChiTietSanPham(serviceCTSP.getList(name));
        }
    }//GEN-LAST:event_txtTimKiemCTSPKeyReleased

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnTaoHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoHoaDonChoMouseClicked
        // TODO add your handling code here:
        fillTableHDC2();
//        tblHoaDonCho.setRowSelectionInterval(1, 1);
        molGH = (DefaultTableModel) tblGioHang.getModel();
        if (molGH.getRowCount() > 0) {
            molGH.setRowCount(0);
        }

    }//GEN-LAST:event_btnTaoHoaDonChoMouseClicked

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        // TODO add your handling code here:
//        indexHoaDonCho = tblHoaDonCho.getSelectedRow();
//        String maHD = tblHoaDonCho.getValueAt(indexHoaDonCho, 1).toString();
//        String parentDirectory = "D:\\FPT Polytechnic\\DuAn1\\PRO1041_DuAn1";
//        String newDirectoryName = "GioHang";
//        luuGioHangVaoFile(maHD, parentDirectory, newDirectoryName);
    }//GEN-LAST:event_tblHoaDonChoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNhapMaSPCT;
    private javax.swing.JButton btnQR;
    private javax.swing.JButton btnTaoHoaDonCho;
    private javax.swing.JButton btnThemGioHang;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaTatCaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtTimKiemCTSP;
    // End of variables declaration//GEN-END:variables
}
