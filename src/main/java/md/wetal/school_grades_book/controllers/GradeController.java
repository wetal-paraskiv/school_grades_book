package md.wetal.school_grades_book.controllers;

import md.wetal.school_grades_book.entities.Course;
import md.wetal.school_grades_book.entities.Grade;
import md.wetal.school_grades_book.entities.Student;
import md.wetal.school_grades_book.repositories.CourseRepository;
import md.wetal.school_grades_book.repositories.StudentRepository;
import md.wetal.school_grades_book.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/api/grades")
public class GradeController {

    GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Autowired
    private StudentRepository gradeRepository;

    @Autowired
    private StudentRepository gradeStudentRepository;

    @Autowired
    private CourseRepository gradeCourseRepository;


    @GetMapping("/filter")
    public String getAllByName(Model model, @RequestParam String q) {
        model.addAttribute("grades", gradeService.getAllBySearch(q));
        return "grades";
    }

    @GetMapping("/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<Grade> page = gradeService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<Grade> grades = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("grades", grades);
        return "grades";
    }

    @GetMapping
    public String all(Model model) {
        return getOnePage(model, 1);
    }

//    @GetMapping
//    public String all(Model model) {
//        model.addAttribute("grades", gradeService.getAll());
//        return "grades";
//    }

    @GetMapping("{id}")
    public ResponseEntity<Grade> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Grade>(gradeService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/new")
    public String createForm(Model model) {
        Grade grade = new Grade();
        model.addAttribute("grade", grade);
        List<Student> studentList = gradeStudentRepository.findAll();
        model.addAttribute("studentList", studentList);
        List<Course> courseList = gradeCourseRepository.findAll();
        model.addAttribute("courseList", courseList);
        return "new_grade";
    }
    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping
    public String saveNew(@ModelAttribute("grade") Grade grade) {
        if (grade.getCourse() != null && grade.getGrade() != 0 && grade.getStudent() != null) {
            gradeService.saveNew(grade);
            return "redirect:/api/grades";
        }
        return "redirect:/api/grades/new";
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("grade", gradeService.getOne(id));
        List<Student> studentList = gradeStudentRepository.findAll();
        model.addAttribute("studentList", studentList);
        List<Course> courseList = gradeCourseRepository.findAll();
        model.addAttribute("courseList", courseList);
        return "edit_grade";
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("grade") Grade grade,
            Model model) {
        Grade targetedGrade = gradeService.getOne(id);
        targetedGrade.setGrade(grade.getGrade());
        targetedGrade.setStudent(grade.getStudent());
        targetedGrade.setCourse(grade.getCourse());
        gradeService.update(targetedGrade, id);
        return "redirect:/api/grades";
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        gradeService.delete(id);
        return "redirect:/api/grades";
    }

}

