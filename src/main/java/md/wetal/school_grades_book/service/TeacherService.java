package md.wetal.school_grades_book.service;

import md.wetal.school_grades_book.entities.Student;
import md.wetal.school_grades_book.entities.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllBySearch(String q);

    List<Teacher> getAll();

    Teacher getOne(Long id);

    Teacher saveNew(Teacher teacher);

    Teacher update(Teacher teacher, Long id);

    void delete(Long id);
}
