/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl2.DAO;

import btl2.entiny.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class UserDAO {
    public static User get(int id) {
        User user = null;
        try
        {
            
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            user = (User) session.get(User.class, id);
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return user;
    }
    
    public static List<User>  getAllUser() {
       List<User> user = null;
        try
        {   Transaction tx = null;
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            user =  session.createQuery("from User user where user.permission = 0").list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        }
        catch ( HibernateException e ){
            System.err.println(e);
        }
        return user;
    }
    
     public static List<User> check(String nameString) {
        List<User> user = null;
        String query = "FROM User WHERE username = '" +nameString+"'";
        try
        {
            Transaction tx=null;
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            user = (List<User>) session.createQuery(query).list();
            tx=session.beginTransaction();
            tx.commit();
            session.close();
        }
        catch ( Exception e ){
            System.err.println(e);
        }
        
        if (user!= null && user.isEmpty())
        {
            return null;
        }
        return user;
    }
     
     public static boolean addNewUser(String nameString,String passwordString) {
        User user = new User(nameString,passwordString);
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(user);
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
     
     public static boolean updateUser(User user) {
        Transaction transaction = null;
        try
        {
            //if use open then flush and close session
            //if getCurrent then session.getTransaction().commit(); faster
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(user);
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
