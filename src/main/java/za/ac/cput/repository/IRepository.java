package za.ac.cput.repository;

import java.util.*;

public interface IRepository <T,ID>{
    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete(ID t);

    List<T> getAll();


}
