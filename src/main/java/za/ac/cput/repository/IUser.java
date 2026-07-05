package za.ac.cput.repository;

import za.ac.cput.domain.*;
import java.util.*;

public interface IUser extends IRepository<User,String> {
    List<User> getAllUsers();
}
