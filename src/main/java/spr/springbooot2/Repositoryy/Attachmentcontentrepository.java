package spr.springbooot2.Repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spr.springbooot2.spr.Attachmentcontent;

public interface Attachmentcontentrepository extends JpaRepository<Attachmentcontent, Integer> {
}
