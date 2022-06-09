package md.wetal.school_grades_book.service;

import md.wetal.school_grades_book.entities.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllBySearch(String q);

    List<Student> getAll();

    Student getOne(Long id);

    Student saveNew(Student student);

    Student update(Student student, Long id);

    void delete(Long id);
}
