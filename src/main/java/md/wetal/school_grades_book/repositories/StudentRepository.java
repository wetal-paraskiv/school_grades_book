package md.wetal.school_grades_book.repositories;

import md.wetal.school_grades_book.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT st FROM students st WHERE concat(first_name, ' ', last_name) LIKE %?1%")
    public List<Student> getAllBySearch(String q);

}
