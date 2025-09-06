package Service;

import Model.User;

public interface UserService {
    User login(String username, String password);
    User findByUsername(String username);
    boolean register(User user);
}
