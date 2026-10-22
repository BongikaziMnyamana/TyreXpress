
package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.AdminService;
import java.util.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Admin create(@RequestBody Admin admin) {
        return service.create(admin);
    }

    @GetMapping("/read/{adminId}")
    public Admin read(@PathVariable Long adminId) {
        return service.read(adminId);
    }

    @PutMapping("/update")
    public Admin update(@RequestBody Admin admin) {
        return service.update(admin);
    }

    @DeleteMapping("/delete/{adminId}")
    public void delete(@PathVariable Long adminId) {
        service.delete(adminId);
    }

    @GetMapping("/getAll")
    public List<Admin> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-user/{userId}")
    public Admin findByUserId(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }
}
