package md.wetal.school_grades_book.service.Implementation;

import md.wetal.school_grades_book.entities.Student;
import md.wetal.school_grades_book.exceptions.ResourceNotFoundException;
import md.wetal.school_grades_book.repositories.StudentRepository;
import md.wetal.school_grades_book.service.StudentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

//    @Qualifier("firstStudentService") // annotation is used to differentiate beans of the same interface
    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllBySearch(String q) {
        return studentRepository.getAllBySearch(q);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getOne(Long id) {
        return studentRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
    }

    @Override
    public Student saveNew(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student, Long id) {
        Student targetedStudent = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedStudent.setFirstName(student.getFirstName());
        targetedStudent.setLastName(student.getLastName());
        targetedStudent.setEmail(student.getEmail());
        studentRepository.save(targetedStudent);
        return targetedStudent;
    }

    @Override
    public void delete(Long id) {
        studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        studentRepository.deleteById(id);
    }
}
