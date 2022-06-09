package md.wetal.school_grades_book.service;

import md.wetal.school_grades_book.entities.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllBySearch(String q);

    List<Course> getAll();

    Course getOne(Long id);

    Course saveNew(Course course);

    Course update(Course course, Long id);

    void delete(Long id);
}
