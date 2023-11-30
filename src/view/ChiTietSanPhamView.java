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
import java.util.ArrayList;
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
    DefaultComboBoxModel<MauSac> cbxMauSacLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbxChatLieu = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbxChatLieuLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<KichThuoc> cbxKichThuoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<KichThuoc> cbxKichThuocLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<SanPham> cbxSanPham = new DefaultComboBoxModel<>();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChatLieuServiceImp serviceCl = new ChatLieuServiceImp();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    SanPhamServiceImp serviceSP = new SanPhamServiceImp();
    int trangCTSP = 1, soTrangCTSP, tongBanGhiCTSP, index = 0;
    public List<String> list = new ArrayList<>();

    /**
     * Creates new form CTSPView
     */
    public ChiTietSanPhamView() {
        initComponents();
        this.setSize(1300, 755);
        loadCbxSanPham(serviceSP.getAll());
        loadPageCTSP();
        cboMaSP.setEnabled(false);
        loadCbxKichThuoc(serviceKT.getAll());
        loadCbxMauSac(serviceMS.getAll());
        loadCbxChatLieu(serviceCl.getAll());
        rdConhang1.setSelected(true);
        loadLocMauSac(serviceMS.getAll());
        loadLocChatLieu(serviceCl.getAll());
        loadLocKichThuoc(serviceKT.getAll());
    }

    public void mtam() {
        ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
        String tenTimCL = cl.toString();
        MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
        String tenTimMS = ms.toString();
        KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
        String tenTimKT = kt.toString();
        String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        fillTableChiTietSanPham(serviceCTSP.getListLoc(tenList, tenTimCL, tenTimMS, tenTimKT));
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
        int soLuong;
        double gia;
        boolean trangThai;
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
//       String qr= qrCode(txtMaCTSP.getText());
        return new ChiTietSanPham(sp, ms, cl, kt, soLuong, gia, trangThai);

    }

    public void deltailChiTietSanPham(int index) {
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
        String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        tongBanGhiCTSP = serviceCTSP.tongBanGhi(tenPage);
        if (tongBanGhiCTSP % 5 == 0) {
            soTrangCTSP = tongBanGhiCTSP / 5;
        } else {
            soTrangCTSP = tongBanGhiCTSP / 5 + 1;
        }
        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        System.out.println("Số trang: " + soTrangCTSP);
        System.out.println("Số bản ghi: " + tongBanGhiCTSP);
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
    }

    public void loadCbxSanPham(List<SanPham> list) {
        cbxSanPham.removeAllElements();
        String ma = new SanPhamView().getMaSanPham();
        String ten = new SanPhamView().getTenSanPham();
        System.out.println("Mã: " + ma + " Tên: " + ten);
        for (SanPham sanPham : list) {
            SanPham sps = new SanPham(ma, ten);
            cbxSanPham.addElement(sps);
        }
        cboMaSP.setModel((ComboBoxModel) cbxSanPham);
    }

    public void loadLocMauSac(List<MauSac> list) {
        cbxMauSacLoc.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSacLoc.addElement(mauSac);
        }
        cboLocMau.setModel((ComboBoxModel) cbxMauSacLoc);
    }

    public void loadLocKichThuoc(List<KichThuoc> list) {
        cbxKichThuocLoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuocLoc.addElement(kichThuoc);
        }
        cboLocKich.setModel((ComboBoxModel) cbxKichThuocLoc);
    }

    public void loadLocChatLieu(List<ChatLieu> list) {
        cbxChatLieuLoc.removeAllElements();
        for (ChatLieu chatLieu : list) {
            cbxChatLieuLoc.addElement(chatLieu);
        }
        cboLocChat.setModel((ComboBoxModel) cbxChatLieuLoc);
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
        jLabel46 = new javax.swing.JLabel();
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
        cboLocMau = new javax.swing.JComboBox<>();
        cboLocKich = new javax.swing.JComboBox<>();
        cboLocChat = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        cboMaSP = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
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
        btnBackSP = new javax.swing.JButton();
        btnTaoAllQR = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        cboLocMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocMauMouseClicked(evt);
            }
        });

        cboLocKich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocKich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocKichMouseClicked(evt);
            }
        });

        cboLocChat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocChatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(cboLocMau, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(cboLocKich, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(cboLocChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocKich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if (tblChiTietSanPham.getColumnModel().getColumnCount() > 0) {
            tblChiTietSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(4).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(5).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(6).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(7).setResizable(false);
        }

        cboMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("VND");

        jLabel6.setText("Cái");

        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
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

        btnBackSP.setText("Back to sản phẩm");
        btnBackSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackSPActionPerformed(evt);
            }
        });

        btnTaoAllQR.setText("Tạo QR ALL");
        btnTaoAllQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoAllQRActionPerformed(evt);
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
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnQR)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(btnQuayLai)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnThemCTSP)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSuaCTSP)))
                                .addGap(9, 9, 9))
                            .addComponent(btnTaoAllQR))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(154, 154, 154)
                                        .addComponent(btnBackSP))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnClearCTSP)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatfile)
                                .addGap(5, 5, 5)
                                .addComponent(btnNhapFileExcel))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addGap(18, 18, 18)
                                .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdConhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdHethang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(34, 34, 34))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDau2)
                .addGap(33, 33, 33)
                .addComponent(btnLui2)
                .addGap(41, 41, 41)
                .addComponent(lbSoTrang2)
                .addGap(44, 44, 44)
                .addComponent(btnTien2)
                .addGap(38, 38, 38)
                .addComponent(btnCuoi2)
                .addGap(308, 308, 308))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBackSP)
                            .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoAllQR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel47)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(rdConhang1)
                            .addComponent(rdHethang1))
                        .addGap(21, 48, Short.MAX_VALUE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNhapFileExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemCTSP)
                        .addComponent(btnSuaCTSP)
                        .addComponent(btnClearCTSP)
                        .addComponent(btnQR)
                        .addComponent(btnXuatfile)))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuoi2)
                    .addComponent(btnTien2)
                    .addComponent(lbSoTrang2)
                    .addComponent(btnLui2)
                    .addComponent(btnDau2))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1092, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );

        add(jPanel1, "card3");
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
        deltailChiTietSanPham(index);
        btnThemCTSP.setEnabled(false);
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        loadPageCTSP();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        // TODO add your handling code here:      
        ChiTietSanPham ctsp = savesCTSP();
        String ten = new SanPhamView().getTenSanPham();
        ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
        String tenTimCL = cl.toString();
        MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
        String tenTimMS = ms.toString();
        KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
        String tenTimKT = kt.toString();
        System.out.println("Mtam ơi: " + tenTimCL + tenTimMS + tenTimKT+ten);
        if (validateCTSPs()) {
            if (serviceCTSP.checkTrungCTSP(tenTimCL, tenTimMS, tenTimKT,ten)) {
                JOptionPane.showMessageDialog(this, "Chi tiết sản phẩm đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
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
        }
        String ten = new SanPhamView().getTenSanPham();
        ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
        String tenTimCL = cl.toString();
        MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
        String tenTimMS = ms.toString();
        KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
        String tenTimKT = kt.toString();
        System.out.println("Mtam ơi: " + tenTimCL + tenTimMS + tenTimKT);
        if (serviceCTSP.checkTrungCTSP(tenTimCL, tenTimMS, tenTimKT,ten)) {
            JOptionPane.showMessageDialog(this, "Chi tiết sản phẩm đã tồn tại, vui lòng sửa lại", "Message", 2);
            return;
        } else {
            ChiTietSanPham ctsp = savesCTSP();
            String ma = tblChiTietSanPham.getValueAt(index, 0).toString();
            if (serviceCTSP.sua(ctsp, ma) > 0) {
                serviceCTSP.updateTrangThaiSoLuong();
                loadPageCTSP();
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thành công");

            } else {
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thất bại");
            }
        }

    }//GEN-LAST:event_btnSuaCTSPActionPerformed

    private void btnClearCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCTSPActionPerformed
        // TODO add your handling code here:
        String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
        btnThemCTSP.setEnabled(true);
        txtSoLuong.setText("");
        txtGia.setText("");
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearCTSPActionPerformed

    private void btnDau2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDau2ActionPerformed
        // TODO add your handling code here:
        trangCTSP = 1;
        String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
    }//GEN-LAST:event_btnDau2ActionPerformed

    private void btnLui2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui2ActionPerformed
        // TODO add your handling code here:
        if (trangCTSP > 1) {
            trangCTSP--;
            String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
            lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnLui2ActionPerformed

    private void btnTien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien2ActionPerformed
        // TODO add your handling code here:
        if (trangCTSP < soTrangCTSP) {
            trangCTSP++;
            String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
            lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnTien2ActionPerformed

    private void btnCuoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoi2ActionPerformed
        // TODO add your handling code here:
        trangCTSP = soTrangCTSP;
        String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
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
            String ma = (String) tblChiTietSanPham.getValueAt(index, 0);
            if (serviceCTSP.checkMaQR(ma)) {
                JOptionPane.showMessageDialog(this, "Mã QR của chi tiết này sản phẩm đã tồn tại");
            } else {
                deltailChiTietSanPham(index);
                btnThemCTSP.setEnabled(false);
                serviceCTSP.taoQR(ma);
                JOptionPane.showMessageDialog(this, "Tạo QR thành công cho sản phẩm có mã: " + ma);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Tạo QR thất bại");
        }
    }//GEN-LAST:event_btnQRActionPerformed

    private void btnNhapFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapFileExcelActionPerformed
        // TODO add your handling code here:
        mol = (DefaultTableModel) tblChiTietSanPham.getModel();
        for (int i = 0; i < 10; i++) {
            mol.addRow(new Object[]{
                i, i, i, i, i, i, i, i
            });
        }

        System.out.println("Nhập excel1");
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
        System.out.println("Nhập excel2");
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                mol = (DefaultTableModel) tblChiTietSanPham.getModel();
                System.out.println("Nhập excel3");
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                for (int i = 1; i < tblChiTietSanPham.getRowCount(); i++) {
                    System.out.println("Nhập excel6");
                    XSSFRow excelRow = excelSheet.getRow(i);
                    System.out.println("Nhập excel5");
                    if (excelRow != null) {
                        System.out.println("Test3");
                        XSSFCell excelSoLuong = excelRow.getCell(0);
                        XSSFCell excelGia = excelRow.getCell(1);
                        XSSFCell excelSP = excelRow.getCell(2);
                        XSSFCell excelCL = excelRow.getCell(3);
                        XSSFCell excelMS = excelRow.getCell(4);
                        XSSFCell excelKT = excelRow.getCell(5);
                        XSSFCell excelTT = excelRow.getCell(6);
                        mol.addRow(new Object[]{excelSoLuong, excelGia, excelSP, excelCL, excelKT, excelMS, excelTT});
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
                        ChiTietSanPham ctsp2 = new ChiTietSanPham(sp, ms, cl, kt, soLuong, gia, trangThai);
                        System.out.println("Test1");
                        serviceCTSP.them(ctsp2);
                        System.out.println("Test2");
                        fillTableChiTietSanPham(serviceCTSP.getList(new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham())));

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
        fillTableChiTietSanPham(serviceCTSP.getList(new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham())));
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
            JOptionPane.showMessageDialog(this, "Xuất file  thành công: ");
        }
    }//GEN-LAST:event_btnXuatfileActionPerformed

    private void cboMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMauSacMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauSacMouseClicked

    private void btnBackSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackSPActionPerformed
        // TODO add your handling code here:
        jPanel2.removeAll();
        jPanel2.add(new SanPhamView());
        jPanel2.repaint();
        jPanel2.revalidate();
    }//GEN-LAST:event_btnBackSPActionPerformed

    private void cboLocMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocMauMouseClicked
        // TODO add your handling code here: 
        mtam();
    }//GEN-LAST:event_cboLocMauMouseClicked

    private void cboLocKichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocKichMouseClicked
        // TODO add your handling code here:
        mtam();
    }//GEN-LAST:event_cboLocKichMouseClicked

    private void cboLocChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocChatMouseClicked
        // TODO add your handling code here:
        mtam();
    }//GEN-LAST:event_cboLocChatMouseClicked

    private void btnTaoAllQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoAllQRActionPerformed
        // TODO add your handling code here:
        for (ChiTietSanPham chiTietSanPham : serviceCTSP.getAll()) {
            qrCode(chiTietSanPham.getMaChiTietSanPham());
            serviceCTSP.taoQR(chiTietSanPham.getMaChiTietSanPham());
        }
    }//GEN-LAST:event_btnTaoAllQRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackSP;
    private javax.swing.JButton btnClearCTSP;
    private javax.swing.JButton btnCuoi2;
    private javax.swing.JButton btnDau2;
    private javax.swing.JButton btnLui2;
    private javax.swing.JButton btnNhapFileExcel;
    private javax.swing.JButton btnQR;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnSearchGia;
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JButton btnTaoAllQR;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnTien2;
    private javax.swing.JButton btnXuatfile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLocChat;
    private javax.swing.JComboBox<String> cboLocKich;
    private javax.swing.JComboBox<String> cboLocMau;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTienMax;
    private javax.swing.JTextField txtTienMin;
    // End of variables declaration//GEN-END:variables
}
