package btl2.entiny;
// Generated Jun 29, 2020 9:30:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private int id;
     private String username;
     private String password;
     private String ten;
     private String email;
     private Date timeCreate;
     private Boolean isDelete;
     private int permission;
     private Set nguoithamgiahoinghis = new HashSet(0);

    public User() {
    }

    public User(String username,String passString) {
        this.username = username;
        this.password = passString;
        this.timeCreate = new Date();
        this.isDelete = false;
    }
	
    public User(int id) {
        this.id = id;
    }
    
    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.ten = user.ten;
        this.email = user.email;
        this.timeCreate = user.timeCreate;
        this.isDelete = user.isDelete;
        this.nguoithamgiahoinghis = user.nguoithamgiahoinghis;
        this.permission = user.permission;
    }
    
    public User(int id, String username, String password,String ten,String email, Date timeCreate, Boolean isDelete, int permission, Set nguoithamgiahoinghis) {
       this.id = id;
       this.username = username;
       this.password = password;
       this.ten = ten;
       this.email = email;
       this.timeCreate = timeCreate;
       this.isDelete = isDelete;
       this.permission = permission;
       this.nguoithamgiahoinghis = nguoithamgiahoinghis;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    
    
    public Date getTimeCreate() {
        return this.timeCreate;
    }
    
    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }
    public Boolean getIsDelete() {
        return this.isDelete;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    
    public Set getNguoithamgiahoinghis() {
        return this.nguoithamgiahoinghis;
    }
    
    public void setNguoithamgiahoinghis(Set nguoithamgiahoinghis) {
        this.nguoithamgiahoinghis = nguoithamgiahoinghis;
    }
    
}


