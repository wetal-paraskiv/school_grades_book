package md.wetal.school_grades_book.service.Implementation;

import md.wetal.school_grades_book.entities.Course;
import md.wetal.school_grades_book.exceptions.ResourceNotFoundException;
import md.wetal.school_grades_book.repositories.CourseRepository;
import md.wetal.school_grades_book.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllBySearch(String q) {
        return courseRepository.getAllBySearch(q);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getOne(Long id) {
        return courseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Course saveNew(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course, Long id) {
        Course targetedCourse = courseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedCourse.setName(course.getName());
        targetedCourse.setTeacher(course.getTeacher());
        courseRepository.save(targetedCourse);
        return targetedCourse;
    }

    @Override
    public void delete(Long id) {
        courseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        courseRepository.deleteById(id);
    }
}
