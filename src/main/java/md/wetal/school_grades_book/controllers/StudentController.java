package md.wetal.school_grades_book.controllers;

import md.wetal.school_grades_book.entities.Student;
import md.wetal.school_grades_book.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@Controller
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @CrossOrigin
//    @GetMapping
//    public List<Person> all() {
//        return personService.getAll();
//    }

    @GetMapping("/filter")
    public String getAllByFullName(Model model, @RequestParam String q) {
        model.addAttribute("students", studentService.getAllBySearch(q));
        return "students";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Student>(studentService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/new")
    public String createForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }
    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping
    public String saveNew(@ModelAttribute("student") Student student) {
        studentService.saveNew(student);
        return "redirect:/api/students";
    }
//    @CrossOrigin
//    @PostMapping
//    public ResponseEntity<Student> save(@RequestBody Student student) {
//        return new ResponseEntity<Student>(studentService.saveNew(student), HttpStatus.CREATED);
//    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getOne(id));
        return "edit_student";
    }
    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("student") Student student,
            Model model) {
        Student targetedStudent = studentService.getOne(id);
        targetedStudent.setFirstName(student.getFirstName());
        targetedStudent.setLastName(student.getLastName());
        targetedStudent.setEmail(student.getEmail());
        studentService.update(targetedStudent, id);
        return "redirect:/api/students";
    }
//    @PutMapping("{id}")
//    public ResponseEntity<Person> update(@PathVariable("id") Long id, @RequestBody Person person) {
//        return new ResponseEntity<Person>(personService.update(person, id), HttpStatus.OK);
//    }
//    https://www.youtube.com/watch?v=HYGnVeCs0Yg

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return "redirect:/api/students";
    }
//    @CrossOrigin
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
//        personService.delete(id);
//        return new ResponseEntity<String>(
//                "person's info with id: " + id + " was successfully deleted.", HttpStatus.OK);
//    }

}
