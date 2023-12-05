/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DoiHang;
import model.DoiHangChiTiet;
import model.HoaDonChiTiet;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.DoiHangChiTietServiceImp;
import service.servicImp.DoiHangServiceImp;
import service.servicImp.HoaDonChiTietServiceImp;

/**
 *
 * @author Admin
 */
public class DoiHangView extends javax.swing.JPanel {

    DoiHangServiceImp serviceDH = new DoiHangServiceImp();
    HoaDonChiTietServiceImp serviceHDCT = new HoaDonChiTietServiceImp();
    DoiHangChiTietServiceImp serviceDHCT = new DoiHangChiTietServiceImp();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    DefaultTableModel tblmolHDDH = new DefaultTableModel();
    DefaultTableModel tblmolHDCT = new DefaultTableModel();
    DefaultTableModel tblmolDSDH = new DefaultTableModel();
    public static int indexHoaDonDoiHang = -1;
    public static int indexHoaDonChiTiet = -1;
    public static int indexDoiHangChiTiet = -1;
    public static String tenSP;
    public static String tenCL;
    public static int soLuongSP;
    public static String maDHCT;
    public static String maCTSPCu;
    int so = serviceDHCT.countDoiHangChiTiet();
    Random random = new Random();

    /**
     * Creates new form DoiHangView
     */
    public DoiHangView() {
        initComponents();
        this.setSize(1300, 755);
        loadTableHDDH(serviceDH.getAllDangDoiHang());
        saveIndex();
    }

    public void loadTableHDDH(List<DoiHang> list) {
        tblmolHDDH = (DefaultTableModel) tblHoaDonDoiHang.getModel();
        tblmolHDDH.setRowCount(0);
        for (DoiHang item : list) {
            tblmolHDDH.addRow(new Object[]{
                tblHoaDonDoiHang.getRowCount() + 1, item.getMaDoiHang(), item.getHoaDon().getMaHoaDon(),
                item.getHoaDon().getKhachHang().getHoTen(), item.getNhanVien().getHoTen(), item.getNgayDoiTra(),
                item.getTrangThai()
            });
        }
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        DoiHangView.tenSP = tenSP;
    }

    public int getIndexHDDH() {
        return indexHoaDonDoiHang;
    }

    public void setIndexHDDH(int indexHoaDonDoiHang) {
        DoiHangView.indexHoaDonDoiHang = indexHoaDonDoiHang;
    }

    public String getMaCTSPCu() {
        return maCTSPCu;
    }

    public void setMaCTSPCu(String maCTSPCu) {
        DoiHangView.maCTSPCu = maCTSPCu;
    }

    public String getMaDHCT() {
        return maDHCT;
    }

    public void setMaDHCT(String maDHCT) {
        DoiHangView.maDHCT = maDHCT;
    }

    public String getMaCL() {
        return tenCL;
    }

    public void setMaCL(String tenCL) {
        DoiHangView.tenCL = tenCL;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setMaCL(int soLuongSP) {
        DoiHangView.soLuongSP = soLuongSP;
    }

    public String maTangTuDong(String DHCT) {
        so++;
        String maTuDong = "";
        String chuHoa = "QWERTYUIOPASDFGHJKLZXCVBNM";
        char[] kyTu = new char[2];
        for (int i = 0; i < 2; i++) {
            kyTu[i] = chuHoa.charAt(random.nextInt(chuHoa.length()));
            maTuDong += kyTu[i];
        }
        String maDHCT = DHCT + String.format("%04d", so) + maTuDong;
        return maDHCT;
    }

    public void fillTableHoaDonChiTiet(List<HoaDonChiTiet> list) {
        tblmolHDCT = (DefaultTableModel) tblChiTietHoaDon.getModel();
        tblmolHDCT.setRowCount(0);
        for (HoaDonChiTiet item : list) {
            tblmolHDCT.addRow(new Object[]{tblChiTietHoaDon.getRowCount() + 1,
                item.getMaHDCT(), item.getCtsp().getSanPham().getTenSanPham(),
                item.getCtsp().getMaChiTietSanPham(), item.getSoLuong(),
                item.getDonGia()
            });
        }
    }

    public void showHoaDonChiTiet(int index) {
        String maHoadon = (String) tblHoaDonDoiHang.getValueAt(index, 2);
        fillTableHoaDonChiTiet(serviceHDCT.getHDCTFromHoaDon(maHoadon));
    }

    public void fillTableDanhSachDoiHang(List<DoiHangChiTiet> list) {
        tblmolDSDH = (DefaultTableModel) tblDanhSachDoiHang.getModel();
        tblmolDSDH.setRowCount(0);
        for (DoiHangChiTiet item : list) {
            tblmolDSDH.addRow(new Object[]{tblDanhSachDoiHang.getRowCount() + 1,
                item.getHoaDonChiTiet().getMaHDCT(),
                item.getHoaDonChiTiet().getCtsp().getSanPham().getTenSanPham(),
                item.getHoaDonChiTiet().getCtsp().getMaChiTietSanPham(),
                item.getChiTietSanPham().getMaChiTietSanPham(),
                item.getSoLuong(), item.getChiTietSanPham().getGia(), item.getMoTa()
            });
        }
    }

    public void showDoiHangChiTiet(int index) {
        String maDoiHang = (String) tblHoaDonDoiHang.getValueAt(index, 1);
        fillTableDanhSachDoiHang(serviceDHCT.getDHCTDangDoi(maDoiHang));
    }

    public void saveIndex() {
        if (indexHoaDonDoiHang != -1) {
            this.showHoaDonChiTiet(indexHoaDonDoiHang);
            this.showDoiHangChiTiet(indexHoaDonDoiHang);
            tblHoaDonDoiHang.setRowSelectionInterval(indexHoaDonDoiHang, indexHoaDonDoiHang);
            detailDH();
        }
    }

    public Double tongTienHoaDon() {
        int x = tblChiTietHoaDon.getRowCount();
        if (x == 0) {
            return 0.0;
        }
        Double sum = 0.0;
        for (int i = 0; i < x; i++) {
            int soLuong = Integer.parseInt(tblChiTietHoaDon.getValueAt(i, 4).toString());
            Double donGia = Double.parseDouble(tblChiTietHoaDon.getValueAt(i, 5).toString());
            sum += (soLuong * donGia);
        }
        return sum;
    }

    public Double tongTienDoiHang() {
        int x = tblDanhSachDoiHang.getRowCount();
        if (x == 0) {
            return 0.0;
        }
        Double sum = 0.0;
        for (int i = 0; i < x; i++) {
            int soLuong = Integer.parseInt(tblDanhSachDoiHang.getValueAt(i, 5).toString());
            Double donGia = Double.parseDouble(tblDanhSachDoiHang.getValueAt(i, 6).toString());
            sum += (soLuong * donGia);
        }
        return sum;
    }

    public int soLuongDoiHang() {
        int x = tblDanhSachDoiHang.getRowCount();
        if (x == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < x; i++) {
            sum += Integer.parseInt(tblDanhSachDoiHang.getValueAt(i, 5).toString());
        }
        return sum;
    }

    public int soLuongHoaDon() {
        int x = tblChiTietHoaDon.getRowCount();
        if (x == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < x; i++) {
            sum += Integer.parseInt(tblChiTietHoaDon.getValueAt(i, 4).toString());
        }
        return sum;
    }

    public String phanCach(Double x) {
        NumberFormat fm = NumberFormat.getNumberInstance(Locale.US);
        return fm.format(x);
    }

    public void detailDH() {
        txtMaHD.setText(tblHoaDonDoiHang.getValueAt(indexHoaDonDoiHang, 2).toString());
        txtMaKH.setText(tblHoaDonDoiHang.getValueAt(indexHoaDonDoiHang, 3).toString());
        txtTongTienHoaDon.setText(phanCach(tongTienHoaDon()));
        txtTongTienHangDoi.setText(phanCach(tongTienDoiHang()));
        txtTongSoLuongMua.setText(soLuongHoaDon() + "");
        txtTongSoLuongDoi.setText(soLuongDoiHang() + "");
    }

    public void clearDonDoiHang() {
        txtMaHD.setText("");
        txtMaKH.setText("");
        txtTongTienHoaDon.setText("");
        txtTongTienHangDoi.setText("");
        txtTongSoLuongMua.setText("");
        txtTongSoLuongDoi.setText("");
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
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonDoiHang = new javax.swing.JTable();
        btnThemHoaDonDoiHang = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnXacNhanDoiHang = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtTongSoLuongMua = new javax.swing.JTextField();
        txtTongSoLuongDoi = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTongTienHoaDon = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTongTienHangDoi = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachDoiHang = new javax.swing.JTable();
        btnThemVaoDSDH = new javax.swing.JButton();
        btnChonHang = new javax.swing.JButton();
        btnLamMoiDSDH = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();

        pnlTong.setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn đổi hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDonDoiHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã đổi hàng", "Mã hóa đơn", "Khách hàng", "Người tạo", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDonDoiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonDoiHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonDoiHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThemHoaDonDoiHang.setText("Thêm hóa đơn đổi hàng");
        btnThemHoaDonDoiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemHoaDonDoiHangMouseClicked(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn đổi hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnXacNhanDoiHang.setText("Xác nhận đổi hàng");
        btnXacNhanDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanDoiHangActionPerformed(evt);
            }
        });

        jLabel18.setText("Mã HĐ");

        jLabel20.setText("Mã KH");

        jLabel22.setText("Tổng số lượng mua");

        jLabel23.setText("Tổng số lượng đổi");

        txtMaHD.setEnabled(false);

        txtMaKH.setEnabled(false);

        txtTongSoLuongMua.setEnabled(false);

        txtTongSoLuongDoi.setEnabled(false);
        txtTongSoLuongDoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongSoLuongDoitienKhachNhap(evt);
            }
        });

        jLabel24.setText("Tổng tiền hóa đơn:");

        txtTongTienHoaDon.setEnabled(false);

        jLabel25.setText("Tổng tiền hàng đổi:");

        txtTongTienHangDoi.setEnabled(false);

        btnHuy.setText("Hủy");
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyMouseClicked(evt);
            }
        });
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXacNhanDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTienHangDoi, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTongSoLuongDoi, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(txtTongSoLuongMua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTienHoaDon))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTongSoLuongMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTongSoLuongDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTongTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTongTienHangDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXacNhanDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐCT", "Tên SP", "Mã CTSP", "Số lượng", "Đơn giá"
            }
        ));
        tblChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đổi hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachDoiHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐCT", "Tên SP", "Mã CTSP cũ", "Mã CTSP mới", "Số lượng", "Đơn giá", "Mô tả"
            }
        ));
        tblDanhSachDoiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachDoiHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachDoiHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThemVaoDSDH.setText("Thêm vào danh sách đổi hàng");
        btnThemVaoDSDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemVaoDSDHMouseClicked(evt);
            }
        });

        btnChonHang.setText("Chọn hàng muốn đổi");
        btnChonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonHangMouseClicked(evt);
            }
        });

        btnLamMoiDSDH.setText("Làm mới danh sách đổi hàng");
        btnLamMoiDSDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiDSDHMouseClicked(evt);
            }
        });
        btnLamMoiDSDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDSDHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemVaoDSDH, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnLamMoiDSDH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChonHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(884, 884, 884)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnThemHoaDonDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThemVaoDSDH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiDSDH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnThemHoaDonDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(268, Short.MAX_VALUE)))
        );

        pnlTong.add(jPanel4, "card2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1223, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );

        pnlTong.add(jPanel7, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiDSDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDSDHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiDSDHActionPerformed

    private void btnThemVaoDSDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemVaoDSDHMouseClicked
        // TODO add your handling code here:
        indexHoaDonDoiHang = tblHoaDonDoiHang.getSelectedRow();
        indexHoaDonChiTiet = tblChiTietHoaDon.getSelectedRow();
        maDHCT = maTangTuDong("DHCT");
        String maDH = tblHoaDonDoiHang.getValueAt(indexHoaDonDoiHang, 1).toString();
        String maHDCT = tblChiTietHoaDon.getValueAt(indexHoaDonChiTiet, 1).toString();
        DoiHang hd = new DoiHang(maDH);
        HoaDonChiTiet hdct = new HoaDonChiTiet(maHDCT);
        DoiHangChiTiet dhct = new DoiHangChiTiet(maDHCT, hd, hdct);
        serviceDHCT.them(dhct);
        showDoiHangChiTiet(indexHoaDonDoiHang);
    }//GEN-LAST:event_btnThemVaoDSDHMouseClicked

    private void tblDanhSachDoiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachDoiHangMouseClicked
        // TODO add your handling code here:
        indexDoiHangChiTiet = tblDanhSachDoiHang.getSelectedRow();
        String maHDCT = tblDanhSachDoiHang.getValueAt(indexDoiHangChiTiet, 1).toString();
        tenSP = tblDanhSachDoiHang.getValueAt(indexDoiHangChiTiet, 2).toString();
        maCTSPCu = tblDanhSachDoiHang.getValueAt(indexDoiHangChiTiet, 3).toString();
        tenCL = serviceCTSP.getTenCL(maCTSPCu);
        maDHCT = serviceDHCT.getMaDHCT(maHDCT);
        soLuongSP = serviceHDCT.getSoLuongFromHDCT(maHDCT);
    }//GEN-LAST:event_tblDanhSachDoiHangMouseClicked

    private void tblChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonMouseClicked
        // TODO add your handling code here:
        indexHoaDonChiTiet = tblChiTietHoaDon.getSelectedRow();
    }//GEN-LAST:event_tblChiTietHoaDonMouseClicked

    private void txtTongSoLuongDoitienKhachNhap(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongSoLuongDoitienKhachNhap

    }//GEN-LAST:event_txtTongSoLuongDoitienKhachNhap

    private void btnXacNhanDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanDoiHangActionPerformed
        if (indexHoaDonDoiHang == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn để đổi hàng");
        } else {
            tblmolHDCT = (DefaultTableModel) tblChiTietHoaDon.getModel();
            tblmolDSDH = (DefaultTableModel) tblDanhSachDoiHang.getModel();
            String maDH = tblHoaDonDoiHang.getValueAt(indexHoaDonDoiHang, 1).toString();
            serviceDH.capNhatTrangThai(maDH);
            serviceDHCT.capNhatTrangThai(maDH);
            loadTableHDDH(serviceDH.getAllDangDoiHang());
            tblmolHDCT.setRowCount(0);
            tblmolDSDH.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Đổi hàng thành công");
        }
    }//GEN-LAST:event_btnXacNhanDoiHangActionPerformed

    private void btnThemHoaDonDoiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemHoaDonDoiHangMouseClicked
        // TODO add your handling code here:

        pnlTong.removeAll();
        pnlTong.add(new ChonHoaDonView());
        pnlTong.repaint();
        pnlTong.revalidate();

    }//GEN-LAST:event_btnThemHoaDonDoiHangMouseClicked

    private void tblHoaDonDoiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonDoiHangMouseClicked
        // TODO add your handling code here:
        indexHoaDonDoiHang = tblHoaDonDoiHang.getSelectedRow();
        this.showHoaDonChiTiet(indexHoaDonDoiHang);
        this.showDoiHangChiTiet(indexHoaDonDoiHang);
        detailDH();
    }//GEN-LAST:event_tblHoaDonDoiHangMouseClicked

    private void btnChonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonHangMouseClicked
        // TODO add your handling code here:
        indexDoiHangChiTiet = tblDanhSachDoiHang.getSelectedRow();
        int soLuong = Integer.parseInt(tblDanhSachDoiHang.getValueAt(indexDoiHangChiTiet, 5).toString());
        if (soLuong == 0) {
            pnlTong.removeAll();
            pnlTong.add(new ChonChiTietSanPhamView());
            pnlTong.repaint();
            pnlTong.revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã đc đổi, vui lòng làm mới danh sách nếu muốn đổi lại");
        }
    }//GEN-LAST:event_btnChonHangMouseClicked

    private void btnLamMoiDSDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiDSDHMouseClicked
        // TODO add your handling code here:
        for (int i = 0; i < tblDanhSachDoiHang.getRowCount(); i++) {
            int soLuong = Integer.parseInt(tblDanhSachDoiHang.getValueAt(i, 5).toString());
            if (soLuong != 0) {
                String maCTSPCu = tblDanhSachDoiHang.getValueAt(i, 3).toString();
                String maCTSPMoi = tblDanhSachDoiHang.getValueAt(i, 4).toString();
                serviceCTSP.tangSoLuong(soLuong, maCTSPMoi);
                serviceCTSP.giamSoLuong(soLuong, maCTSPCu);
            }
        }
        String maDH = tblHoaDonDoiHang.getValueAt(indexHoaDonDoiHang, 1).toString();
        serviceDHCT.xoa(maDH);
        showDoiHangChiTiet(indexHoaDonDoiHang);
    }//GEN-LAST:event_btnLamMoiDSDHMouseClicked

    private void btnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMouseClicked
        // TODO add your handling code here:
        if (indexHoaDonDoiHang == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn để hủy");
        } else {
            tblmolHDCT = (DefaultTableModel) tblChiTietHoaDon.getModel();
            tblmolDSDH = (DefaultTableModel) tblDanhSachDoiHang.getModel();
            String maDH = tblHoaDonDoiHang.getValueAt(indexHoaDonDoiHang, 1).toString();
            serviceDH.huyDonDoiHang(maDH);
            loadTableHDDH(serviceDH.getAllDangDoiHang());
            tblmolHDCT.setRowCount(0);
            tblmolDSDH.setRowCount(0);
            clearDonDoiHang();
        }
    }//GEN-LAST:event_btnHuyMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLamMoiDSDH;
    private javax.swing.JButton btnThemHoaDonDoiHang;
    private javax.swing.JButton btnThemVaoDSDH;
    private javax.swing.JButton btnXacNhanDoiHang;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblDanhSachDoiHang;
    private javax.swing.JTable tblHoaDonDoiHang;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTongSoLuongDoi;
    private javax.swing.JTextField txtTongSoLuongMua;
    private javax.swing.JTextField txtTongTienHangDoi;
    private javax.swing.JTextField txtTongTienHoaDon;
    // End of variables declaration//GEN-END:variables
}
