package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.service.IOrderLineService;
import za.ac.cput.service.IOrderService;
import za.ac.cput.service.OrderLineService;
import za.ac.cput.service.OrderService;

import java.util.List;
@RestController
@RequestMapping("/orderLine")
public class OrderLineController {
@Autowired
    private static OrderLineController controller = null;
    private IOrderLineService service;

    public OrderLineController(OrderLineService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public OrderLine create(@RequestBody OrderLine orderLine) {
        return service.create(orderLine);
    }

    @GetMapping("/read/{id}")
    public OrderLine read(@PathVariable Long id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public OrderLine update(@RequestBody OrderLine orderLine) {
        return service.update(orderLine);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<OrderLine> getAll() {
        return service.getAll();
    }
}

