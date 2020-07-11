/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.GUI;

import btl2.DAO.HoiNghiDAO;
import btl2.entiny.Hoinghi;
import btl2.entiny.Nguoithamgiahoinghi;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class ChiTietHN extends javax.swing.JPanel {

    /**
     * Creates new form ChiTietHN
     */
    Hoinghi hoinghi=null;
    public ChiTietHN() {
        initComponents();
        if (hoinghi==null){
            hoinghi = new Hoinghi();
        }
    }
    
    public ChiTietHN(Hoinghi value) {
        initComponents();
        hoinghi = value;
        createDetailPage();
    }
    
    public void setHoinghi(Hoinghi value){
        hoinghi = value;
        createDetailPage();
    }
    
    private void createDetailPage(){
        if (HoiNghiDAO.getImg(hoinghi)!=""){
            img.setIcon(AddHoiNghi.ResizeImage(HoiNghiDAO.getImg(hoinghi), 360,224));
        }
        name.setText(hoinghi.getTen());
        detail.setText("<html><p style=\"width:700px\">"+hoinghi.getMotachitiet()+"</p></html>");
        if (Home.isLogin() && HoiNghiDAO.isUserJoin(Home.getCurrentUser(), hoinghi)!=0){
            if (HoiNghiDAO.isUserJoin(Home.getCurrentUser(), hoinghi) == 1){
                    userjoin.setText("Đang chờ xét duyệt");
            }
            else {
                 userjoin.setText("Đã tham gia");
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
        String strDay="DD/MM/YYYY";
        if (hoinghi.getThoigianbd()!= null) {
            strDay = dateFormat.format(hoinghi.getThoigianbd());
        }
        timeBG.setText(strDay);
        if (hoinghi.getThoigiankt()!= null) {
            strDay = dateFormat.format(hoinghi.getThoigiankt());
        }
        timeEnd.setText(strDay);
        String sltg = hoinghi.getSoluongnguoithamgia()==null?"0/0":String.valueOf(hoinghi.getSoLuongDaThamGia())+'/'+String.valueOf(hoinghi.getSoluongnguoithamgia());
        System.out.println("Số lượng tham gia: "+sltg);
        userjoin.setText("Số lượng tham gia: "+sltg);
        if (hoinghi.getSoLuongDaThamGia()==hoinghi.getSoluongnguoithamgia()){
            jButton2.setVisible(false);
        }
        else {
            if (Home.isLogin() && HoiNghiDAO.isUserJoin(Home.getCurrentUser(), hoinghi) != 0){
                jButton2.setVisible(false);
                if (HoiNghiDAO.isUserJoin(Home.getCurrentUser(), hoinghi) == 1){
                    userjoin.setText("Đang chờ xét duyệt");
                }
                else {
                    userjoin.setText("Đã tham gia");
                }
                
            }
            else {
                jButton2.setVisible(true);
            } 
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        timeBG = new javax.swing.JLabel();
        detail = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        userjoin = new javax.swing.JLabel();
        timeEnd = new javax.swing.JLabel();
        timeBG1 = new javax.swing.JLabel();
        timeBG2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new javax.swing.OverlayLayout(this));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        img.setForeground(new java.awt.Color(153, 153, 255));

        name.setBackground(new java.awt.Color(255, 255, 255));
        name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(51, 51, 51));
        name.setText("jLabel2");

        timeBG.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeBG.setForeground(new java.awt.Color(255, 51, 51));
        timeBG.setText("jLabel3");

        detail.setText("jLabel4");
        detail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        detail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton2.setBackground(new java.awt.Color(255, 153, 51));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Đăng ký tham dự");
        jButton2.setBorder(null);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        userjoin.setBackground(new java.awt.Color(255, 255, 255));
        userjoin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userjoin.setForeground(new java.awt.Color(0, 102, 255));

        timeEnd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeEnd.setForeground(new java.awt.Color(255, 51, 51));
        timeEnd.setText("jLabel3");

        timeBG1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeBG1.setForeground(new java.awt.Color(102, 102, 102));
        timeBG1.setText("Từ");

        timeBG2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeBG2.setForeground(new java.awt.Color(102, 102, 102));
        timeBG2.setText("đến");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(userjoin, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(timeBG1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeBG, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(timeBG2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeBG)
                            .addComponent(timeEnd)
                            .addComponent(timeBG1)
                            .addComponent(timeBG2))
                        .addGap(18, 18, 18)
                        .addComponent(userjoin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(detail, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if (!Home.isLogin()){
            Dialog.getInstance().setVisible(true);
        }
        else {
            Object[] objects = {"Tham gia","Huỷ"};
            int result = JOptionPane.showOptionDialog(this,"Bạn có muốn tham gia không?","Success",JOptionPane.INFORMATION_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,objects,objects[0]);
            if (result==0){
                if (Home.isLogin() && HoiNghiDAO.isUserJoin(Home.getCurrentUser(), hoinghi) != 0){
                    JOptionPane.showMessageDialog(this,"Bạn đã đăng ký tham gia !","Sai",JOptionPane.WARNING_MESSAGE);
                    jButton2.setVisible(false);
                    if (HoiNghiDAO.isUserJoin(Home.getCurrentUser(), hoinghi) == 1){
                        userjoin.setText("Đang chờ xét duyệt");
                    }
                    else {
                        userjoin.setText("Đã tham gia");
                    }
                }
                else{
                    if (HoiNghiDAO.addUserJoin(new Nguoithamgiahoinghi(hoinghi,Home.getCurrentUser()))){
                        jButton2.setVisible(false);
                        userjoin.setText("Đang chờ xét duyệt");
                        Home.upDateListHN();
                    }
                    else {
                        System.out.println("False");
                    }
                }
                
            }
        }
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel detail;
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel timeBG;
    private javax.swing.JLabel timeBG1;
    private javax.swing.JLabel timeBG2;
    private javax.swing.JLabel timeEnd;
    private javax.swing.JLabel userjoin;
    // End of variables declaration//GEN-END:variables
}