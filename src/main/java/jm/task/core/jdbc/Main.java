package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Antony", "Soprano", (byte) 42);
        userService.saveUser("Elliot", "Sanders", (byte) 36);
        userService.saveUser("Evgeny", "Fedorov", (byte) 28);
        userService.saveUser("Artyem", "Pirogov", (byte) 32);
        System.out.println("+-------+---------------+--------------------+----------+");
        System.out.println("|   id  |     name      |    lastName        |    age   |");
        System.out.println("+-------+---------------+--------------------+----------+");
        userService.getAllUsers();
        userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
