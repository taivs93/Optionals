package manager;

import entity.Address;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManager {
    private static List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UserManager.users = users;
    }

    public User addUser(User user) {
        findUserById(user.getId()).ifPresentOrElse(
                existedUser -> {
                    System.out.println("User existed");
                },
                () -> {
                    users.add(user);
                    System.out.println("User added successfully");
                }
        );
        return user;
    }

    public User getUserById(String id){
        return findUserById(id).orElseThrow(() -> new RuntimeException("Can not find user id"));
    }

    public List<User> getUserByCity(String city){
        return users.stream()
                .filter(user -> getUserCity(user).map(userCity -> userCity.equals(city))
                        .orElse(false)).toList();
    }

    public List<User> getUserByEmailDomain(String emailDomain) {
        return users.stream()
                .filter(user -> getUserEmailDomain(user).map(userEmail -> userEmail.substring(userEmail
                                .indexOf('@') + 1).equals(emailDomain))
                        .orElse(false)).toList();
    }
    public boolean isUserFromCountry(String id, String country) {
        return findUserById(id)
                .flatMap(user -> Optional.ofNullable(user.getAddress()))
                .map(Address::getCountry)
                .filter(countryName -> countryName.equalsIgnoreCase(country))
                .isPresent();
    }

    public String getUserPhoneNumber(String id){
        return findUserById(id).map(User::getPhoneNumber).orElse("No phone number");
    }


    private static Optional<User> findUserById(String id){
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
    private static Optional<String> getUserEmailDomain(User user){
        return findUserById(user.getId()).map(User::getEmail);
    }
    private static Optional<String> getUserCity(User user){
        return Optional.ofNullable(user.getAddress())
                .flatMap(address -> Optional.ofNullable(address.getCity()));
    }
}
