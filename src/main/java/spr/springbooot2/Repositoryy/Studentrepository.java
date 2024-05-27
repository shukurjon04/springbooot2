package spr.springbooot2.Repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spr.springbooot2.spr.Student;
@Repository
public interface Studentrepository extends JpaRepository<Student, Integer> {
}
