/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.GUI;

import btl2.DAO.HoiNghiDAO;
import btl2.DAO.NguoithamgiahoinghiDAO;
import btl2.DAO.UserDAO;
import btl2.entiny.Hoinghi;
import btl2.entiny.Nguoithamgiahoinghi;
import btl2.entiny.User;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author DELL
 */
public class AdminManage extends javax.swing.JPanel {

    /**
     * Creates new form AdminManage
     */
    private  JList<Hoinghi> jList1;
    private  JList<Nguoithamgiahoinghi> jListUser = null;
    private  JList<User> jListUserAcc;
    
    DefaultListModel<Nguoithamgiahoinghi> modelUser = null;
    private List<Nguoithamgiahoinghi> userList;
    private List<Hoinghi> fullListHN;
    
    DefaultListModel<User> modelUserAcc = null;
    private List<User> userAccList;
    private List<User> userAccListCurrent = new ArrayList<>();
    private Color actColor = new Color(51,51,51);
    private Color noneActColor = new Color(119,119,119);
    public AdminManage() {
        initComponents();
        startAdminManage();
    }

    private void startAdminManage(){
        List<Hoinghi> hoinghis = HoiNghiDAO.getAll();
        fullListHN = hoinghis;
        DefaultListModel<Hoinghi> model = new DefaultListModel<>();
        if (hoinghis!=null){
           hoinghis.forEach((val) -> { 
             model.addElement(val);
           });
        }
        jList1 = new JList<>(model);
        JScrollPane containerListView;
        HomeItem homeItem = new HomeItem();
        jList1.setCellRenderer(homeItem);
        jList1.setBorder(new EmptyBorder(10,10, 10, 10));
        containerListView = new JScrollPane(jList1);
        containerListView.setBorder(createEmptyBorder());
        listHN.add(containerListView);
        jList1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!jList1.getSelectedValue().isBatDau()){
                    Home.swapLayout(new AddHoiNghi(jList1.getSelectedValue()));
                }
            }
        });
        jComboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filer();
            }
        });
        
        
        //List user join
        userList = NguoithamgiahoinghiDAO.getAllValid();
        //sortNew(userList);
        modelUser = new DefaultListModel<>();
        
        userList.forEach((val) -> { 
            modelUser.addElement(val);
        });
        
        jListUser = new JList<>(modelUser);
        ItemUserJoin itemUserJoin = new ItemUserJoin();
        jListUser.setCellRenderer(itemUserJoin);
        JScrollPane containerListView2;
        jListUser.setBorder(new EmptyBorder(10,10, 10, 10));
        containerListView2 = new JScrollPane(jListUser);
        containerListView2.setBorder(createEmptyBorder());
        layoutUserJoin.add(containerListView2);
        jListUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object[] objects = {"Chấp nhận","Từ chối"};
                Nguoithamgiahoinghi ntg = jListUser.getSelectedValue();
                int result=-1;
                if (ntg!=null){
                    result = JOptionPane.showOptionDialog(Home.getInstance(),"Người dùng: "+jListUser.getSelectedValue().getUser().getUsername()+", id: "
                        +jListUser.getSelectedValue().getUser().getId()+" muốn tham gia\nhội nghị: " 
                        + jListUser.getSelectedValue().getHoinghi().getTen()+", có id: "+jListUser.getSelectedValue().getHoinghi().getId()
                        ,"Phê duyệt",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,objects,objects[0]);
                }
                
                if (result==0){
                    
                    if ( ntg != null ) {
                        ntg.setIsallow(1);
                        NguoithamgiahoinghiDAO.allow(ntg);
                        startUiUserJoin();
                        
                    } else {
                        // Clear the text since there's no selection
                    } 
                    
                }
                else if (result==1){
                    if ( ntg != null ) {
                        ntg.setIsallow(1);
                        NguoithamgiahoinghiDAO.reject(ntg);
                        startUiUserJoin();
                    } else {
                        // Clear the text since there's no selection
                    } 
                }
            }
        });
        
        jComboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBox4.getSelectedIndex()==0){
                    sortNew(userList);
                    sortUI();
                }
                else {
                    sortOld(userList);
                    sortUI();
                }
            }
        });
    
        //List user
        userAccList = UserDAO.getAllUser();
        userAccListCurrent.addAll(userAccList);
        modelUserAcc = new DefaultListModel<>();
        if (userAccList!=null){
            userAccList.forEach((val) -> { 
                modelUserAcc.addElement(val);
            });
        }
        
        jListUserAcc = new JList<>(modelUserAcc);
        sortUserAcc();
        UserItem userItem = new UserItem();
        jListUserAcc.setCellRenderer(userItem);
        JScrollPane containerListView3;
        jListUserAcc.setBorder(new EmptyBorder(10,10, 10, 10));
        containerListView3 = new JScrollPane(jListUserAcc);
        containerListView3.setBorder(createEmptyBorder());
        listUser.add(containerListView3);

        jListUserAcc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               if (jListUserAcc.getSelectedValue()!=null){
                   if (!jListUserAcc.getSelectedValue().getIsDelete()){
                        Object[] objects = {"Cấm","Thoát"};
                        int result = -1;
                        result = JOptionPane.showOptionDialog(Home.getInstance(),"Bạn có muốn chặn người dùng này?"
                                 ,"Chặn người dùng",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,objects,objects[0]);
                        if (result==0){
                            jListUserAcc.getSelectedValue().setIsDelete(true);
                            if (UserDAO.updateUser(jListUserAcc.getSelectedValue())){
                                JOptionPane.showMessageDialog(null, "Đã chặn");
                            } else {
                                JOptionPane.showMessageDialog(null, "Chặn thất bại");
                            }
                        }
                   }
                   else {
                        Object[] objects = {"Bỏ cấm","Thoát"};
                        int result = -1;
                        result = JOptionPane.showOptionDialog(Home.getInstance(),"Bạn có muốn bỏ chặn người dùng này?"
                                 ,"Bỏ chặn người dùng",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,objects,objects[0]);
                        if (result==0){
                            jListUserAcc.getSelectedValue().setIsDelete(false);
                            if (UserDAO.updateUser(jListUserAcc.getSelectedValue())){
                                 JOptionPane.showMessageDialog(null, "Đã bỏ chặn");
                            } else {
                                 JOptionPane.showMessageDialog(null, "Bỏ chặn thất bại");
                            }
                        }
                   }
               }
              
            }
        });
        
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filerUserAcc(searchBox.getText().trim().toLowerCase());
            }
        });
        
        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortUserAcc();
            }
        });
    }
    
    private void filerUserAcc(String keySearch){
        int selected = jComboBox1.getSelectedIndex();
        userAccListCurrent.clear();
        if (selected==0){
            if(userAccList!=null){
                if (searchBox.getText().trim().equals("")){
                    userAccList.forEach((val)-> 
                        userAccListCurrent.add(val));
                }
                else {
                    userAccList.forEach((val)->{
                        if (val.getUsername().trim().toLowerCase().contains(keySearch)||
                            val.getTen()!=null && val.getTen().trim().toLowerCase().contains(keySearch)||
                                 val.getTen()!=null && val.getEmail().trim().toLowerCase().contains(keySearch)){
                                userAccListCurrent.add(val);
                            }
                    });
                }
            } 
        } else if (selected==1){
            if(userAccList!=null){
                if (searchBox.getText().trim().equals("")){
                        userAccList.forEach((val)-> {
                            if (!val.getIsDelete())
                                userAccListCurrent.add(val);
                            }
                    );
                }
                else {
                    userAccList.forEach((val)->{
                        if (val.getUsername().trim().toLowerCase().contains(keySearch)||
                             val.getTen()!=null && val.getTen().trim().toLowerCase().contains(keySearch)||
                                 val.getTen()!=null && val.getEmail().trim().toLowerCase().contains(keySearch)){
                                if (!val.getIsDelete())
                                    userAccListCurrent.add(val);
                            }
                    });
                }
            }
        }
        else if (selected==2){
            if(userAccList!=null){           
               if (searchBox.getText().trim().equals("")){
                        userAccList.forEach((val)-> {
                            if (val.getIsDelete())
                                userAccListCurrent.add(val);
                            }
                    );
                }
                else {
                    userAccList.forEach((val)->{
                        if (val.getUsername().trim().toLowerCase().contains(keySearch)||
                             val.getTen()!=null && val.getTen().trim().toLowerCase().contains(keySearch)||
                                 val.getTen()!=null && val.getEmail().trim().toLowerCase().contains(keySearch)){
                                if (val.getIsDelete())
                                    userAccListCurrent.add(val);
                            }
                    });
                }
            }
        }
        
        sortUserAcc();
    }
    
    private void sortUserAcc(){
        DefaultListModel<User> model = new DefaultListModel<>();
        int index = jComboBox2.getSelectedIndex();
        if (index == 0){
            Collections.sort(userAccListCurrent, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    if (o1.getTimeCreate().before(o2.getTimeCreate())){
                        return 1;
                    }
                    return -1;
                }
            });
        }
        else if (index==1) {
            Collections.sort(userAccListCurrent, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                   return o1.getUsername().compareTo(o2.getUsername());
                }
            });
        }
        else if (index==2) {
            Collections.sort(userAccListCurrent, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o2.getUsername().compareTo(o1.getUsername());
                }
            });
        }
        if (userAccListCurrent!=null){
            userAccListCurrent.forEach(action->model.addElement(action));
        }
        jListUserAcc.setModel(model);
    }
    
    private void filer(){
        int selected = jComboBox3.getSelectedIndex();
        DefaultListModel<Hoinghi> model = new DefaultListModel<>();
        
        if(selected==0){
            if(fullListHN!=null){
                fullListHN.forEach((val)-> 
                model.addElement(val));
            } 
        } else if(selected==1) {
            if(fullListHN!=null){
                fullListHN.forEach((val)-> {   
                    if (!val.isHetHan()){
                        model.addElement(val);
                }});
            }
        }
        else if(selected==2) {
            if(fullListHN!=null){
                fullListHN.forEach((val)-> {   
                    if (val.isHetHan()){
                        model.addElement(val);
                }});
            }
        }
        
        jList1.setModel(model);    
    }
    
    private void sortNew(List<Nguoithamgiahoinghi> uL){
        Collections.sort(uL,new Comparator<Nguoithamgiahoinghi>() {
            @Override
            public int compare(Nguoithamgiahoinghi o1, Nguoithamgiahoinghi o2) {
                return o1.getThoigianthamgia().before(o2.getThoigianthamgia())==true?1:-1;
            }
        });
    }
    
    private void sortOld(List<Nguoithamgiahoinghi> uL){
        Collections.sort(uL,new Comparator<Nguoithamgiahoinghi>() {
            @Override
            public int compare(Nguoithamgiahoinghi o1, Nguoithamgiahoinghi o2) {
                return o1.getThoigianthamgia().after(o2.getThoigianthamgia())==true?1:-1;
            }
        });
    }
    
    private void startUiUserJoin(){
        userList = NguoithamgiahoinghiDAO.getAllValid();
        //sortNew(userList);
        modelUser = new DefaultListModel<>();
        
        userList.forEach((val) -> { 
            modelUser.addElement(val);
        });
        
        jListUser.setModel(modelUser);
    }
    
    private void sortUI(){
        //sortNew(userList);
        modelUser = new DefaultListModel<>();
        
        userList.forEach((val) -> { 
            modelUser.addElement(val);
        });
        
        jListUser.setModel(modelUser);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btThemMoi = new javax.swing.JButton();
        listHN = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        listPheDuyet = new javax.swing.JPanel();
        layoutUserJoin = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        listUser = new javax.swing.JPanel();

        setBackground(new java.awt.Color(248, 248, 248));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Quản lý hội nghị");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(119, 119, 119));
        jLabel2.setText("Quản lý người dùng");
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
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addContainerGap(589, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Thêm và sửa");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(119, 119, 119));
        jLabel4.setText("Phê duyệt yêu cầu");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setBackground(new java.awt.Color(248, 248, 248));
        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel9.setBackground(new java.awt.Color(248, 248, 248));
        jPanel9.setForeground(new java.awt.Color(248, 248, 248));

        btThemMoi.setBackground(new java.awt.Color(255, 153, 51));
        btThemMoi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btThemMoi.setText("Thêm mới");
        btThemMoi.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btThemMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btThemMoiMouseClicked(evt);
            }
        });

        listHN.setBackground(new java.awt.Color(248, 248, 248));
        listHN.setLayout(new java.awt.BorderLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sort.png"))); // NOI18N
        jLabel8.setOpaque(true);

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa diễn ra", "Đã diễn ra" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(389, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel9, "card3");

        listPheDuyet.setBackground(new java.awt.Color(248, 248, 248));

        layoutUserJoin.setBackground(new java.awt.Color(248, 248, 248));
        layoutUserJoin.setLayout(new java.awt.BorderLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sort.png"))); // NOI18N
        jLabel9.setOpaque(true);

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mới nhất", "Cũ nhất" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listPheDuyetLayout = new javax.swing.GroupLayout(listPheDuyet);
        listPheDuyet.setLayout(listPheDuyetLayout);
        listPheDuyetLayout.setHorizontalGroup(
            listPheDuyetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listPheDuyetLayout.createSequentialGroup()
                .addGroup(listPheDuyetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(layoutUserJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(listPheDuyetLayout.createSequentialGroup()
                        .addContainerGap(389, Short.MAX_VALUE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)))
                .addContainerGap())
        );
        listPheDuyetLayout.setVerticalGroup(
            listPheDuyetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listPheDuyetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listPheDuyetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layoutUserJoin, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );

        jPanel5.add(listPheDuyet, "card2");

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, "card2");

        jPanel8.setBackground(new java.awt.Color(248, 248, 248));
        jPanel8.setForeground(new java.awt.Color(248, 248, 248));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(248, 248, 248));
        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 1, 1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hoạt động", "Bị chặn" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày đăng ký", "username A  -> Z", "username Z  ->  A" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sort.png"))); // NOI18N
        jLabel6.setOpaque(true);

        searchBox.setBackground(new java.awt.Color(255, 255, 255));
        searchBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBoxKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jComboBox2)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBox))
                .addContainerGap())
        );

        jPanel8.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        listUser.setBackground(new java.awt.Color(248, 248, 248));
        listUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 40, 40, 40));
        listUser.setLayout(new java.awt.BorderLayout());
        jPanel8.add(listUser, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8, "card3");

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        //searchHN(searchBox.getText().trim().toLowerCase());
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void btThemMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btThemMoiMouseClicked
        // TODO add your handling code here:
        Home.swapLayout(new AddHoiNghi());
    }//GEN-LAST:event_btThemMoiMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout =  (CardLayout) jPanel5.getLayout();
        cardLayout.last(jPanel5);
        startUiUserJoin();
        jLabel4.setForeground(actColor);
        jLabel3.setForeground(noneActColor);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout =  (CardLayout) jPanel5.getLayout();
        cardLayout.first(jPanel5);
        jLabel4.setForeground(noneActColor);
        jLabel3.setForeground(actColor);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout =  (CardLayout) jPanel2.getLayout();
        cardLayout.first(jPanel2);
        jLabel2.setForeground(noneActColor);
        jLabel1.setForeground(actColor);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout =  (CardLayout) jPanel2.getLayout();
        cardLayout.last(jPanel2);
        jLabel1.setForeground(noneActColor);
        jLabel2.setForeground(actColor);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void searchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxKeyReleased
        // TODO add your handling code here:
        filerUserAcc(searchBox.getText().trim().toLowerCase());
    }//GEN-LAST:event_searchBoxKeyReleased

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        sortUserAcc();
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btThemMoi;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel layoutUserJoin;
    private javax.swing.JPanel listHN;
    private javax.swing.JPanel listPheDuyet;
    private javax.swing.JPanel listUser;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables
}
