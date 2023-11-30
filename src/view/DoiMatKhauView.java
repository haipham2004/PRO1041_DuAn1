/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import javax.swing.JOptionPane;
import service.servicImp.TaiKhoanServiceImp;

/**
 *
 * @author Admin
 */
public class DoiMatKhauView extends javax.swing.JPanel {

    TaiKhoanServiceImp serviceDMK = new TaiKhoanServiceImp();

    /**
     * Creates new form DoiMatKhauView
     */
    public DoiMatKhauView() {
        initComponents();
        this.setSize(1300, 755);
        thongBaoMK.setVisible(false);
    }

    public boolean checkEmpty() {
        if (txtMatKhauHT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mat khau hien tai khong duoc de trong !");
            txtMatKhauHT.requestFocus();
            return false;
        }
        if (txtMatKhauM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mat khau moi khong duoc de trong !");
            txtMatKhauM.requestFocus();
            return false;
        }
        if (txtMatKhauM2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhap lai mat khau moi khong duoc de trong !");
            txtMatKhauM2.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkHopLe(String maTK, String mkHienTai, String matKhauMoi, String matKhauMoi2) {
        if (matKhauMoi.length() < 8) {
            JOptionPane.showMessageDialog(this, "Mat khau moi phai co it nhat 8 ky tu !");
            return false;
        }
        if (matKhauMoi.length() > 20) {
            JOptionPane.showMessageDialog(this, "Mat khau moi khong duoc qua 20 ky tu !");
            return false;
        }
//        if (matKhauMoi.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
//            JOptionPane.showMessageDialog(this, "Mat khau moi phai co it nhat mot chu cai viet hoa, mot chu cai viet thuong, mot so va mot ki tu dac biet !");
//            return false;
//        }
        if (!serviceDMK.checkMaTK(maTK)) {
            JOptionPane.showMessageDialog(this, "Khong ton tai ma tai khoan vua nhap !");
            return false;
        }
        if (!serviceDMK.checkPassword(maTK, mkHienTai)) {
            JOptionPane.showMessageDialog(this, "Mat khau hien tai khong dung !");
            return false;
        }

        if (!matKhauMoi.equals(matKhauMoi2)) {
            JOptionPane.showMessageDialog(this, "Xac nhan mat khau moi khong khop !");
            return false;

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

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        btnHuyBo = new javax.swing.JButton();
        txtMatKhauM = new javax.swing.JPasswordField();
        btnCapNhatMK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhauHT = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMatKhauM2 = new javax.swing.JPasswordField();
        thongBaoMK = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ĐỔI MẬT KHẨU");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nhập lại mật khẩu mới");

        btnHuyBo.setBackground(new java.awt.Color(204, 0, 51));
        btnHuyBo.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnHuyBo.setText("HỦY BỎ");
        btnHuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyBoMouseClicked(evt);
            }
        });

        txtMatKhauM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauMActionPerformed(evt);
            }
        });
        txtMatKhauM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauMKeyReleased(evt);
            }
        });

        btnCapNhatMK.setBackground(new java.awt.Color(51, 204, 255));
        btnCapNhatMK.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnCapNhatMK.setText("CẬP NHẬT");
        btnCapNhatMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMKMouseClicked(evt);
            }
        });
        btnCapNhatMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatMKActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mật khẩu hiện tại");

        txtMatKhauHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauHTActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Mã tài khoản");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Mật khẩu mới");

        txtMatKhauM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauM2ActionPerformed(evt);
            }
        });

        thongBaoMK.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtMatKhauHT, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMatKhauM, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(thongBaoMK, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMatKhauM2, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnCapNhatMK)
                        .addGap(147, 147, 147)
                        .addComponent(btnHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtMatKhauHT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhauM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thongBaoMK))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtMatKhauM2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhatMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyBo))
                .addGap(424, 424, 424))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyBoMouseClicked
        // TODO add your handling code here:
        txtMaTK.setText("");
        txtMatKhauHT.setText("");
        txtMatKhauM.setText("");
        txtMatKhauM2.setText("");
    }//GEN-LAST:event_btnHuyBoMouseClicked

    private void txtMatKhauMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauMActionPerformed

    private void btnCapNhatMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMKMouseClicked
        // TODO add your handling code here:
        if (checkEmpty()) {
            String maTK = txtMaTK.getText();
            String matKhauHT = new String(txtMatKhauHT.getPassword());
            String matKhauMoi = new String(txtMatKhauM.getPassword());
            String matKhauMoi2 = new String(txtMatKhauM2.getPassword());
            if (checkHopLe(maTK, matKhauHT, matKhauMoi, matKhauMoi2)) {
                if (serviceDMK.updatePass(maTK, matKhauMoi) > 0) {
                    JOptionPane.showMessageDialog(this, "Doi mat khau thanh cong !");
                } else {
                    JOptionPane.showMessageDialog(this, "Doi mat khau that bai !");
                }
            }
        }
        this.setVisible(true);
    }//GEN-LAST:event_btnCapNhatMKMouseClicked

    private void btnCapNhatMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatMKActionPerformed

    private void txtMatKhauHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauHTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauHTActionPerformed

    private void txtMatKhauM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauM2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauM2ActionPerformed

    private void txtMatKhauMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauMKeyReleased
        // TODO add your handling code here:
        String MatKhauM = new String(txtMatKhauHT.getPassword());
        if (txtMatKhauM.getPassword().length <=8 && txtMatKhauM.getPassword().length >0) {
            thongBaoMK.setForeground(Color.red);
            thongBaoMK.setText("Mật khẩu yếu");
        }
    }//GEN-LAST:event_txtMatKhauMKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatMK;
    private javax.swing.JButton btnHuyBo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel thongBaoMK;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JPasswordField txtMatKhauHT;
    private javax.swing.JPasswordField txtMatKhauM;
    private javax.swing.JPasswordField txtMatKhauM2;
    // End of variables declaration//GEN-END:variables
}
