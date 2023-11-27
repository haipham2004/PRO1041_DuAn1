/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
import model.Events;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import service.servicImp.HoaDonChiTietServiceImp;
import service.servicImp.HoaDonServiceImp;
import service.servicImp.KhachHangServiceImp;
import service.servicImp.KhuyenMaiServiceImp;
import service.servicImp.NhanVienServiceImp;
import util.PDFGene;
//
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class BanHangView extends javax.swing.JPanel implements Runnable, ThreadFactory {

    DefaultTableModel molGH = new DefaultTableModel();
    DefaultTableModel molCTSP = new DefaultTableModel();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    DefaultComboBoxModel<Events> cbxEvents = new DefaultComboBoxModel<>();
    static int indexHoaDonCho = -1;
    int index = -1;
    String userName;
    HoaDonServiceImp serviceHD = new HoaDonServiceImp();
    DefaultTableModel molHDC = new DefaultTableModel();
    AdamStoreView adamStoreView = new AdamStoreView();
    int so = serviceHD.countHoaDon();
    int so2 = serviceHD.countHoaDon();
    HoaDonChiTietServiceImp serviceHDCT = new HoaDonChiTietServiceImp();
    KhuyenMaiServiceImp serviceKM = new KhuyenMaiServiceImp();
    NhanVienServiceImp serviceNV = new NhanVienServiceImp();
    KhachHangServiceImp serviceKH = new KhachHangServiceImp();
    PDFGene pdf = new PDFGene();
//
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    /**
     * Creates new form BanHangView
     */
    public BanHangView() {
        initComponents();
        this.setSize(1300, 755);
        fillTableHDC(serviceHD.getHoaDonCho());
        fillTableChiTietSanPham(serviceCTSP.getAll());
        molGH = (DefaultTableModel) tblGioHang.getModel();
        molGH.setRowCount(0);
        txtMaHDBH2.setEnabled(false);
        txtTongTienBH2.setEnabled(false);
        txtTienThuaBH2.setEnabled(false);
        initWebcam();
    }
    private Point previousPosition;

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); // 0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnlWebCam.setLayout(new BorderLayout());
        pnlWebCam.add(panel, 0);
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            try {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {

            }

            if (result != null) {
                List<ChiTietSanPham> list = serviceCTSP.getList(result.getText());
                txtTest.setText(result.getText());
                int indexs = tblChiTietSanPham.getSelectedRow();
                int indexGioHang = -1;
                try {
                    if (!serviceCTSP.checkMaQR(result.getText())) {
                        JOptionPane.showMessageDialog(this, "Mã QR không tồn tại");
                        return;
                    }
                } catch (SQLException ex) {
                }
                try {
                    String input = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
                    if (input == null || input.isEmpty()) {
                        return;
                    }
                    String maFake = null;
                    int soLuongFake = 0;
                    double giaFake = 0;
                    String tenFake = null;
                    int soLuongTonFake = 0;

                    for (ChiTietSanPham chiTietSanPham : list) {
                        if (chiTietSanPham.getMaChiTietSanPham().equals(result.getText())) {
                            maFake = chiTietSanPham.getMaChiTietSanPham();
                            soLuongFake = Integer.parseInt(input);
                            if (soLuongFake > chiTietSanPham.getSoLuong()) {
                                JOptionPane.showMessageDialog(this, "Số lượng không đủ");
                                return;
                            }

                            soLuongTonFake = chiTietSanPham.getSoLuong() - soLuongFake;
                            giaFake = chiTietSanPham.getGia();
                            tenFake = chiTietSanPham.getSanPham().getTenSanPham();
                        }
                    }
                    ChiTietSanPham ctsps = serviceCTSP.getOne(maFake);

                    if (tblGioHang.getRowCount() > 0) {
                        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                            if (tblGioHang.getValueAt(i, 0) != null) {
                                if (tblGioHang.getValueAt(i, 0).toString().equals(maFake)) {
                                    indexGioHang = i;
                                    break;
                                }
                            }
                        }
                    }

                    if (indexGioHang != -1) {
                        for (ChiTietSanPham chiTietSanPham : list) {
                            if (chiTietSanPham.getMaChiTietSanPham().equals(result.getText())) {
                                int soLuongHienTai = Integer.parseInt(tblGioHang.getValueAt(indexGioHang, 1).toString());
                                int soLuonngSauKhiThem = soLuongHienTai + Integer.parseInt(input);
                                if (soLuonngSauKhiThem > chiTietSanPham.getSoLuong()) {
                                    JOptionPane.showMessageDialog(this, "Số lượng không đủ ạ");
                                    return;
                                }
                                tblGioHang.setValueAt(soLuonngSauKhiThem, indexGioHang, 1);
                                double thanhTienSauKhiThem = Math.round((soLuonngSauKhiThem * giaFake) * 100) / 100;
                                tblGioHang.setValueAt(thanhTienSauKhiThem, indexGioHang, 3);
                                soLuongTonFake = chiTietSanPham.getSoLuong() - soLuonngSauKhiThem;
                            }
                        }
                    } else {
                        fillTableGioHang(tblGioHang, ctsps, soLuongFake);
                        indexHoaDonCho = tblHoaDonCho.getSelectedRow();
                        String maHD = tblHoaDonCho.getValueAt(indexHoaDonCho, 1).toString();
                        String parentDirectory = "D:\\PRO1041_DuAn1";
                        String newDirectoryName = "GioHang";
                        luuGioHangVaoFile(maHD, parentDirectory, newDirectoryName);
                    }
                    for (int i = 0; i < tblChiTietSanPham.getRowCount(); i++) {
                        if (tblChiTietSanPham.getValueAt(i, 0).equals(maFake)) {
                            tblChiTietSanPham.setValueAt(soLuongTonFake, i, 1);
                        }

                    }
                    fillDonHang2();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //Quân
    public String maTangTuDong(String HD) {
        so++;
        String maHD = HD + String.format("%04d", so);;
        return maHD;
    }

    public String maTangTuDong2(String HD) {
        so2++;
        String maHD = HD + String.format("%04d", so2);;
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

    // Hải
    //webcam
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

    public void fillTableGioHang(JTable tbl, ChiTietSanPham ctsp, int SoLuongMua) {
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        Object[] rowData = {
            ctsp.getMaChiTietSanPham(),
            SoLuongMua,
            ctsp.getGia(),
            SoLuongMua * ctsp.getGia(),
            ctsp.getSanPham().getTenSanPham()
        };
        dtm.insertRow(0, rowData);
    }

    // Hải
    // Quân
    public void fillTableHDC2() {
        DangNhapView dangNhapView = new DangNhapView();
        String maHD = maTangTuDong("HD");
        txtMaHDBH2.setText(maTangTuDong2("HD"));
        userName = dangNhapView.getTaiKhoan();
        LocalDate ngayTao = LocalDate.now();
        molHDC.addRow(new Object[]{
            this.tblHoaDonCho.getRowCount() + 1, maHD, dangNhapView.getTenNV(), ngayTao, "Chờ thanh toán"
        });
    }

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

        try (FileWriter fileWriter = new FileWriter(filePath)) {
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
        String filePath = directory + File.separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                molGH.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean validBH() {
        if (txtMaKHBH2.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã KH");
            return false;
        }
        if (txtTienKhachBH2.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tiền khách đưa");
            return false;
        }
        return true;
    }

    public String giuSo(String x) {
        String so = x.replaceAll("[^0-9]", "");
        return so;
    }

    public Double fillDonHang() {
        int x = tblGioHang.getRowCount();
        if (x == 0) {
            return 0.0;
        }
        Double sum = 0.0;
        for (int i = 0; i < x; i++) {
            sum += Double.parseDouble(tblGioHang.getValueAt(i, 3).toString());
        }
        return sum;
    }

    public void fillDonHang2() {
        Double sum = fillDonHang();
        txtTongTienBH2.setText(phanCach(sum));
        loadCboEvent(serviceKM.getActive3(sum));
        cboEventBH.setSelectedIndex(-1);
//        if (serviceKM.getActive2(sum)==null) {
//            txtTongTienBH2.setText(phanCach(sum));
//            return;
//        }
//        Double mucGiam = Double.parseDouble(serviceKM.getActive2(sum).getMucGiamGia()) / 100;
//        txtTongTienBH2.setText(phanCach(sum * (1 - mucGiam)));
    }

    public HoaDon getFormBH() {
        String maHD = txtMaHDBH2.getText();
        NhanVien nhanVien = serviceNV.timTheoUserName(userName);
        String maKH = txtMaKHBH2.getText();
        KhachHang khachHang = serviceKH.getOne(maKH);
        Date ngayTao = new Date();
        Double tongTien = Double.valueOf(boPhanCach(txtTongTienBH2.getText()));
        String ghiChu = txtGhiChu.getText();
        Events ev;
        if (cboEventBH.getSelectedIndex() == -1) {
            ev = null;
        } else {
            String x = cboEventBH.getSelectedItem().toString();
            ev = serviceKM.searchTen(x);
        }
        return new HoaDon(maHD, nhanVien, khachHang, ngayTao, tongTien, true, ghiChu, ev);
    }

    public String phanCach(Double x) {
        NumberFormat fm = NumberFormat.getNumberInstance(Locale.US);
        return fm.format(x);
    }

    public String boPhanCach(String x) {
        String so = x.replace(",", "");
        if (so.isBlank()) {
            return "0";
        }
        try {
            Double temp = Double.valueOf(so);
            return String.format("%.0f", temp);
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    public void tinhThua() {
        Double tongTien = Double.valueOf(boPhanCach(txtTongTienBH2.getText()));
        txtTienKhachBH2.setText(phanCach(Double.valueOf(boPhanCach(txtTienKhachBH2.getText()))));
        Double tienKhach = Double.valueOf(boPhanCach(txtTienKhachBH2.getText()));
        Double tienThua = tienKhach - tongTien;
        txtTienThuaBH2.setText(phanCach(tienThua));
    }

    public void loadCboEvent(List<Events> list) {
        cbxEvents.removeAllElements();
        for (Events ev : list) {
            cbxEvents.addElement(ev);
        }
        cboEventBH.setModel((ComboBoxModel) cbxEvents);
        cboEventBH.setSelectedIndex(-1);
    }

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
        btnXoaSP = new javax.swing.JButton();
        btnXoaTatCaSP = new javax.swing.JButton();
        txtTest = new javax.swing.JTextField();
        btnQR = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        btnThemGioHang = new javax.swing.JButton();
        txtTimKiemCTSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToanBH2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaHDBH2 = new javax.swing.JTextField();
        txtMaKHBH2 = new javax.swing.JTextField();
        txtTongTienBH2 = new javax.swing.JTextField();
        txtTienKhachBH2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTienThuaBH2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnTaoHoaDonCho = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboEventBH = new javax.swing.JComboBox<>();
        pnlWebCam = new javax.swing.JPanel();

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
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

        btnXoaSP.setText("Xóa sản phẩm");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnXoaTatCaSP.setText("Xóa tất cả");
        btnXoaTatCaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaSPActionPerformed(evt);
            }
        });

        btnQR.setText("Quét QR");
        btnQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnXoaSP)
                        .addGap(35, 35, 35)
                        .addComponent(btnXoaTatCaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(txtTest, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(174, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTatCaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTest))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
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

        btnThanhToanBH2.setText("Thanh toán");
        btnThanhToanBH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanBH2ActionPerformed(evt);
            }
        });

        jLabel18.setText("Mã HĐ");

        jLabel20.setText("Mã KH");

        jLabel22.setText("Tổng tiền");

        jLabel23.setText("Tiền khách đưa");

        txtTienKhachBH2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachBH2tienKhachNhap(evt);
            }
        });

        jLabel24.setText("Tiền thừa");

        jLabel2.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        btnTaoHoaDonCho.setText("Tạo hóa đơn");
        btnTaoHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoHoaDonChoMouseClicked(evt);
            }
        });
        btnTaoHoaDonCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonChoActionPerformed(evt);
            }
        });

        jLabel3.setText("Event");

        cboEventBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEventBHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20)
                            .addComponent(jLabel24))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTaoHoaDonCho)
                                    .addComponent(btnThanhToanBH2)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMaHDBH2, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                        .addComponent(txtMaKHBH2)
                                        .addComponent(txtTongTienBH2)))
                                .addGap(22, 22, 22))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienThuaBH2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachBH2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboEventBH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(21, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMaHDBH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaKHBH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTongTienBH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTienKhachBH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTienThuaBH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboEventBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addComponent(btnThanhToanBH2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaoHoaDonCho)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlWebCam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        pnlWebCam.setForeground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            } else if (soLuongMua < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng nhập lại");
                return;
            }
            String ma = tblChiTietSanPham.getValueAt(indexs, 0).toString();
            int soLuong = Integer.parseInt(tblChiTietSanPham.getValueAt(indexs, 1).toString());
            double gia = Double.parseDouble(tblChiTietSanPham.getValueAt(indexs, 2).toString());
            String ten = tblChiTietSanPham.getValueAt(indexs, 3).toString();
            double thanhTien = Math.round((soLuong * gia) * 100) / 100;
            ChiTietSanPham ctsps = serviceCTSP.getOne(ma);
            if (tblGioHang.getRowCount() > 0) {
                for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                    if (tblGioHang.getValueAt(i, 0) != null) {
                        if (tblGioHang.getValueAt(i, 0).toString().equals(ma)) {
                            indexGioHang = i;
                            break;
                        }
                    }
                }
            }
            if (indexGioHang != -1) {
                int soLuongHienTai = Integer.parseInt(tblGioHang.getValueAt(indexGioHang, 1).toString());
                int soLuonngSauKhiThem = soLuongHienTai + Integer.parseInt(input);
                tblGioHang.setValueAt(soLuonngSauKhiThem, indexGioHang, 1);
                double thanhTienSauKhiThem = Math.round((soLuonngSauKhiThem * gia) * 100) / 100;
                tblGioHang.setValueAt(thanhTienSauKhiThem, indexGioHang, 3);

            } else {
                fillTableGioHang(tblGioHang, ctsps, Integer.parseInt(input));

                //Quân
                //Nhớ đổi đường dẫn thư mục
                indexHoaDonCho = tblHoaDonCho.getSelectedRow();
                String maHD = tblHoaDonCho.getValueAt(indexHoaDonCho, 1).toString();
                String parentDirectory = "D:\\PRO1041_DuAn1";

                String newDirectoryName = "GioHang";
                luuGioHangVaoFile(maHD, parentDirectory, newDirectoryName);
            }
            soLuongTon = soLuongTon - soLuongMua;
            tblChiTietSanPham.setValueAt(soLuongTon, indexs, 1);
            fillDonHang2();
            //Quân
            //Nhớ đổi đường dẫn thư mục
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_btnThemGioHangActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void txtTimKiemCTSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCTSPKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemCTSPKeyReleased

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnTaoHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoHoaDonChoMouseClicked
        // TODO add your handling code here:
        fillTableHDC2();
        molGH = (DefaultTableModel) tblGioHang.getModel();
        if (molGH.getRowCount() > 0) {
            molGH.setRowCount(0);
        }
        int indexDongCuoi = tblHoaDonCho.getRowCount() - 1;
        tblHoaDonCho.setRowSelectionInterval(indexDongCuoi, indexDongCuoi);
    }//GEN-LAST:event_btnTaoHoaDonChoMouseClicked

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        // TODO add your handling code here:
        indexHoaDonCho = tblHoaDonCho.getSelectedRow();
        String fileName = "GioHang_" + tblHoaDonCho.getValueAt(indexHoaDonCho, 1) + ".csv";
        //Nhớ đổi đường dẫn thư mục
        loadTableDataFromFile("D:\\PRO1041_DuAn1\\GioHang", fileName);
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:     
        int indexXoaGH = tblGioHang.getSelectedRow();
        if (indexXoaGH == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xoá");
            return;
        }

        int checkXoaGH = JOptionPane.showConfirmDialog(this, "Bạn có chắc mắc muốn xoá sản phẩm");
        if (checkXoaGH == JOptionPane.YES_NO_OPTION) {

            String productID = tblGioHang.getValueAt(indexXoaGH, 0).toString();
            int quantity = Integer.parseInt(tblGioHang.getValueAt(indexXoaGH, 1).toString());
            int indexSanPham = -1;
            for (int i = 0; i < tblChiTietSanPham.getRowCount(); i++) {
                if (tblChiTietSanPham.getValueAt(i, 0).toString().equals(productID)) {
                    indexSanPham = i;
                    break;
                }
            }
            if (indexSanPham != -1) {
                int currentQuantity = Integer.parseInt(tblChiTietSanPham.getValueAt(indexSanPham, 1).toString());
                int updatedQuantity = currentQuantity + quantity;
                tblChiTietSanPham.setValueAt(updatedQuantity, indexSanPham, 1);
            }
            molGH.removeRow(indexXoaGH);
        } else {
            JOptionPane.showMessageDialog(this, "Not");
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRActionPerformed
        // TODO add your handling code here:
        if (webcam.isOpen()) {
            webcam.close();
            System.out.println("Close");
        } else {
            initWebcam();

        }
    }//GEN-LAST:event_btnQRActionPerformed

    private void btnThanhToanBH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanBH2ActionPerformed
        if (txtTienThuaBH2.getText().contains("-")) {
            JOptionPane.showMessageDialog(this, "Số tiền được nhập không đủ để thanh toán");
            return;
        }
        HoaDon hd;
        try {
            hd = this.getFormBH();
            Double tongSau = hd.getTongTien();
            Double tong = fillDonHang();
            Double dua = Double.valueOf(boPhanCach(txtTienKhachBH2.getText()));
            Double tra = Double.valueOf(boPhanCach(txtTienThuaBH2.getText()));
            pdf.genPDF(serviceHDCT.getJoHang(tblGioHang), hd, tong, tongSau, dua, tra);
            serviceHD.them(hd);
            serviceCTSP.sua2(serviceHDCT.getJoHang(tblGioHang));
            serviceHDCT.insert(serviceHDCT.getJoHang2(tblGioHang, hd));
        } catch (IOException ex) {
            Logger.getLogger(BanHangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThanhToanBH2ActionPerformed

    private void txtTienKhachBH2tienKhachNhap(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachBH2tienKhachNhap
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_DELETE))) {
            String txt = txtTienKhachBH2.getText().replaceAll("[^\\d]", "");
            txtTienKhachBH2.setText(txt);
        }
        tinhThua();
    }//GEN-LAST:event_txtTienKhachBH2tienKhachNhap

    private void btnXoaTatCaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaSPActionPerformed
        // TODO add your handling code here:
        int checkXoaGH = JOptionPane.showConfirmDialog(this, "Bạn có chắc mắc muốn xoá tất cả sản phẩm chưa được đặt hàng");
        if (checkXoaGH == JOptionPane.YES_NO_OPTION) {

            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                String productID = tblGioHang.getValueAt(i, 0).toString();
                int quantity = Integer.parseInt(tblGioHang.getValueAt(i, 1).toString());

                int indexSanPham = -1;
                for (int j = 0; j < tblChiTietSanPham.getRowCount(); j++) {
                    if (tblChiTietSanPham.getValueAt(j, 0).toString().equals(productID)) {
                        indexSanPham = j;
                        break;
                    }
                }

                if (indexSanPham != -1) {
                    int currentQuantity = Integer.parseInt(tblChiTietSanPham.getValueAt(indexSanPham, 1).toString());
                    int updatedQuantity = currentQuantity + quantity;
                    tblChiTietSanPham.setValueAt(updatedQuantity, indexSanPham, 1);
                }
            }

            molGH.setRowCount(0);
        } else {
            JOptionPane.showMessageDialog(this, "Not");
        }


    }//GEN-LAST:event_btnXoaTatCaSPActionPerformed

    private void btnTaoHoaDonChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonChoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnTaoHoaDonChoActionPerformed

    private void cboEventBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEventBHActionPerformed
        if (cboEventBH.getSelectedIndex() == -1) {
            Double sum = fillDonHang();
            txtTongTienBH2.setText(phanCach(sum));
            tinhThua();
            return;
        }

        String x = cboEventBH.getSelectedItem().toString();
        Events ev = serviceKM.searchTen(x);
        Double tien = fillDonHang();
        Double giam;
        Double newTien;
        if (ev.isHinhThuc()) {
            System.out.println(giuSo(ev.getMucGiamGia()));
            System.out.println(1);
            giam = Double.valueOf(giuSo(ev.getMucGiamGia()));
            newTien = tien - giam;
            txtTongTienBH2.setText(phanCach(newTien));
        } else {
            System.out.println(giuSo(ev.getMucGiamGia()));
            System.out.println(2);
            giam = Double.valueOf(giuSo(ev.getMucGiamGia())) / 100;
            newTien = tien * (1 - giam);
            txtTongTienBH2.setText(phanCach(newTien));
        }
        tinhThua();
    }//GEN-LAST:event_cboEventBHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQR;
    private javax.swing.JButton btnTaoHoaDonCho;
    private javax.swing.JButton btnThanhToanBH2;
    private javax.swing.JButton btnThemGioHang;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaTatCaSP;
    private javax.swing.JComboBox<String> cboEventBH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlWebCam;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaHDBH2;
    private javax.swing.JTextField txtMaKHBH2;
    private javax.swing.JTextField txtTest;
    private javax.swing.JTextField txtTienKhachBH2;
    private javax.swing.JTextField txtTienThuaBH2;
    private javax.swing.JTextField txtTimKiemCTSP;
    private javax.swing.JTextField txtTongTienBH2;
    // End of variables declaration//GEN-END:variables

}
