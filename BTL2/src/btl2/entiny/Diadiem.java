package btl2.entiny;
// Generated Jun 29, 2020 9:30:22 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Diadiem generated by hbm2java
 */
public class Diadiem  implements java.io.Serializable {


     private int id;
     private String ten;
     private String diachi;
     private Integer succhua;
     private Set hoinghis = new HashSet(0);

    public Diadiem() {
    }

	
    public Diadiem(int id) {
        this.id = id;
    }
    public Diadiem(int id, String ten, String diachi, Integer succhua, Set hoinghis) {
       this.id = id;
       this.ten = ten;
       this.diachi = diachi;
       this.succhua = succhua;
       this.hoinghis = hoinghis;
    }
    
    public Diadiem(String ten, String diachi, Integer succhua) {
       this.ten = ten;
       this.diachi = diachi;
       this.succhua = succhua;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getDiachi() {
        return this.diachi;
    }
    
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public Integer getSucchua() {
        return this.succhua;
    }
    
    public void setSucchua(Integer succhua) {
        this.succhua = succhua;
    }
    public Set getHoinghis() {
        return this.hoinghis;
    }
    
    public void setHoinghis(Set hoinghis) {
        this.hoinghis = hoinghis;
    }

    @Override
    public String toString() {
        return ten+". Địa chỉ: "+diachi+". Sức chứa: "+succhua; //To change body of generated methods, choose Tools | Templates.
    }

    
}


