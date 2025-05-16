import entity.Address;
import entity.Selection;
import entity.User;
import manager.UserManager;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager();
    public static void main(String[] args) {
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
                    userManager.addUser(user);
                }
                case FINDBYID -> {
                    System.out.print("Nhập ID: ");
                    String id = scanner.next();
                    userManager.getUserById(id);
                }
                case FINDBYCITY -> {
                    scanner.nextLine();
                    System.out.print("Nhập thành phố: ");
                    String city = scanner.nextLine();
                    userManager.getUserByCity(city);

                }
                case FINDBYEMAIL -> {
                    System.out.print("Nhập email: ");
                    String email = scanner.next();
                    userManager.getUserByEmailDomain(email);
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