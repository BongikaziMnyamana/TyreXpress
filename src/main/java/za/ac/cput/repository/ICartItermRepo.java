package za.ac.cput.repository;

import za.ac.cput.domain.CartItem;
import java.util.List;

public interface ICartItermRepo extends IRepository<CartItem, String> {
    boolean delete(String cartItemId);
    List<CartItem> getAll();
}