package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUser_UserId(Long userId);

}
