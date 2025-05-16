package manager;

import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManager {
    private static final List<User> users = new ArrayList<>();

    public void addUser(User user){
        Optional<User> existedUser = findUserById(user.getId());
        if (existedUser.isEmpty()){
            users.add(user);
            System.out.println("Add user successfully");
        } else System.out.println("User id was existed");
    }

    public void getUserById(String id){
        Optional<User> existedUser = findUserById(id);
        if (existedUser.isEmpty()){
            System.out.println("User not found");
        } else System.out.println(existedUser.get());
    }

    public void getUserByCity(String city){
        users.stream()
                .filter(user -> getUserCity(user).map(userCity -> userCity.equals(city))
                        .orElse(false)).forEach(System.out::println);
    }

    public void getUserByEmailDomain(String emailDomain) {
        users.stream()
                .filter(user -> getUserEmailDomain(user).map(userEmail -> userEmail.substring(userEmail
                                .indexOf('@') + 1).equals(emailDomain))
                        .orElse(false)).forEach(System.out::println);
    }
    private static Optional<User> findUserById(String id){
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
    private static Optional<String> getUserEmailDomain(User user){
        return Optional.ofNullable(user.getEmail());
    }
    private static Optional<String> getUserCity(User user){
        return Optional.ofNullable(user.getAddress().getCity());
    }
}
