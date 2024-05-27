package spr.springbooot2.Repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spr.springbooot2.Controller.Faculity;
import spr.springbooot2.spr.Fakultet;

@Repository
public interface Faculityrepositr extends JpaRepository<Fakultet, Integer> {
}
