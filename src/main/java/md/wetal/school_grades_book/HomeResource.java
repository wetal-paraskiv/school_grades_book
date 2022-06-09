package md.wetal.school_grades_book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeResource {

    @GetMapping("/")
    public String notLogged() {
        return ("home");
    }

    @GetMapping("/student")
    public String student() {
        return ("<h1 style=\"text-align: center;margin-top: 15%\">if you see this,</h1>" +
                "<h1 style=\"text-align: center;margin-top: 15%\">that mean YOU are authenticated ..</h1>" +
                "<h1 style=\"text-align: center;margin-top: 15%\">at least with ROLE_STUDENT..</h1>" +
                "<h1 style=\"text-align: center;margin-top: 15%\">:)</h1>");
    }

    @GetMapping("/teacher")
    public String teacher() {
        return ("<h1 style=\"text-align: center;margin-top: 15%\">page for TEACHERS</h1>" +
                "<h1 style=\"text-align: center;margin-top: 15%\">that are of course</h1>" +
                "<h1 style=\"text-align: center;margin-top: 15%\">authenticated..</h1>" +
                "<h1 style=\"text-align: center;margin-top: 15%\">:)</h1>");
    }
}