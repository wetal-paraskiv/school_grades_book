package md.wetal.school_grades_book.repositories;

import md.wetal.school_grades_book.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM grades g WHERE concat(g.date, ' ', g.grade, ' ', g.student.firstName, " +
           "' ', g.student.lastName, ' ', g.course.name) LIKE %?1%")
    public List<Grade> getAllBySearch(String q);
}
