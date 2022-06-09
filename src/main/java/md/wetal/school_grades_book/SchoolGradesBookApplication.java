package md.wetal.school_grades_book;

import md.wetal.school_grades_book.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SchoolGradesBookApplication {

	public static void main(String[] args) {

		SpringApplication.run(SchoolGradesBookApplication.class, args);

		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /c start chrome.exe http://localhost:8080");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
