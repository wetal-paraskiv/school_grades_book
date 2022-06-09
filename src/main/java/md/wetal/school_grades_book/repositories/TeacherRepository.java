package md.wetal.school_grades_book.repositories;

import md.wetal.school_grades_book.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM teachers t WHERE concat(first_name, ' ', last_name, ' ', email) LIKE %?1%")
    public List<Teacher> getAllBySearch(String q);

}
