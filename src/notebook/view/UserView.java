package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;


import java.util.Locale;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = null;


        while (true) {

            String command = prompt("Введите команду: ").toUpperCase();
            String userId;
            User user;
            try {

                com = Commands.valueOf(command);
                if (com == Commands.EXIT) return;

                switch (com) {
                    case CREATE:
                        user = createUser(true);
                        userController.saveUser(user);
                        break;
                    case READ:
                        userId = prompt("Введите идентификатор записи: ");
                        user = userController.readUser(Long.parseLong(userId));
                        System.out.println(user);
                        break;
                    case READALL:
                        System.out.println(userController.readAll());
                        break;
                    case UPDATE:
                        userId = prompt("Введите идентификатор записи: ");
                        user = userController.readUser(Long.parseLong(userId));
                        System.out.println("\n" + user);
                        userController.updateUser(userId, createUser(false));
                        break;
                    case DELETE:
                        Long userIdDelete = Long.valueOf(prompt("Enter user id: "));
                        userController.deleteUser(userIdDelete);
                        System.out.println("Пользователь удален");
                        break;
                    case LIST:
                        System.out.println("EXIT - выход из программы");
                        System.out.println("CREATE - создать пользователя");
                        System.out.println("READ - вывести пользователя");
                        System.out.println("READALL - вывести список пользователей");
                        System.out.println("UPDATE - изменить данные пользователя");
                        System.out.println("DELETE - удалить пользователя");
                        System.out.println("LIST - список всех команд");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Команда " + command + " не найдена. Cписок команд - LIST");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            }
    }




    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
    public String checkLine(String str) {
        str = str.trim().replace(" ", "");
        if (!str.isEmpty()) {
            return str;
        } else {
            System.out.println("Значение не может быть пустым.\n");
            str = prompt("Введите корректные данные: ");
            return checkLine(str);
        }
    }

    private User createUser(boolean isCheckNeed) {
        String firstName = isCheckNeed ? checkLine(prompt("Имя: ")) : prompt("Имя: ");
        String lastName = isCheckNeed ? checkLine(prompt("Фамилия: ")) : prompt("Фамилия: ");
        String phone = isCheckNeed ? checkLine(prompt("Номер телефона: ")) : prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

}
