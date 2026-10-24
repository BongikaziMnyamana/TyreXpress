package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.IProductRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private final IProductRepository productRepository;

    public StockService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void deductStock(Order order) {

        if (order.isStockDeducted()) {
            return;
        }

        if (order.getOrderLines() == null || order.getOrderLines().isEmpty()) {
            throw new IllegalStateException("Cannot deduct stock from an empty order");
        }

        Map<Long, Integer> quantities = new HashMap<>();

        for (OrderLine line : order.getOrderLines()) {

            if (line.getQuantity() <= 0) {
                throw new IllegalArgumentException("Product quantity must be greater than zero");
            }

            Long productId = Long.valueOf(line.getProductId());

            quantities.merge(productId, line.getQuantity(), Integer::sum);
        }

        // Process products in ID order to reduce deadlock risk.
        for (Long productId : quantities.keySet().stream().sorted().toList()) {

            Product product = productRepository.findByIdForUpdate(productId).orElseThrow(() -> new IllegalStateException("Product not found: " + productId));

            int quantity = quantities.get(productId);

            if (product.getStock() < quantity) {
                throw new IllegalStateException("Insufficient stock for " + product.getName());
            }

            product.setStock(product.getStock() - quantity);

            productRepository.save(product);
        }

        order.setStockDeducted(true);
    }
}
