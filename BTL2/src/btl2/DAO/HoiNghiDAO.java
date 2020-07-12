/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.DAO;

import btl2.entiny.Hinhanh;
import btl2.entiny.Hoinghi;
import btl2.entiny.Nguoithamgiahoinghi;
import btl2.entiny.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class HoiNghiDAO {
    public static Hoinghi get(int id) {
        Hoinghi hoinghi = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            hoinghi = (Hoinghi) session.get(Hoinghi.class, id);
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return hoinghi;
    }

    public static List<Hoinghi> getAll(){
        List<Hoinghi> hoinghis = null;
        try
        {
            Transaction tx=null;
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            hoinghis =  session.createQuery("from Hoinghi ").list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return hoinghis;
    }
    
    public static List<Hoinghi> getAllInf(){
        List<Hoinghi> hoinghis = null;
        try
        {
            Transaction tx=null;
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            hoinghis = session.createQuery("select hn from Hoinghi hn, Diadiem dm where hn.diadiem = dm.id").list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return hoinghis;
    }
    
    public static List<Hoinghi> getAllInfValid(){
        List<Hoinghi> hoinghis = null;
        try
        {
            Transaction tx=null;
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            hoinghis = session.createQuery("select hn from Hoinghi hn, Diadiem dm where hn.diadiem = dm.id and hn.thoigianbd > current_time").list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return hoinghis;
    }
    
    public static List<Hoinghi> listUserJoin(User user){
        List<Hoinghi> hoinghis =  new ArrayList<>();
        try
        {
            Transaction tx=null;
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            List<Object> objects =  session.createQuery("from Hoinghi hn, Diadiem dm, Nguoithamgiahoinghi ntg,User user where user.id = ntg.user and ntg.hoinghi = hn.id and hn.diadiem = dm.id and user.id = "+user.getId()).list();
            if (!objects.isEmpty()){
                for (Object object : objects) {
                    Object[] objects1 = (Object[]) object;
                    Hoinghi hn = (Hoinghi) objects1[0];
                    hoinghis.add(hn);
                }
            }
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return hoinghis;
    }
    
    public static int isUserJoin(User user, Hoinghi hn){
         List<Nguoithamgiahoinghi> ntg = null;
        try {
            Transaction tx=null;
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            ntg = session.createQuery("from Nguoithamgiahoinghi ntg where ntg.user = "+user.getId()+" and ntg.hoinghi = "+hn.getId()).list();
            System.out.println(ntg);
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        if (ntg==null || ntg!=null && ntg.isEmpty()){
            return 0;
        }
        if (ntg.get(0).getIsallow()==1)
             return 2;
         else {
             return 1;
         } 
    }
    
     public static boolean addUserJoin(Nguoithamgiahoinghi ntg){
        Transaction transaction = null;
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(ntg);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } 
            System.err.println(e);
        }
        return false;
    }
     
     public static int addHN(Hoinghi hn) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(hn);
            transaction.commit();
            List<Hoinghi> hoinghis = session.createQuery("from Hoinghi hn").list();
            session.close();  
            if (!hoinghis.isEmpty()){
                return hoinghis.get(hoinghis.size()-1).getId();
            }
        }
        catch ( HibernateException e ){
            if (transaction != null)
                transaction.rollback();
            System.err.println(e);
            return -1;
        }
        return -1;
    }
     
     public static boolean addImg(Hinhanh hinhanh) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(hinhanh);
            transaction.commit();
            session.close();  
        }
        catch ( HibernateException e ){
            if (transaction != null)
                transaction.rollback();
            System.err.println(e);
            return false;
        }
        return true;
    }
     
    public static String getImg(Hoinghi hn){
        List<Hinhanh> hinhanhs = null;
        try
        {
            Transaction tx=null;
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            hinhanhs = session.createQuery("from Hinhanh ha where ha.hoinghi = "+hn.getId()).list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
            if (hinhanhs.isEmpty()){
                return "";
            } else {
                return hinhanhs.get(0).getHinhanh();
            }
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return "";
    }
    
    public static boolean updateHoinghi(Hoinghi hn) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hn);
            transaction.commit();
            session.close();
        }
        catch ( HibernateException e ){
            if (transaction != null)
                transaction.rollback();
            System.err.println(e);
            return false;
        }
        return true;
    }
}
