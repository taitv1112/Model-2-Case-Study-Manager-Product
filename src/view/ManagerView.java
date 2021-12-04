package view;

import controller.ManagerController;
import model.Manager;

import java.io.IOException;
import java.util.Scanner;

public class ManagerView {
    Scanner sc = new Scanner(System.in);
    ManagerController managerController = new ManagerController();

    public void viewCreate(String type) throws IOException {
        while (true) {
            if (type.equals(Manager.USER)) {
                Manager manager = getManager(Manager.USER);
                managerController.addManager(manager);
            } else if (type.equals(Manager.STAFF)) {
                Manager manager = getManager(Manager.STAFF);
                managerController.addManager(manager);
            } else if (type.equals(Manager.ADMIN)) {
                Manager manager = getManager(Manager.ADMIN);
                managerController.addManager(manager);
            }
            System.out.println("Create success !");
            System.out.println("Enter any Key to continue create - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                return;
            }
        }
    }

    private Manager getManager(String type) {
        sc.nextLine();
        System.out.println("Enter userManager ");
        String userManager = sc.nextLine();
        System.out.println("Enter password ");
        String password = sc.nextLine();
        if (type.equals("ADMIN")) {
            Manager manager = new Manager(userManager, password);
            return manager;
        }
        System.out.println("Enter name  ");
        String name = sc.nextLine();
        System.out.println("Enter date of birth");
        String dateOfBirth = sc.nextLine();
        System.out.println("Enter age");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter phone number");
        String phone = sc.nextLine();
        System.out.println("Enter address");
        String address = sc.nextLine();
        System.out.println("Enter email");
        String email = sc.nextLine();
        if (type.equals("USER")) {
            Manager manager = new Manager(userManager, password, name, dateOfBirth, age, phone, address, email);
            return manager;
        } else if (type.equals("STAFF")) {
            System.out.println("Enter salary");
            double salary = sc.nextInt();
            Manager manager = new Manager(userManager, password, name, dateOfBirth, age, salary, phone, address, email);
            return manager;
        }
        return null;
    }

    public void viewDeleteManager() throws IOException {
        while (true) {
            System.out.println("Enter userManager want delete ");
            String id = sc.nextLine();
            if (managerController.deleteManager(id)) {
                managerController.showListManager();
                System.out.println("Delete success");
            } else {
                System.err.println("UserManager not found");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuManager();
            }
        }
    }

    public void viewEditManager() throws IOException {
        while (true) {
            System.out.println("Enter idManager want edit ");
            sc.nextLine();
            String userManager = sc.nextLine();
            int index = managerController.findIndexByUserName(userManager);
            if (index > -1) {
                if (managerController.showListManager().get(index).getRole().equals("USER")) {
                    managerController.editManager(index, getManager(Manager.USER));
                    managerController.showListManager();
                } else if (managerController.showListManager().get(index).getRole().equals(Manager.STAFF)) {
                    managerController.editManager(index, getManager(Manager.STAFF));
                    managerController.showListManager();
                } else if (managerController.showListManager().get(index).getRole().equals(Manager.ADMIN)) {
                    managerController.editManager(index, getManager(Manager.ADMIN));
                    managerController.showListManager();
                }
                System.out.println("Edit success");
            } else {
                System.err.println("Edit Error. UserManager not found !");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuManager();
            }
        }
    }

    public void viewShowManager() throws IOException {
        for (Manager manager : managerController.showListManager()) {
            System.out.println(manager);
        }
    }

    public void viewMenu() throws IOException {
        while (true) {
            System.out.println("Welcome to Manager Product App");
            System.out.println("1.Login");
            System.out.println("2.Register");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: viewLogin();break;
                case 2:viewCreate(Manager.USER);break;
            }
        }
    }

    private void viewLogin() throws IOException {
        sc.nextLine();
        System.out.println("Enter account");
        String userName = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        if (managerController.loginAdmin(userName, password)) {
            menuAdmin();
        } else if (managerController.loginStaff(userName, password)) {
            menuStaff();
        }else if(managerController.loginUser(userName, password)){
            menuUser();
        }else {
            System.err.println("Account or password wrong. Please try again or exit ");
        }
        System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
        String back = sc.next();
        if (back.equalsIgnoreCase("quit")) {
            viewMenu();
        }
    }


    public void menuUser() throws IOException {
        ProductView productView = new ProductView();
        CategoryView categoryView = new CategoryView();
        while (true) {
            System.out.println("Menu Staff ");
            System.out.println("1.Category Management");
            System.out.println("2.Product Management");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    categoryView.viewMenu();
                    break;
                case 2:
                    productView.viewMenuProduct();
                    break;
            }
        }
    }

    public void menuStaff() throws IOException {
        ProductView productView = new ProductView();
        CategoryView categoryView = new CategoryView();
        while (true) {
            System.out.println("Menu Staff ");
            System.out.println("1.Category Management");
            System.out.println("2.Product Management");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    categoryView.viewMenu();
                    break;
                case 2:
                    productView.viewMenuProduct();
                    break;
            }
        }
    }
    public void menuAdmin() throws IOException {
        ProductView productView = new ProductView();
        CategoryView categoryView = new CategoryView();
        ManagerView managerView = new ManagerView();
        CartView cartView = new CartView();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Manager ");
            System.out.println("1.Category Management");
            System.out.println("2.Product Management");
            System.out.println("3.Manager Management");
            System.out.println("4.Cart Management");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    categoryView.viewMenu();
                    break;
                case 2:
                    productView.viewMenuProduct();
                    break;
                case 3:
                    managerView.viewMenuManager();
                    break;
                case 4:cartView.viewCreateCart();
            }
        }
    }

    public void viewMenuManager() throws IOException {
        while (true) {
            System.out.println("Menu Manager");
            System.out.println("1. Add admin");
            System.out.println("2. Edit Manager");
            System.out.println("3. Delete Manager");
            System.out.println("4. Show Manager");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    viewCreateManager();
                case 2:
                    viewEditManager();
                    break;
                case 3:
                    viewDeleteManager();
                    break;
                case 4:
                    viewShowManager();
                    break;
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                return;
            }
        }
    }

    private void viewCreateManager() throws IOException {
        int choice;
        while (true) {
            System.out.println("1. Create admin");
            System.out.println("2. Create staff");
            System.out.println("1. Create user");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    viewCreate(Manager.ADMIN);
                    break;
                case 2:
                    viewCreate(Manager.STAFF);
                    break;
                case 3:
                    viewCreate(Manager.USER);
                    break;
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuManager();
            }
        }
    }
}
