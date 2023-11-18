/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
//
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

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
        loadSPCT();
        loadCbxSanPham(serviceSP.getAll());
        loadCbxTimSanPham(serviceSP.getAll());
        loadCbxKichThuoc(serviceKT.getAll());
        loadCbxMauSac(serviceMS.getAll());
        loadCbxChatLieu(serviceCl.getAll());
        rdConhang1.setSelected(true);
    }
    
    public void loadSPCT(){
        String tenSP = new SanPhamView().getTenSPs();
        if (tenSP == null) {
            loadPageCTSP();
        }else{
            fillTableChiTietSanPham(serviceCTSP.getList(tenSP));    
        }
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
                chiTietSanPham.getKichThuoc(), chiTietSanPham.isTrangThai() ? "Còn hàng" : "Hết hàng",});
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
        String qrCodes = qrCode(maCTSP);
        return new ChiTietSanPham(maCTSP, sp, ms, cl, kt, soLuong, gia, trangThai, qrCodes);

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
    
//       public void loadPageTheoTenCTSP() {
//        tongBanGhiCTSP = serviceCTSP.tongBanGhi();
//        if (tongBanGhiCTSP % 4 == 0) {
//            soTrangCTSP = tongBanGhiCTSP / 4;
//        } else {
//            soTrangCTSP = tongBanGhiCTSP / 4 + 1;
//        }
//        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
//        fillTableChiTietSanPham(serviceCTSP.getList(new SanPhamView().getTenSPs()));
//    }

    public void loadCbxSanPham(List<SanPham> list) {
        cbxSanPham.removeAllElements();
        for (SanPham sanPham : list) {
            cbxSanPham.addElement(sanPham);
        }
        cboMaSP.setModel((ComboBoxModel) cbxSanPham);
    }

    public void loadCbxTimSanPham(List<SanPham> list) {
        cbxSanPham.removeAllElements();
        for (SanPham sanPham : list) {
            cbxSanPham.addElement(sanPham);
        }
        cboTimCTSP.setModel((ComboBoxModel) cbxSanPham);
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

    public String qrCode(String qrcode) {
        try {
            String qrCodeData = qrcode;
            String filePath = "D:\\PRO1041_DuAn1\\PRO1041_DuAn1\\src\\qr\\" + qrcode + ".png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf(".") + 1), new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrcode;
    }

//    public void qrCode2(int index) {
//        try {
//            String qrs = tblChiTietSanPham.getValueAt(index, 0).toString();
//            String qrCodeData = qrs;
//            String filePath = "D:\\PRO1041_DuAn1\\PRO1041_DuAn1\\src\\qr\\" + qrs + ".png";
//            String charset = "UTF-8";
//            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
//            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//            BitMatrix matrix = new MultiFormatWriter().encode(
//                    new String(qrCodeData.getBytes(charset), charset),
//                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
//            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf(".") + 1), new File(filePath));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static CellStyle createStyleForHeader(XSSFSheet sheet) {
        // Create font
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        cboTimCTSP = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        cboMaSP = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnThemCTSP = new javax.swing.JButton();
        btnSuaCTSP = new javax.swing.JButton();
        btnClearCTSP = new javax.swing.JButton();
        btnDau2 = new javax.swing.JButton();
        btnLui2 = new javax.swing.JButton();
        btnTien2 = new javax.swing.JButton();
        btnCuoi2 = new javax.swing.JButton();
        lbSoTrang2 = new javax.swing.JLabel();
        btnQR = new javax.swing.JButton();
        btnNhapFileExcel = new javax.swing.JButton();
        btnXuatfile = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();
        btnBackSP = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jLabel45.setText("Mã CTSP");

        jLabel46.setText("Tên SP");

        jLabel47.setText("Màu sắc");

        jLabel48.setText("Chất liệu");

        jLabel49.setText("Kích thước");

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMauSacMouseClicked(evt);
            }
        });

        jLabel50.setText("Số lượng");

        jLabel51.setText("Giá");

        jLabel52.setText("Trạng thái");

        buttonGroup1.add(rdConhang1);
        rdConhang1.setText("Còn hàng");

        buttonGroup1.add(rdHethang1);
        rdHethang1.setText("Hết hàng");

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá tiền"));
        jPanel10.setToolTipText("");

        jLabel4.setText("Từ");

        txtTienMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMinKeyReleased(evt);
            }
        });

        txtTienMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMaxKeyReleased(evt);
            }
        });

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

        cboTimCTSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTimCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTimCTSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTimCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cboTimCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "Mã SPCT", "Số lượng tồn", "Giá", "Tên sản phẩm", "Chất liệu", "Màu sắc ", "Kích thước", "Trạng thái"
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

        btnQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/z4883882330886_8f9a20df51f3b6cffdecf910e8a70379.jpg"))); // NOI18N
        btnQR.setText("Tạo QR");
        btnQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRActionPerformed(evt);
            }
        });

        btnNhapFileExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/z4883861072449_7ae13d74ca98dc15bf13cc3275ca5686.jpg"))); // NOI18N
        btnNhapFileExcel.setText("Nhập file");
        btnNhapFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapFileExcelActionPerformed(evt);
            }
        });

        btnXuatfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/z4883861072449_7ae13d74ca98dc15bf13cc3275ca5686.jpg"))); // NOI18N
        btnXuatfile.setText("Xuất file");
        btnXuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatfileActionPerformed(evt);
            }
        });

        btnTest.setText("Test");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        btnBackSP.setText("Back to sản phẩm");
        btnBackSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackSPActionPerformed(evt);
            }
        });

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
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnQR))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addGap(47, 47, 47))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnThemCTSP)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSuaCTSP)))))
                        .addGap(9, 9, 9)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(btnTest)
                                        .addGap(206, 206, 206))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addComponent(btnBackSP)
                                        .addGap(23, 23, 23))))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdConhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdHethang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(233, 233, 233))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnClearCTSP)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatfile)
                                .addGap(5, 5, 5)
                                .addComponent(btnNhapFileExcel)))))
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
                    .addComponent(jLabel45)
                    .addComponent(btnBackSP))
                .addGap(14, 14, 14)
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
                                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTest)))
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
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNhapFileExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemCTSP)
                        .addComponent(btnSuaCTSP)
                        .addComponent(btnClearCTSP)
                        .addComponent(btnQR)
                        .addComponent(btnXuatfile)))
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

        add(jPanel2, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGiaActionPerformed
        // TODO add your handling code here:
        double giaMin = Double.parseDouble(txtTienMin.getText());
        double giaMax = Double.parseDouble(txtTienMax.getText());
        fillTableChiTietSanPham(serviceCTSP.getListGia(giaMin, giaMax));
    }//GEN-LAST:event_btnSearchGiaActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();
        txtMaCTSP.setEnabled(false);
        deltailChiTietSanPham(index);
        btnThemCTSP.setEnabled(false);
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
                serviceCTSP.updateTrangThaiSoLuong();
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thành công");
                loadPageCTSP();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thất bại");
            }
        }

    }//GEN-LAST:event_btnSuaCTSPActionPerformed

    private void btnClearCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCTSPActionPerformed
        // TODO add your handling code here:
        loadPageCTSP();
        btnThemCTSP.setEnabled(true);
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

    private void txtTienMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMinKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienMinKeyReleased

    private void txtTienMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMaxKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienMaxKeyReleased

    private void btnQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRActionPerformed
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để tạo mã QR");
            return;
        }
        if (index != -1) {
            try {
                String ma = (String) tblChiTietSanPham.getValueAt(index, 0);
                if (serviceCTSP.checkMaQR(ma)) {
                    System.out.println("Hihi");
                    JOptionPane.showMessageDialog(this, "Mã QR của chi tiết này sản phẩm đã tồn tại");
                } else {
                    qrCode(ma);
                    deltailChiTietSanPham(index);
                    txtMaCTSP.setEnabled(false);
                    btnThemCTSP.setEnabled(false);
                    serviceCTSP.taoQR(ma);
                    JOptionPane.showMessageDialog(this, "Tạo QR thành công cho sản phẩm có mã: " + ma);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietSanPhamView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tạo QR thất bại");
        }
    }//GEN-LAST:event_btnQRActionPerformed

    private void btnNhapFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapFileExcelActionPerformed
        // TODO add your handling code here:
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "D:\\";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                for (int i = 1; i < tblChiTietSanPham.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.getRow(i);

                    if (excelRow != null) {

                        XSSFCell excelMaSPCT = excelRow.getCell(0);
                        XSSFCell excelSoLuong = excelRow.getCell(1);
                        XSSFCell excelGia = excelRow.getCell(2);
                        XSSFCell excelSP = excelRow.getCell(3);
                        XSSFCell excelCL = excelRow.getCell(4);
                        XSSFCell excelMS = excelRow.getCell(5);
                        XSSFCell excelKT = excelRow.getCell(6);
                        XSSFCell excelTT = excelRow.getCell(7);
                        mol.addRow(new Object[]{excelMaSPCT, excelSoLuong, excelGia, excelSP, excelCL, excelKT, excelMS, excelTT});
                        String mactsp = excelMaSPCT.toString();

                        ChiTietSanPham ctsp = new ChiTietSanPham();
                        int soLuong;

                        if (excelSoLuong.getCellType() == CellType.NUMERIC) {
                            soLuong = (int) excelSoLuong.getNumericCellValue();
                        } else {
                            soLuong = (int) Double.parseDouble(excelSoLuong.toString());
                        }

                        ctsp.setSoLuong(soLuong);

                        double gia;

                        if (excelGia.getCellType() == CellType.NUMERIC) {
                            gia = excelGia.getNumericCellValue();
                        } else {
                            gia = Double.parseDouble(excelGia.toString());
                        }

                        ctsp.setGia(gia);
                        SanPham sp = new SanPham();
                        String sps = excelSP.toString();
                        sp.setMaSanPham(sps);
                        ChatLieu cl = new ChatLieu();
                        String cls = excelCL.toString();
                        cl.setMaChatLieu(cls);
                        MauSac ms = new MauSac();
                        String mss = excelMS.toString();
                        ms.setMaMauSac(mss);
                        KichThuoc kt = new KichThuoc();
                        String kts = excelKT.toString();
                        kt.setMaKichThuoc(kts);
                        boolean trangThai;
                        if (soLuong > 0) {
                            trangThai = true;
                        } else {
                            trangThai = false;
                        }

                        ChiTietSanPham ctsp2 = new ChiTietSanPham(mactsp, sp, ms, cl, kt, soLuong, gia, trangThai);

                        serviceCTSP.them(ctsp2);
                        loadPageCTSP();
                    } else {
                        System.out.println("Dòng " + i + " là null. Bỏ qua dòng này.");
                    }
                }
                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            }

        }
    }//GEN-LAST:event_btnNhapFileExcelActionPerformed

    private void btnXuatfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatfileActionPerformed
        // TODO add your handling code here:
        fillTableChiTietSanPham(serviceCTSP.getAll());
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJtableExporter;

        JFileChooser excel = new JFileChooser("D:");
        excel.setDialogTitle("Save as");
        FileNameExtensionFilter file = new FileNameExtensionFilter("EXCEL FILE", "xls", "xlsx", "xlsm");
        excel.setFileFilter(file);

        int excelChooser = excel.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            excelJtableExporter = new XSSFWorkbook();
            XSSFSheet excelsheet = excelJtableExporter.createSheet("Hoa Don");
            CellStyle style = createStyleForHeader(excelsheet);
            XSSFRow a = excelsheet.createRow(0);
            XSSFCell cell1 = a.createCell(0);
            cell1.setCellStyle(style);
            cell1.setCellValue("Mã chi tiết SP");

            XSSFCell cell2 = a.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue("Số lượng");

            XSSFCell cell3 = a.createCell(2);
            cell3.setCellStyle(style);
            cell3.setCellValue("Giá");

            XSSFCell cell4 = a.createCell(3);
            cell4.setCellStyle(style);
            cell4.setCellValue("Tên SP");

            XSSFCell cell5 = a.createCell(4);
            cell5.setCellStyle(style);
            cell5.setCellValue("Chất liệu");

            XSSFCell cell6 = a.createCell(5);
            cell6.setCellStyle(style);
            cell6.setCellValue("Màu sắc");

            XSSFCell cell7 = a.createCell(6);
            cell7.setCellStyle(style);
            cell7.setCellValue("Kích thước");

            XSSFCell cell8 = a.createCell(7);
            cell8.setCellStyle(style);
            cell8.setCellValue("Trạng thái");

            for (int i = 0; i < tblChiTietSanPham.getRowCount(); i++) {
                try {
                    XSSFRow excelRow = excelsheet.createRow(i + 1);

                    for (int j = 0; j < tblChiTietSanPham.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblChiTietSanPham.getValueAt(i, j).toString());
                    }

                    excelFOU = new FileOutputStream(excel.getSelectedFile() + ".xlsx");
                    excelBOU = new BufferedOutputStream(excelFOU);
                    try {
                        excelJtableExporter.write(excelBOU);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (excelBOU != null) {
                            excelBOU.close();
                        }
                        if (excelFOU != null) {
                            excelFOU.close();
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Xuất file  thành công: " + file);
        }
    }//GEN-LAST:event_btnXuatfileActionPerformed

    private void cboMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMauSacMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauSacMouseClicked

    private void cboTimCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTimCTSPMouseClicked
        // TODO add your handling code here:
        SanPham sp = (SanPham) cbxSanPham.getSelectedItem();
        String tenTimCTSP = sp.toString();
        fillTableChiTietSanPham(serviceCTSP.getList(tenTimCTSP));
    }//GEN-LAST:event_cboTimCTSPMouseClicked

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        // TODO add your handling code here:
//        String ten="OLD NAVY";
//        spview.fillTableChiTietSanPham2(serviceCTSP.getList(ten), tblChiTietSanPham);
    }//GEN-LAST:event_btnTestActionPerformed

    private void btnBackSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackSPActionPerformed
        // TODO add your handling code here:
        jPanel2.removeAll();
            jPanel2.add(new SanPhamView());
            jPanel2.repaint();
            jPanel2.revalidate();
    }//GEN-LAST:event_btnBackSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackSP;
    private javax.swing.JButton btnClearCTSP;
    private javax.swing.JButton btnCuoi2;
    private javax.swing.JButton btnDau2;
    private javax.swing.JButton btnLui2;
    private javax.swing.JButton btnNhapFileExcel;
    private javax.swing.JButton btnQR;
    private javax.swing.JButton btnSearchGia;
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnTien2;
    private javax.swing.JButton btnXuatfile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTimCTSP;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTienMax;
    private javax.swing.JTextField txtTienMin;
    // End of variables declaration//GEN-END:variables
}
