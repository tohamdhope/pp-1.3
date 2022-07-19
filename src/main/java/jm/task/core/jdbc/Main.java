package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Antony", "Soprano", (byte) 42);
        userDao.saveUser("Elliot", "Sanders", (byte) 36);
        userDao.saveUser("Evgeny", "Fedorov", (byte) 28);
        userDao.saveUser("Artyem", "Pirogov", (byte) 32);
        System.out.println("+-------+---------------+--------------------+----------+");
        System.out.println("|   id  |     name      |    lastName        |    age   |");
        System.out.println("+-------+---------------+--------------------+----------+");
        userDao.getAllUsers();
        /*
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
         */
    }
}
