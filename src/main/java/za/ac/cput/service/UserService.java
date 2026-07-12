package za.ac.cput.service;

import za.ac.cput.domain.*;
import za.ac.cput.repository.*;
import java.util.*;

public class UserService implements IUserService {
    private static UserService service = null;
    private IUser repository;

    private UserService() {
        repository = UserRepository.getRepository();
    }
    public static UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    @Override
    public User create(User user) {
        return repository.create(user);
    }

    @Override
    public User read(String user_id) {
        return repository.read(user_id);
    }

    @Override
    public User update(User user) {
       return repository.update(user);
    }

    @Override
    public boolean delete(String user_id) {
        return repository.delete(user_id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
