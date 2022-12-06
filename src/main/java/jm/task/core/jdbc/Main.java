package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Anton", "Sukhankin", (byte) 22);
        userDaoJDBC.saveUser("Geralt", "of Rivia", (byte) 100);
        userDaoJDBC.saveUser("Yennefer", "of Vengerberg", (byte) 95);
        userDaoJDBC.saveUser("Yarpen", "Zigrin", (byte) 42);
        List<User> list = userDaoJDBC.getAllUsers();
        System.out.println(list);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }

}