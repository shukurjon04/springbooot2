package spr.springbooot2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spr.springbooot2.Repositoryy.Adresrepository;
import spr.springbooot2.Repositoryy.Faculityrepositr;
import spr.springbooot2.Repositoryy.Repositr;
import spr.springbooot2.payload.Faculitydto;
import spr.springbooot2.spr.Fakultet;
import spr.springbooot2.spr.Univercity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Faculity")
public class Faculity {


    private final Repositr repository;

    private final Faculityrepositr faculityrepositr;
    private final Adresrepository adresrepository;

    @Autowired
    public Faculity(Repositr repository, Faculityrepositr faculityrepositr, Adresrepository adresrepository) {
        this.repository = repository;
        this.adresrepository = adresrepository;
        this.faculityrepositr = faculityrepositr;
    }

    @PostMapping(value = "/add")
    public String addFaculity(@RequestBody Faculitydto faculitydto) {
        Fakultet fakultet = new Fakultet();
        System.out.println(faculitydto);
        fakultet.setName(faculitydto.getFacultyname());
        Optional<Univercity> optionalUnivercity = repository.findById(faculitydto.getUniversityid());
        fakultet.setUnivercity(optionalUnivercity.get());
        faculityrepositr.save(fakultet);
        return "qoshilid";

    }

    @GetMapping(value = "/Get")
    public List<Fakultet> GetFaculity() {
        return faculityrepositr.findAll();
    }

    @PutMapping(value = "/{edit}")
    public String editFaculity(@PathVariable("edit") int id ,@RequestBody Faculitydto faculitydto) {
        Optional<Fakultet> optionalFakultet = faculityrepositr.findById(id);
        if (optionalFakultet.isEmpty()) {
            return "error";
        }
        Fakultet fakultet = optionalFakultet.get();
        Optional<Univercity> optionalUnivercity = repository.findById(faculitydto.getUniversityid());
        fakultet.setName(faculitydto.getFacultyname());
        fakultet.setUnivercity(optionalUnivercity.get());
        faculityrepositr.save(fakultet);
        return "tahrirlandi";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteFaculity(@PathVariable int id) {
        Optional<Fakultet> optionalFakultet = faculityrepositr.findById(id);
        if (optionalFakultet.isEmpty()) {
            return "Fakultet not found";
        }
        faculityrepositr.delete(optionalFakultet.get());
        return "ochirildi";


    }
}
