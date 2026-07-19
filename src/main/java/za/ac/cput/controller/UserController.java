package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.*;
import java.util.*;

public class UserController {
    private static UserController controller = null;
    private IUserService service;

    private UserController() {
        service = UserService.getService();
    }
    public static UserController getController() {
        if (controller == null) {
            controller = new UserController();
        }
        return controller;
    }

    public User create(User user) {
        return service.create(user);
    }

    public User read(String userId) {
        return service.read(userId);
    }

    public User update(User user) {
        return service.update(user);
    }

    public boolean delete(String userId) {
        return service.delete(userId);
    }

    public List<User> getAll() {
        return service.getAll();
    }
}
