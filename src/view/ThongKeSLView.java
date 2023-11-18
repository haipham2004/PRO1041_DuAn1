/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import service.servicImp.ThongKeSLServiceImp;

import java.util.Date;
import javax.swing.JPanel;
import model.ChiTietHoaDon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import repository.ThongKeDTRepository;
import repository.ThongKeSLRepository;

/**
 *
 * @author Admin
 */
public class ThongKeSLView extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    ThongKeSLServiceImp service = new ThongKeSLServiceImp();
    ThongKeSLRepository repo = new ThongKeSLRepository();

    /**
     * Creates new form ThongKeView
     */
    public ThongKeSLView() {
        initComponents();
        this.setSize(1300, 755);
        fillTableCTHD(repo.getListCTHD());
        setDataToChart(panelChartHoaDon);
//        setDataToChart2(panelTopSP);
    }

    public void fillTableCTHD(List<ChiTietHoaDon> listCTHD) {
        defaultTableModel = (DefaultTableModel) tblChiTietDonHang.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietHoaDon cthd : listCTHD) {
            defaultTableModel.addRow(new Object[]{
                cthd.getHoaDon().getMaHoaDon(), cthd.getHoaDon().getNgayTao(),
                cthd.getChiTietSanPham().getMaChiTietSanPham(), cthd.getSoLuong(),
                cthd.getDonGia(), cthd.getSoLuong() * cthd.getDonGia()
            });
        }
    }

    public void setDataToChart(JPanel panelHoaDon) {
        List<ChiTietHoaDon> listCthd = repo.getListBieuDoHD();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (txtNgayBd.getCalendar() == null || txtNgayKT.getCalendar() == null) {
            if (listCthd != null) {
                for (ChiTietHoaDon chiTietHoaDon : listCthd) {
                    dataset.addValue(chiTietHoaDon.getHoaDon().getSoLuongHoaDon(), "Hóa đơn",
                            chiTietHoaDon.getHoaDon().getNgayTao());
                }
            }
            JFreeChart lineChart = ChartFactory.createLineChart("Biểu đồ thống kê tổng số hóa đơn".toUpperCase(),
                    "Ngày", "Hóa đơn", dataset, PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new Dimension(650, 236));
            panelHoaDon.removeAll();
            panelHoaDon.setLayout(new CardLayout());
            panelHoaDon.add(chartPanel);
            panelHoaDon.validate();
            panelHoaDon.repaint();
        }
    }

    public void setDataToChart2(JPanel panelTopSP) {
        List<ChiTietHoaDon> listTop = repo.getListBieuDoTopSP();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listTop != null) {
            for (ChiTietHoaDon chiTietHoaDon : listTop) {
                dataset.addValue(chiTietHoaDon.getHoaDon().getSoLuongHoaDon(), "Hóa đơn",
                        chiTietHoaDon.getHoaDon().getNgayTao());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Top sản phẩm bán được".toUpperCase(),
                "Sản phẩm", "Số lượng bán", dataset, PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(859, 236));
        panelTopSP.removeAll();
        panelTopSP.setLayout(new CardLayout());
        panelTopSP.add(chartPanel);
        panelTopSP.validate();
        panelTopSP.repaint();

    }
//    public void setDataToChart2(JPanel panelTopSP) {
//        List<ChiTietHoaDon> listTop = repo.getListBieuDoTopSP();
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        TaskSeriesCollection ds = new TaskSeriesCollection();
//        JFreeChart ganttChart = ChartFactory.createGanttChart(
//                "BIỂU ĐỒ THEO DÕI SỐ LƯỢNG SẢN PHẨM",
//                "Sản phẩm", "Số lượng", ds, true, false, false
//        );
//        TaskSeries taskSeries;
//        Task task;
//
//        if (listTop != null) {
//            for (ChiTietHoaDon cthd : listTop) {
//                taskSeries = new TaskSeries(cthd.getChiTietSanPham().getSanPham().getTenSanPham());
//                task = new Task(cthd.getChiTietSanPham().getSanPham().getTenSanPham(),
//                        new SimpleTimePeriod(cthd.getHoaDon().getNgayTao(), cthd.getHoaDon().getNgayTao()));
//                taskSeries.add(task);
//                ds.add(taskSeries);
//            }
//        }
//
//        ChartPanel chartPanel = new ChartPanel(ganttChart);
//        chartPanel.setPreferredSize(new Dimension(859, 321));
//        panelTopSP.removeAll();
//        panelTopSP.setLayout(new CardLayout());
//        panelTopSP.add(chartPanel);
//        panelTopSP.validate();
//        panelTopSP.repaint();
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNgayBd = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        btnXuatExcel = new javax.swing.JToggleButton();
        btnGuiEmail = new javax.swing.JToggleButton();
        btnTimKiem = new javax.swing.JToggleButton();
        panelChartHoaDon = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietDonHang = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelTopSP = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THỐNG KÊ SỐ ĐƠN HÀNG");

        jLabel2.setText("Từ ");

        jLabel3.setText("Đến");

        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sheets.png"))); // NOI18N
        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        btnGuiEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new (1).png"))); // NOI18N
        btnGuiEmail.setText("Gửi Email");

        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(76, 76, 76)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuatExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuiEmail)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXuatExcel)
                        .addComponent(btnGuiEmail)
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNgayBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelChartHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                panelChartHoaDonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelChartHoaDonLayout = new javax.swing.GroupLayout(panelChartHoaDon);
        panelChartHoaDon.setLayout(panelChartHoaDonLayout);
        panelChartHoaDonLayout.setHorizontalGroup(
            panelChartHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        panelChartHoaDonLayout.setVerticalGroup(
            panelChartHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblChiTietDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Ngày tạo", "Mã SP", "Số lượng", "Đơn giá"
            }
        ));
        tblChiTietDonHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblChiTietDonHangKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietDonHang);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("CHI TIẾT ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChartHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelChartHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelTopSPLayout = new javax.swing.GroupLayout(panelTopSP);
        panelTopSP.setLayout(panelTopSPLayout);
        panelTopSPLayout.setHorizontalGroup(
            panelTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
        );
        panelTopSPLayout.setVerticalGroup(
            panelTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("TOP SẢN PHẨM BÁN CHẠY");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(panelTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(panelTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
        ThongKeSLRepository repo = new ThongKeSLRepository();
        Date ngayBd = txtNgayBd.getDate();
        Date ngayKt = txtNgayKT.getDate();
        fillTableCTHD(repo.getListTk(ngayBd, ngayKt));
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ChiTietHoaDon> listTKBD = repo.getListTKBieuDoHD(txtNgayBd.getDate(), txtNgayKT.getDate());
        if (listTKBD != null) {
            for (ChiTietHoaDon chiTietHoaDon : listTKBD) {
                dataset.addValue(chiTietHoaDon.getHoaDon().getSoLuongHoaDon(), "Hóa đơn",
                        chiTietHoaDon.getHoaDon().getNgayTao());
            }
        }
        JFreeChart lineChart = ChartFactory.createLineChart("Biểu đồ thống kê tổng số hóa đơn".toUpperCase(),
                "Ngày", "Hóa đơn", dataset, PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(650, 236));
        panelChartHoaDon.removeAll();
        panelChartHoaDon.setLayout(new CardLayout());
        panelChartHoaDon.add(chartPanel);
        panelChartHoaDon.validate();
        panelChartHoaDon.repaint();
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void panelChartHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelChartHoaDonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_panelChartHoaDonKeyReleased

    private void tblChiTietDonHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblChiTietDonHangKeyReleased
        // TODO add your handling code here:
//        setDataToChart(panelChartHoaDon);
    }//GEN-LAST:event_tblChiTietDonHangKeyReleased

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnGuiEmail;
    private javax.swing.JToggleButton btnTimKiem;
    private javax.swing.JToggleButton btnXuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelChartHoaDon;
    private javax.swing.JPanel panelTopSP;
    private javax.swing.JTable tblChiTietDonHang;
    private com.toedter.calendar.JDateChooser txtNgayBd;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    // End of variables declaration//GEN-END:variables
}
