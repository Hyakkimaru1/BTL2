/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.GUI;

import btl2.DAO.DiadiemDAO;
import btl2.DAO.HoiNghiDAO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import btl2.entiny.Diadiem;
import btl2.entiny.Hinhanh;
import btl2.entiny.Hoinghi;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author DELL
 */
public class AddHoiNghi extends javax.swing.JPanel {

    /**
     * Creates new form AddHoiNghi
     */
    String path="";
    List<Diadiem> diadiems = null;
    boolean isCreate = true;
    Hoinghi hn = null;
    String pathHn="";
    public AddHoiNghi() {
        initComponents();
        start();
    }
    
    public AddHoiNghi(Hoinghi hn) {
        initComponents();
        this.hn = hn;
        this.isCreate = false;
        person.setValue(new Long(hn.getSoluongnguoithamgia()));
        ten.setText(hn.getTen());
        ngayBD.setDate(hn.getThoigianbd());
        ngayKT.setDate(hn.getThoigiankt());
        motaNgan.setText(hn.getMota());
        motaFull.setText(hn.getMotachitiet());
        pathHn = HoiNghiDAO.getImg(hn);
        path = HoiNghiDAO.getImg(hn);
        if (!pathHn.equals("")){
            jLabel1.setText("");
            jLabel1.setIcon(ResizeImage(pathHn, jLabel1));
        }
        
        diadiems = DiadiemDAO.getAll();
        if (diadiems!=null){
            int index = 0;
            for (Diadiem diadiem: diadiems){
                jComboBox1.addItem(diadiem.toString());
                if (diadiem.getId()==hn.getDiadiem().getId()){
                    jComboBox1.setSelectedIndex(index);
                }
                index++;
            }
        }
        
    }

    private void start(){
       updateListDiaDiem();
       person.setValue(new Long(2));
    }
    
    public void updateListDiaDiem(){
        diadiems = DiadiemDAO.getAll();
        jComboBox1.removeAllItems();
        if (diadiems!=null){
            for (Diadiem diadiem: diadiems){
                jComboBox1.addItem(diadiem.toString());
            }
        }
    }
    
    private void saveFile(String link){
        File sourceFile = new File(path);
        File destinationFile = new File(link);
        
        BufferedImage bi;
        try {
            bi = ImageIO.read(sourceFile);
            BufferedImage outputImage = new BufferedImage(500,
                500, bi.getType());
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(bi, 0, 0, 500, 500, null);
            g2d.dispose();
            ImageIO.write(outputImage, "png", destinationFile);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lưu hình");
            System.err.println(e);
        }
    }
    
    private boolean checkFillAll(){
        if (motaFull.getText().trim().equals("")||ten.getText().trim().equals("")
                ||motaNgan.getText().trim().equals("")||path.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Điền đầy đủ các trường và hình");
            return false;
        }
        return true;
    }
    
    private boolean checkDate(){
        if (ngayBD.getDate()==null||ngayKT.getDate()==null){
            JOptionPane.showMessageDialog(null, "Chọn ngày bắt đầu và ngày kết thúc đúng định dạng.\n vd: 12:00 AM 07/07/2020");
            return false;
        }
        else {
            Date date = new Date();
            if (ngayBD.getDate().before(date)||ngayKT.getDate().before(date))
            {
                JOptionPane.showMessageDialog(null, "Thời gian được chọn phải sau thời gian hiên tại");
                return false;
            }
            else if (ngayBD.getDate().after(ngayKT.getDate())){
                JOptionPane.showMessageDialog(null, "Thời gian kết thúc phải sau thời gian bắt đầu");
                return false;
            }
            
            Diadiem d = diadiems.get(jComboBox1.getSelectedIndex());
            //Check diadiem is validding from date1 to date2
            if (hn==null){
                boolean check = DiadiemDAO.checkValid(d,ngayBD.getDate(),ngayKT.getDate());
                if (!check){
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian bạn chọn, địa điểm\n đang được tổ chức hội nghị khác.");
                    return false;
                }
            }
            else {
                //Check diadiem is validding
                boolean check = DiadiemDAO.checkValid(d,ngayBD.getDate(),ngayKT.getDate(),hn);
                if (!check){
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian bạn chọn, địa điểm\n đang được tổ chức hội nghị khác.");
                    return false;
                }
            }
            
        }
        return true;
    }
    
    private boolean checkGuessJoin(){
        if (!person.isEditValid()){
            JOptionPane.showMessageDialog(null, "Số người phải là số nguyên dương");
            return false;
        }
        else {
            if (person.getValue()!=null){
                Long check = (Long) person.getValue();
                if (check.intValue() <= 0){
                    JOptionPane.showMessageDialog(null, "Số người phải là số nguyên dương");
                    return false;
                }
                else {
                    if(diadiems!= null){
                        Diadiem d = diadiems.get(jComboBox1.getSelectedIndex());
                        if (d.getSucchua()<check.intValue()){
                            JOptionPane.showMessageDialog(null, "Số người đang lớn hơn sức chứa");
                            return false;
                        }
                        if (hn!=null && check.intValue()<hn.getSoLuongDaThamGia()){
                            JOptionPane.showMessageDialog(null, "Số người đã tham gia của bạn là "+hn.getSoLuongDaThamGia()+
                                    "\nVui lòng tăng thêm số lượng hoặc đổi địa điểm và tăng thêm");
                            return false;
                        }
                    }
                }
            } else{
                JOptionPane.showMessageDialog(null, "Số người phải là số nguyên dương");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        ten = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        person = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        motaNgan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        motaFull = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ngayBD = new com.toedter.calendar.JDateChooser();
        ngayKT = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(248, 248, 248));
        setLayout(new javax.swing.OverlayLayout(this));

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        ten.setBackground(new java.awt.Color(255, 255, 255));
        ten.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ten.setForeground(new java.awt.Color(51, 51, 51));
        ten.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(107, 107, 107));
        jLabel2.setText("Địa điểm tổ chức");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(107, 107, 107));
        jLabel3.setText("Tên hội nghị");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(107, 107, 107));
        jLabel4.setText("Ngày bắt đầu");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(107, 107, 107));
        jLabel5.setText("Số người tối đa");

        person.setBackground(new java.awt.Color(255, 255, 255));
        person.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        person.setForeground(new java.awt.Color(51, 51, 51));
        person.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        person.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(107, 107, 107));
        jLabel7.setText("Mô tả chi tiết");

        jButton2.setBackground(new java.awt.Color(255, 153, 51));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thêm địa điểm");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(107, 107, 107));
        jLabel6.setText("Mô tả ngắn");

        motaNgan.setBackground(new java.awt.Color(255, 255, 255));
        motaNgan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        motaNgan.setForeground(new java.awt.Color(51, 51, 51));
        motaNgan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        motaNgan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motaNganActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(107, 107, 107));
        jLabel8.setText("Ngày kết thúc");

        jScrollPane2.setBorder(null);

        motaFull.setBackground(new java.awt.Color(255, 255, 255));
        motaFull.setColumns(20);
        motaFull.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        motaFull.setRows(5);
        motaFull.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane2.setViewportView(motaFull);

        jButton3.setBackground(new java.awt.Color(255, 153, 51));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setText("Xác nhận");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(107, 107, 107));
        jLabel9.setText("Chọn hình");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chọn Hình");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        ngayBD.setBackground(new java.awt.Color(255, 255, 255));
        ngayBD.setForeground(new java.awt.Color(51, 51, 51));
        ngayBD.setDateFormatString("hh:mm a dd/MM/yyyy");
        ngayBD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        ngayKT.setBackground(new java.awt.Color(255, 255, 255));
        ngayKT.setForeground(new java.awt.Color(51, 51, 51));
        ngayKT.setDateFormatString("hh:mm a dd/MM/yyyy");
        ngayKT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ngayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addComponent(ten, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ngayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(motaNgan)
                            .addComponent(person))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ten, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(person, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(motaNgan)
                    .addComponent(ngayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenActionPerformed

    private void personActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personActionPerformed

    private void motaNganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motaNganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motaNganActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new DialogAddDiadiem(this);
        updateListDiaDiem();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        if (checkFillAll() && checkDate() &&  checkGuessJoin())
        {   
            Diadiem d = diadiems.get(jComboBox1.getSelectedIndex());
            Long l = (Long) person.getValue();
            if (isCreate){
                
                Hoinghi hn = new Hoinghi(d,ten.getText().trim(),motaNgan.getText().trim(),
                        motaFull.getText().trim(),ngayBD.getDate(),ngayKT.getDate(),l.intValue()); 
                int save = HoiNghiDAO.addHN(hn);
                String link = "src\\img\\hn\\"+String.valueOf(save)+".png";
                saveFile(link);

                Hinhanh hinhanh = new Hinhanh(HoiNghiDAO.get(save), "src\\img\\hn\\"+String.valueOf(save)+".png");
                HoiNghiDAO.addImg(hinhanh);
                JOptionPane.showMessageDialog(null, "Tạo hội nghị thành công");
            } else if(hn!=null){
                hn.setHoinghi(d,ten.getText().trim(),motaNgan.getText().trim(),
                        motaFull.getText().trim(),ngayBD.getDate(),ngayKT.getDate(),l.intValue());
                if (HoiNghiDAO.updateHoinghi(hn)){
                    if (!pathHn.equals(""))
                    {
                        //System.getProperty("user.dir")+
                        saveFile(pathHn);
                    }
                    else {
                        String link = "src\\img\\hn\\"+String.valueOf(hn.getId())+".png";
                        saveFile(link);
                        Hinhanh hinhanh = new Hinhanh(hn, "src\\img\\hn\\"+String.valueOf(hn.getId())+".png");
                        HoiNghiDAO.addImg(hinhanh);
                    }
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                } else{
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                }
            }
            
            Home.swapLayout(new AdminManage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectFile = file.getSelectedFile();
            path = selectFile.getAbsolutePath();
            jLabel1.setText("");
            jLabel1.setIcon(ResizeImage(path,jLabel1));
            
        }
        else if (result == JFileChooser.CANCEL_OPTION){
            //System.out.println("");
        }
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea motaFull;
    private javax.swing.JTextField motaNgan;
    private com.toedter.calendar.JDateChooser ngayBD;
    private com.toedter.calendar.JDateChooser ngayKT;
    private javax.swing.JFormattedTextField person;
    private javax.swing.JTextField ten;
    // End of variables declaration//GEN-END:variables

    public static ImageIcon ResizeImage(String imgPath, JLabel jl) {
        ImageIcon myIcon = new ImageIcon(imgPath);
        Image img = myIcon.getImage();
        Image newImg;
        if (jl.getWidth()!=0||jl.getHeight()!=0){
            newImg = img.getScaledInstance(jl.getWidth(), jl.getHeight(), Image.SCALE_SMOOTH);
        }
        else {
            newImg = img.getScaledInstance(630,187, Image.SCALE_SMOOTH);    
        }
        ImageIcon imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }
    
     public static ImageIcon ResizeImage(String imgPath, int width,int height) {
        ImageIcon myIcon = new ImageIcon(imgPath);
        Image img = myIcon.getImage();
        Image newImg;
        newImg = img.getScaledInstance(width,height, Image.SCALE_SMOOTH);    
        ImageIcon imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }
}
