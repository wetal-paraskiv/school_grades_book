package md.wetal.school_grades_book.repositories;

import md.wetal.school_grades_book.security_mysql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
