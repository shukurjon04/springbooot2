package spr.springbooot2.Repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spr.springbooot2.spr.Address;

@Repository
public interface Adresrepository extends JpaRepository<Address, Integer> {
}
