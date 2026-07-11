package za.ac.cput.repository;

import za.ac.cput.domain.Cart;
import java.util.List;

public interface ICartRepo extends IRepository<Cart, String> {
    boolean delete(String cartId);
    List<Cart> getAll();
}