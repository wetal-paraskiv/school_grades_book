package md.wetal.school_grades_book.service.Implementation;

import md.wetal.school_grades_book.entities.Grade;
import md.wetal.school_grades_book.exceptions.ResourceNotFoundException;
import md.wetal.school_grades_book.repositories.GradeRepository;
import md.wetal.school_grades_book.service.GradeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    public final int NUMBER_OF_ITEMS_PER_PAGE = 1;
    GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllBySearch(String q) {
        return gradeRepository.getAllBySearch(q);
    }

    public Page<Grade> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, NUMBER_OF_ITEMS_PER_PAGE);
        return gradeRepository.findAll(pageable);
    }

    @Override
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade getOne(Long id) {
        return gradeRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
    }

    @Override
    public Grade saveNew(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Grade update(Grade grade, Long id) {
        Grade targetedGrade = gradeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedGrade.setCourse(grade.getCourse());
        targetedGrade.setGrade(grade.getGrade());
        targetedGrade.setStudent(grade.getStudent());
        gradeRepository.save(targetedGrade);
        return targetedGrade;
    }

    @Override
    public void delete(Long id) {
        gradeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        gradeRepository.deleteById(id);
    }
}
