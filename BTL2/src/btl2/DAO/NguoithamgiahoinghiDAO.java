/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.DAO;

import btl2.entiny.Hoinghi;
import btl2.entiny.Nguoithamgiahoinghi;
import btl2.entiny.NguoithamgiahoinghiId;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class NguoithamgiahoinghiDAO {
    public static Nguoithamgiahoinghi get(NguoithamgiahoinghiId id) {
        Nguoithamgiahoinghi row = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            row = (Nguoithamgiahoinghi) session.get(Nguoithamgiahoinghi.class, id);
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        
        return row;
    }
    
    public static List<Nguoithamgiahoinghi> getAllValid() {
        //final List<Nguoithamgiahoinghi> row = new ArrayList<>();
        List<Nguoithamgiahoinghi> row=new ArrayList<>();
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Transaction tx=null;
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            List<Object> objects = session.createQuery("from Nguoithamgiahoinghi ntg, Hoinghi hn,User user where ntg.isallow = 0 and hn.id = ntg.hoinghi and hn.thoigiankt >= current_time and ntg.user = user.id").list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
            if (objects!=null && !objects.isEmpty()){
                objects.forEach((val)->{
                   Object[] os = (Object[]) val;
                   Nguoithamgiahoinghi ntg = (Nguoithamgiahoinghi) os[0];
                   row.add(ntg);
                });
            }
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }  
        return row;
    }
    
    public static boolean allow(Nguoithamgiahoinghi ntg) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(ntg);
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
    
    public static boolean reject(Nguoithamgiahoinghi ntg) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(ntg);
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
