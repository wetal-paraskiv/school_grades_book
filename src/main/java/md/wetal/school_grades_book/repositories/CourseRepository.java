package md.wetal.school_grades_book.repositories;

import md.wetal.school_grades_book.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM courses c WHERE concat(c.name, ' ', c.teacher.firstName, ' ', c.teacher.lastName) LIKE %?1%")
    public List<Course> getAllBySearch(String q);

}
