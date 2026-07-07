package za.ac.cput.repository;

import za.ac.cput.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUser{

    private static IUser repository= null;
    private List<User> userList;
    private UserRepository(){
        userList =new ArrayList<>();
    }
    public static IUser getRepository(){
        if(repository==null){
            repository=new UserRepository();
        }
        return repository;
    }

    @Override
    public User create(User user) {
        boolean success = userList.add(user);
        if(success){
            return user;
        }
        return null;
    }

    @Override
    public User read(String user_id) {
        for (User user: userList){
            if (user.getUser_Id().equals(user_id)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(User user) {
        String id= user.getUser_Id();
        User oldUser = read(id);

        if(oldUser == null){
            return null;
        }
        boolean success = userList.remove(oldUser);
        if(!success){
            return user;
        }
        if (userList.add(user)){
            return user;
        }
        return null;
    }

    @Override
    public boolean delete(String user_id) {
        User userToDelete = read(user_id);
        if(userToDelete == null){
            return false;
        }
        return userList.remove(userToDelete);
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

}
