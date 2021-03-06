/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.GUI;

import btl2.DAO.HoiNghiDAO;
import btl2.entiny.Hoinghi;
import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author DELL
 */
public class HomeItem extends javax.swing.JPanel implements ListCellRenderer<Hoinghi>{

    /**
     * Creates new form NewJPanel
     */
    Color myColor = new Color(255,153,51);
    Hoinghi hoinghi = null;
    public HomeItem() {
        initComponents();
        this.setOpaque(true);
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        img = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        people = new javax.swing.JLabel();
        detail = new javax.swing.JLabel();
        timeBegin = new javax.swing.JLabel();
        timeEnd = new javax.swing.JLabel();
        chinhSua = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 51)));

        name.setText("jLabel3");

        people.setText("jLabel4");

        detail.setText("jLabel5");

        timeBegin.setText("jLabel6");

        timeEnd.setText("jLabel7");

        chinhSua.setBackground(new java.awt.Color(255, 153, 51));
        chinhSua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chinhSua.setForeground(new java.awt.Color(255, 255, 255));
        chinhSua.setText("Có thể chỉnh sửa");
        chinhSua.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chinhSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(detail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(people, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chinhSua)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(people, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chinhSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeBegin)
                    .addComponent(timeEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detail, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chinhSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chinhSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chinhSua;
    private javax.swing.JLabel detail;
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel people;
    private javax.swing.JLabel timeBegin;
    private javax.swing.JLabel timeEnd;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends Hoinghi> list, Hoinghi value, int index, boolean isSelected, boolean cellHasFocus) {
        hoinghi = value;
        if (Home.getCurrentUser()!=null && Home.getCurrentUser().getPermission()==1){
            if (value.isBatDau()){
                chinhSua.setText("Không chỉnh sửa");
                chinhSua.setBackground(Color.red);
                chinhSua.setVisible(true);
            }
            else {
                chinhSua.setText("Có thể chỉnh sửa");
                chinhSua.setBackground(myColor);
                chinhSua.setVisible(true);
            }
        } else {
            chinhSua.setVisible(false);
        }
        
        name.setOpaque(true);
        detail.setOpaque(true);
        people.setOpaque(true);
        timeBegin.setOpaque(true);
        timeEnd.setOpaque(true);
        img.setOpaque(true);
        if (isSelected) {
            name.setBackground(list.getSelectionBackground());
            detail.setBackground(list.getSelectionBackground());
            people.setBackground(list.getSelectionBackground());
            timeBegin.setBackground(list.getSelectionBackground());
            timeEnd.setBackground(list.getSelectionBackground());
            img.setBackground(list.getSelectionBackground());
            this.setBackground(list.getSelectionBackground());
        } else { // when don't select
            name.setBackground(list.getBackground());
            detail.setBackground(list.getBackground());
            people.setBackground(list.getBackground());
             timeBegin.setBackground(list.getBackground());
            timeEnd.setBackground(list.getBackground());
            img.setBackground(list.getBackground());
            this.setBackground(list.getBackground());
        }

        // lbIcon.setIcon(new ImageIcon(getClass().getResource(
        //        "/nguyenvanquan7826/JList/" + value.getIconName() + ".jpg")));
        name.setText(value.getTen());
        detail.setText(value.getMota());
        people.setForeground(Color.blue);
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
        String strDay="DD/MM/YYYY";
        if (value.getThoigianbd()!= null) {
            strDay = dateFormat.format(value.getThoigianbd());
        }
        timeBegin.setText(strDay);
        if (value.getThoigiankt()!= null) {
            strDay = dateFormat.format(value.getThoigiankt());
        }
        timeEnd.setText(strDay);
        if (HoiNghiDAO.getImg(value)!=""){
            img.setIcon(AddHoiNghi.ResizeImage(HoiNghiDAO.getImg(value), img));
        }
        people.setText(value.getSoluongnguoithamgia()==null?"0/0":String.valueOf(value.getSoLuongDaThamGia())+'/'+String.valueOf(value.getSoluongnguoithamgia()));
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(this);
        panel.setBorder(new EmptyBorder(10,10, 10, 10));
        return panel;
    }
}
