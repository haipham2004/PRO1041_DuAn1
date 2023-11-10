/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author RavenPC
 */
public class AdamStoreView extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public AdamStoreView() {
        initComponents();
        this.dispose();
        setUndecorated(true);
        setSize(1500, 820);
        this.setLocationRelativeTo(null);
        execute();
    }

    public void changePanelBody(JPanel panel) {
        panelBody.removeAll();
        panelBody.add(panel);
        panelBody.repaint();
        panelBody.revalidate();
    }

    private void execute() {
        //Thêm icon vào 
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/icon/document.png"));
        ImageIcon iconSanPham = new ImageIcon(getClass().getResource("/icon/shirt.png"));
        ImageIcon iconDot = new ImageIcon(getClass().getResource("/icon/black-circle.png"));
        ImageIcon iconNhanVien = new ImageIcon(getClass().getResource("/icon/multiple-users-silhouette.png"));
        ImageIcon iconBanHang = new ImageIcon(getClass().getResource("/icon/shopping-cart.png"));
        ImageIcon iconKhachHang = new ImageIcon(getClass().getResource("/icon/user (1).png"));
        ImageIcon iconHoaDon = new ImageIcon(getClass().getResource("/icon/bill.png"));
        ImageIcon iconKhuyenMai = new ImageIcon(getClass().getResource("/icon/discount.png"));
        ImageIcon iconVoucher = new ImageIcon(getClass().getResource("/icon/voucher.png"));
        ImageIcon iconDoiMatKhau = new ImageIcon(getClass().getResource("/icon/reset-password.png"));
        ImageIcon iconDangXuat = new ImageIcon(getClass().getResource("/icon/power-off.png"));
        //Tạo thanh thống kê
        MenuItem menuThongKe = new MenuItem(iconThongKe, "Thống kê", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ThongKeView());
            }
        });
        //Thanh bên trong sản phẩm
        MenuItem menuSanPham1 = new MenuItem(iconDot, "Sản phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new SanPhamView());
            }
        });
        MenuItem menuCtsp = new MenuItem(iconDot, "Chi tiết sản phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new CTSPView());
            }
        });

        MenuItem menuThuoctinh = new MenuItem(iconDot, "Thuộc tính", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ThuocTinhView());
            }
        });
        // Sản phẩm chung
        MenuItem menuMatHang = new MenuItem(iconSanPham, "Mặt hàng", null, menuSanPham1, menuCtsp, menuThuoctinh);
        //Nhân viên
        MenuItem menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new NhanVienView());
            }
        });
        //Bán hàng
        MenuItem menuBanHang = new MenuItem(iconBanHang, "Bán hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new BanHangView());
            }
        });
        //Khách  hàng
        MenuItem menuKhachHang = new MenuItem(iconKhachHang, "Khách hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new KhachHangView());
            }
        });
        //Hóa đơn- lịch sử
        MenuItem menuHoaDon = new MenuItem(iconHoaDon, "Hóa đơn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new HoaDonView());
            }
        });
        //Khuyến mại
        MenuItem menuKm = new MenuItem(iconKhuyenMai, "Khuyến mại", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new KhuyenMaiView());
            }
        });
        //Voucher
        MenuItem menuVoucher = new MenuItem(iconVoucher, "Voucher", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new VoucherView());
            }
        });

        //DoiMatKhau
        MenuItem menuDoiMatKhau = new MenuItem(iconDoiMatKhau, "Đổi mật khẩu", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new DoiMatKhauView());
            }
        });

        //DangXuat
        MenuItem menuDangXuat = new MenuItem(iconDangXuat, "Đăng xuất", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });

        //Câu lệnh thêm vào menu
        addMenu(menuThongKe, menuMatHang, menuNhanVien, menuBanHang, menuKhachHang, menuKm, menuVoucher, menuDoiMatKhau, menuDangXuat);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);

            }
        }
        menus.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(45, 113, 248));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(1443, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(115, 120, 230));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 384));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(45, 113, 248));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1514, 981));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdamStoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdamStoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdamStoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdamStoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdamStoreView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
