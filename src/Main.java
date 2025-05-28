import entity.Address;
import entity.Selection;
import entity.User;
import manager.UserManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();
    public static void main(String[] args) {
        userManager.addUser(new User("U001", "Alice", "alice@gmail.com", new Address("Hanoi", "123 A St", "100000", "Vietnam"), "0912345678"));
        userManager.addUser(new User("U002", "Bob", "bob@yahoo.com", new Address("Saigon", "456 B St", "700000", "Vietnam"), "0987654321"));
        userManager.addUser(new User("U003", "Charlie", "charlie@gmail.com", new Address("Hanoi", "789 C St", "100001", "Vietnam"), "0909123456"));
        userManager.addUser(new User("U004", "David", "david@outlook.com", new Address("Danang", "101 D St", "550000", "Vietnam"), "0911223344"));
        userManager.addUser(new User("U005", "Eve", "eve@gmail.com", new Address("Hue", "202 E St", "530000", "Vietnam"), "0933445566"));
        userManager.addUser(new User("U006", "Frank", "frank@yahoo.com", new Address("Saigon", "303 F St", "700001", "Vietnam"), "0977889900"));
        userManager.addUser(new User("U007", "Grace", "grace@gmail.com", new Address("Hanoi", "404 G St", "100002", "Vietnam"), "0966554433"));
        userManager.addUser(new User("U008", "Hannah", "hannah@zmail.com", new Address("Hue", "505 H St", "530001", "Vietnam"), "0955667788"));
        userManager.addUser(new User("U009", "Ivan", "ivan@gmail.com", new Address("Danang", "606 I St", "550001", "Vietnam"), "0944221100"));
        userManager.addUser(new User("U010", "Judy", "judy@protonmail.com", new Address("Saigon", "707 J St", "700002", "Vietnam"), "0933556677"));
        User user;
        boolean isInUsed = true;
        int choice;
        while(isInUsed){
            menu();
            choice = scanner.nextInt();
            Selection selection = Selection.getSelectionByValue(choice);
            switch (selection){
                case ADD -> {
                    user = inputUser();
                    System.out.println(userManager.addUser(user));
                }
                case FINDBYID -> {
                    System.out.print("Nhập ID: ");
                    String id = scanner.next();
                    System.out.println(userManager.getUserById(id));
                }
                case FINDBYCITY -> {
                    scanner.nextLine();
                    System.out.print("Nhập thành phố: ");
                    String city = scanner.nextLine();
                    List<User> userList =  userManager.getUserByCity(city);
                    if(userList.isEmpty()){
                        System.out.println("Not found any user");
                    } else userList.forEach(System.out::println);
                }
                case FINDBYEMAIL -> {
                    System.out.print("Nhập email: ");
                    String email = scanner.next();
                    List<User> userList = userManager.getUserByEmailDomain(email);
                    if(userList.isEmpty()){
                        System.out.println("Not found any user");
                    } else userList.forEach(System.out::println);
                }
                case ISUSERINCOUNTRY -> {
                    System.out.print("Nhập id: ");
                    String id = scanner.next();
                    System.out.print("Nhập quốc gia: ");
                    scanner.nextLine();
                    String country = scanner.nextLine();
                    if (userManager.isUserFromCountry(id, country)){
                        System.out.println("Yes");
                    }
                    else System.out.println("No");
                }
                case GETUSERPHONENUMBER -> {
                    String id = scanner.next();
                    System.out.println(userManager.getUserPhoneNumber(id));
                }
                case EXIT -> {
                    isInUsed = false;
                }
            }
        }
    }

    private static void menu(){
        System.out.println("Menu");
        for (Selection selection : Selection.values()){
            System.out.println(selection.getValue() + ". " + selection.getDescription());
        }
        System.out.print("Lựa chọn: ");
    }

    private static User inputUser() {
        User user = new User();

        System.out.print("Nhập ID: ");
        user.setId(scanner.next());
        scanner.nextLine();
        System.out.print("Nhập tên: ");
        user.setUserName(scanner.nextLine());

        System.out.print("Nhập email domain: ");
        user.setEmail(scanner.next());

        System.out.print("Nhập số điện thoại: ");
        user.setPhoneNumber(scanner.next());

        System.out.println("Nhập địa chỉ:");
        scanner.nextLine();
        System.out.print("Thành phố: ");
        String city = scanner.nextLine();

        System.out.print("Đường: ");
        String street = scanner.nextLine();

        System.out.print("Mã zip: ");
        String zipCode = scanner.nextLine();

        System.out.print("Quốc gia: ");
        String country = scanner.nextLine();

        Address address = new Address(city, street, zipCode, country);
        user.setAddress(address);

        return user;
    }
}