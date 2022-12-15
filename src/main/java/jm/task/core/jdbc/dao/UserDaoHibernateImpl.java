package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String q;
        q = "create table if not exists schema_name.user_table\n" +
                "(\n" +
                "    id       int auto_increment,\n" +
                "    name     varchar(40) null,\n" +
                "    lastName varchar(40) null,\n" +
                "    age      int         null,\n" +
                "    constraint schema_name_pk\n" +
                "        primary key (id)\n" +
                ");\n";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(q);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String q;
        q = "drop table if exists schema_name.user_table;";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(q);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String q = "DELETE FROM schema_name.user_table";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(q);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}

