package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao userDaoImpl = new UserDaoHibernateImpl();

        userDaoImpl.createUsersTable();
        userDaoImpl.saveUser("Anton", "Sukhankin", (byte) 22);
        userDaoImpl.saveUser("Geralt", "of Rivia", (byte) 100);
        userDaoImpl.saveUser("Yennefer", "of Vengerberg", (byte) 95);
        userDaoImpl.saveUser("Yarpen", "Zigrin", (byte) 42);
        List<User> list = userDaoImpl.getAllUsers();
        System.out.println(list);
        userDaoImpl.cleanUsersTable();
        userDaoImpl.dropUsersTable();

    }

}