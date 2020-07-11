/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.DAO;

import btl2.entiny.Diadiem;
import btl2.entiny.Hoinghi;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class DiadiemDAO {
    public static List<Diadiem> getAll(){
        List<Diadiem> diadiems = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            diadiems =  session.createQuery("from Diadiem ").list();
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return diadiems;
    }
    
    public static boolean addDiadiem(Diadiem diadiem) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(diadiem);
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
    
    public static boolean checkValid(Diadiem diadiem, Date startDate, Date endDate){
        List<Diadiem> diadiems = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Query q =  session.createQuery("from Diadiem dm, Hoinghi hn where hn.diadiem = dm.id and dm.id = "+diadiem.getId()
                    +" and hn.thoigiankt >= :startDate and hn.thoigianbd <= :startDate or hn.diadiem = dm.id and dm.id = "+diadiem.getId()
                    +" and hn.thoigiankt >= :endDate and hn.thoigianbd <= :endDate");
            q.setTimestamp("startDate",startDate);
            q.setTimestamp("endDate",endDate);
            diadiems = q.list();
            session.close();
            if (diadiems.isEmpty()){
                return true;
            }
            else return false;
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        
        return false;
    }
    public static boolean checkValid(Diadiem diadiem, Date startDate, Date endDate,Hoinghi hn){
        List<Diadiem> diadiems = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Query q =  session.createQuery("from Diadiem dm, Hoinghi hn where hn.diadiem = dm.id and hn.id != :id and dm.id = "+diadiem.getId()
                    +" and hn.thoigiankt >= :startDate and hn.thoigianbd <= :startDate or hn.diadiem = dm.id and hn.id != :id and dm.id = "+diadiem.getId()
                    +" and hn.thoigiankt >= :endDate and hn.thoigianbd <= :endDate");
            q.setTimestamp("startDate",startDate);
            q.setTimestamp("endDate",endDate);
            q.setParameter("id", hn.getId());
            diadiems = q.list();
            session.close();
            if (diadiems.isEmpty()){
                return true;
            }
            else return false;
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        
        return false;
    }
}
