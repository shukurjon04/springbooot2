package spr.springbooot2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spr.springbooot2.Repositoryy.Studentrepository;
import spr.springbooot2.spr.Student;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    Studentrepository studentrepository;

    @GetMapping
    public Page<Student> getStudent(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return studentrepository.findAll(pageable);
    }
}
