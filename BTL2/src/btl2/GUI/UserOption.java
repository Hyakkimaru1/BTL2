/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.GUI;

import btl2.DAO.HoiNghiDAO;
import btl2.DAO.NguoithamgiahoinghiDAO;
import btl2.entiny.Hoinghi;
import btl2.entiny.Nguoithamgiahoinghi;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author DELL
 */
public class UserOption extends javax.swing.JPanel {

    /**
     * Creates new form UserOption
     */
    Color actColor = new Color(51,51,51);
    Color noneActColor = new Color(119,119,119);
    JList<Hoinghi> listHN;
    ChiTietHN chiTietHN;
    List<Hoinghi> hoinghis;
    List<Hoinghi> sortListHN = new ArrayList<>();
    HNUserItem hnui = new HNUserItem();
    public UserOption() {
        initComponents();
        createListHoiNghi();
        
    }

     private void createListHoiNghi() {
        hoinghis = HoiNghiDAO.listUserJoin(Home.getCurrentUser());
        sortListHN.addAll(hoinghis);
        DefaultListModel<Hoinghi> model = new DefaultListModel<>();
        if (hoinghis!=null){
           hoinghis.forEach((val) -> { 
             model.addElement(val);
           });
        }
        listHN = new JList<>(model);
        listHN.setCellRenderer(hnui);
       // listHN.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane jsp = new JScrollPane(listHN);
        jsp.setBackground(new Color(248,248,248));
        jsp.setBorder(new EmptyBorder(1, 1, 1, 1));
        jPanel4.add(jsp);
        /*listHN.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
            }
        }); */
        
        listHN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               listHN.setSelectedIndex(getRow(e.getPoint()));
               if (SwingUtilities.isRightMouseButton(e)) {
                    if (!listHN.getSelectedValue().isBatDau())
                    {
                        Object[] objects = {"Bỏ tham dự","Thoát"};
                        int result = -1;
                        String namesHN = listHN.getSelectedValue().getTen();
                        result = JOptionPane.showOptionDialog(Home.getInstance(),"Bạn có muốn bỏ tham dự hội nghị "+namesHN+"?"
                                 ,"Bỏ tham dự",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,objects,objects[0]);
                        if (result==0){
                            NguoithamgiahoinghiDAO.reject(new Nguoithamgiahoinghi(listHN.getSelectedValue(), Home.getCurrentUser()));
                            upDateListHN();
                        }   
                    }
                    else {
                        JOptionPane.showMessageDialog(Home.getInstance(),"Hội nghị này đã diễn ra");
                    }
               }
               else {
                    Hoinghi item = HoiNghiDAO.get(listHN.getSelectedValue().getId());
                    //System.out.println(item.getNguoithamgiahoinghis().size());
                    if (item!=null){
                        chiTietHN = new ChiTietHN(item);
                        Home.showChiTietHN(chiTietHN);
                    }
               }
            }
            
        });
        
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHN(searchBox.getText().trim().toLowerCase());
            }
        });
        
        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortHN();
            }
        });
    }
     
    private void upDateListHN(){
        hoinghis = HoiNghiDAO.listUserJoin(Home.getCurrentUser());
        searchHN(searchBox.getText().trim().toLowerCase());
    }
     
    private int getRow(Point point)
    {
        return listHN.locationToIndex(point);
    }
     
    private void searchHN(String search){
        int selected = jComboBox1.getSelectedIndex();
        sortListHN.clear();
        if (selected == 0 ){
            if (searchBox.getText().trim().equals(""))
            {     
                if (hoinghis!=null){
                      hoinghis.forEach((val) -> { 
                       
                        sortListHN.add(val);
                    });
                }
            }
            else {               
                if (hoinghis!=null){
                      hoinghis.forEach((val) -> {
                        if(val.getTen().toLowerCase().contains(search)||val.getDiadiem().getDiachi().toLowerCase().contains(search)
                                ||val.getThoigianbd().toString().contains(search)||val.getThoigiankt().toString().contains(search)){
                           
                            sortListHN.add(val);
                        }
                    });
                }
            }
        }
        else if (selected==1){
            //hoinghis =
             if (searchBox.getText().trim().equals(""))
            {               
                if (hoinghis!=null){
                    hoinghis.forEach((val) -> {
                      if(HoiNghiDAO.isUserJoin(Home.getCurrentUser(), val) == 2){
                          sortListHN.add(val);
                      }
                    });
                }
            }
            else {               
                if (hoinghis!=null){
                      hoinghis.forEach((val) -> {
                        if(val.getTen().toLowerCase().contains(search)||val.getDiadiem().getDiachi().toLowerCase().contains(search)
                                ||val.getThoigianbd().toString().contains(search)||val.getThoigiankt().toString().contains(search)){
                            if ( HoiNghiDAO.isUserJoin(Home.getCurrentUser(), val) == 2){
                                sortListHN.add(val);
                            }
                        }
                    });
                }
            }           
        }  
        else if (selected==2){
            //hoinghis =
            if (searchBox.getText().trim().equals(""))
            {               
                if (hoinghis!=null){
                    hoinghis.forEach((val) -> {
                      if(HoiNghiDAO.isUserJoin(Home.getCurrentUser(), val) == 1){
                         
                          sortListHN.add(val);
                      }
                    });
                }
            }
            else {               
                if (hoinghis!=null){
                      hoinghis.forEach((val) -> {
                        if(val.getTen().toLowerCase().contains(search)||val.getDiadiem().getDiachi().toLowerCase().contains(search)
                                ||val.getThoigianbd().toString().contains(search)||val.getThoigiankt().toString().contains(search) ){
                            if (HoiNghiDAO.isUserJoin(Home.getCurrentUser(), val) == 1){
                                sortListHN.add(val);
                            }                          
                        }
                    });
                }
            }       
        } else if (selected==3){
            //hoinghis =
            Date today = new Date();
            if (searchBox.getText().trim().equals(""))
            {               
                if (hoinghis!=null){
                    hoinghis.forEach((val) -> {
                      if(today.after(val.getThoigiankt())){
                          
                          sortListHN.add(val);
                      }
                    });
                }
            }
            else {               
                if (hoinghis!=null){
                      hoinghis.forEach((val) -> {
                        if(val.getTen().toLowerCase().contains(search)||val.getDiadiem().getDiachi().toLowerCase().contains(search)
                                ||val.getThoigianbd().toString().contains(search)||val.getThoigiankt().toString().contains(search)){
                            if (today.after(val.getThoigiankt())){
                                sortListHN.add(val);
                            }

                        }
                    });
                }
            }       
        }
        else if (selected==4){
            //hoinghis =
            Date today = new Date();
            if (searchBox.getText().trim().equals(""))
            {               
                if (hoinghis!=null){
                    hoinghis.forEach((val) -> {
                      if(!today.after(val.getThoigiankt())){
                          
                          sortListHN.add(val);
                      }
                    });
                }
            }
            else {               
                if (hoinghis!=null){
                      hoinghis.forEach((val) -> {
                        if(val.getTen().toLowerCase().contains(search)||val.getDiadiem().getDiachi().toLowerCase().contains(search)
                                ||val.getThoigianbd().toString().contains(search)||val.getThoigiankt().toString().contains(search)){  
                            if (!today.after(val.getThoigiankt()))
                            {
                                sortListHN.add(val);
                            } 
                        }
                    });
                }
            }       
        }
        sortHN();
    }
    
    private void sortHN(){
        int selected = jComboBox2.getSelectedIndex();
        DefaultListModel<Hoinghi> model = new DefaultListModel<>();
        if (selected == 0 ){
            if (sortListHN!=null){
                sortListHN.forEach((val) -> {                      
                      model.addElement(val);
                });
            }
        }
        else if (selected==1){
            if (sortListHN!=null){
                for (int i=sortListHN.size()-1;i>=0;--i){
                    model.addElement(sortListHN.get(i));
                }
            }
        }  
        else if (selected==2){
            Collections.sort(sortListHN, new Comparator<Hoinghi>() {
                @Override
                public int compare(Hoinghi o1, Hoinghi o2) {
                   return o1.getThoigianbd().before(o2.getThoigianbd())==true?-1:1;
                }
            });
            sortListHN.forEach((val) -> {                      
                      model.addElement(val);
            });
                
        } else if (selected==3){
            Collections.sort(sortListHN, new Comparator<Hoinghi>() {
                @Override
                public int compare(Hoinghi o1, Hoinghi o2) {
                   return o1.getThoigianbd().after(o2.getThoigianbd())==true?-1:1;
                }
            });
            sortListHN.forEach((val) -> {                      
                      model.addElement(val);
            });
                  
        }
        else if (selected==4){
            Collections.sort(sortListHN, new Comparator<Hoinghi>() {
                @Override
                public int compare(Hoinghi o1, Hoinghi o2) {
                   return o1.getThoigiankt().before(o2.getThoigiankt())==true?-1:1;
                }
            });
            sortListHN.forEach((val) -> {                      
                      model.addElement(val);
            });
                
        }
        else if (selected==5){
            Collections.sort(sortListHN, new Comparator<Hoinghi>() {
                @Override
                public int compare(Hoinghi o1, Hoinghi o2) {
                   return o1.getThoigiankt().after(o2.getThoigiankt())==true?-1:1;
                }
            });
            sortListHN.forEach((val) -> {                      
                      model.addElement(val);
            });
                
        }
        listHN.setModel(model);    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 248, 248));
        setLayout(new javax.swing.OverlayLayout(this));

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách hội nghị đã đăng ký");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(119, 119, 119));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cài đặt tài khoản");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 528, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(248, 248, 248));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 1, 1));

        searchBox.setBackground(new java.awt.Color(255, 255, 255));
        searchBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchBox.setForeground(new java.awt.Color(51, 51, 51));
        searchBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 20));
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });
        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBoxKeyReleased(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đã xét duyệt", "Đang xét duyệt", "Đã kết thúc", "Chưa kết thúc" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày đăng ký ↑", "Ngày đăng ký ↓", "Ngày bắt đầu ↑", "Ngày bắt đầu ↓", "Ngày kết thúc ↑", "Ngày kết thúc ↓" }));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sort.png"))); // NOI18N
        jLabel4.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBox, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addComponent(jComboBox2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel4, "card2");

        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        jPanel2.removeAll();
        jPanel2.add(jPanel4);
        jLabel1.setForeground(actColor);
        jLabel2.setForeground(noneActColor);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        jPanel2.remove(jPanel4);
        jPanel2.add(new UserProfile());
        jLabel1.setForeground(noneActColor);
        jLabel2.setForeground(actColor);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
       //searchHN(searchBox.getText().trim().toLowerCase());
    }//GEN-LAST:event_jLabel3MouseClicked

    private void searchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxKeyReleased
        // TODO add your handling code here:
        searchHN(searchBox.getText().trim().toLowerCase());
    }//GEN-LAST:event_searchBoxKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables
}
