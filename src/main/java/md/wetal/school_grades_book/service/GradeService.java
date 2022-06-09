package md.wetal.school_grades_book.service;

import md.wetal.school_grades_book.entities.Grade;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GradeService {

    List<Grade> getAllBySearch(String q);

    List<Grade> getAll();

    Grade getOne(Long id);

    Grade saveNew(Grade grade);

    Grade update(Grade grade, Long id);

    void delete(Long id);

    Page<Grade> findPage(int pageNumber);
}
