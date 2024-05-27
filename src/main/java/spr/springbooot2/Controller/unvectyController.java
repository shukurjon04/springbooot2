package spr.springbooot2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spr.springbooot2.Repositoryy.Adresrepository;
import spr.springbooot2.Repositoryy.Repositr;
import spr.springbooot2.payload.unversitydto;
import spr.springbooot2.spr.Address;
import spr.springbooot2.spr.Univercity;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/University")
public class unvectyController {

    @Autowired
    Repositr repositr;

    @GetMapping(value = "/GetAll")
    public List<Univercity> getAllUnivercity() {
        return repositr.findAll();
    }

    @Autowired
    Adresrepository adresrepository;

    @PostMapping(value = "/AddOne")
    public String addUnivercity(@RequestBody unversitydto unversitydto) {
        Address address = new Address(unversitydto.getRegion(), unversitydto.getDistrict(), unversitydto.getStreet());
        Address save = adresrepository.save(address);
        Univercity univercity = new Univercity(unversitydto.getName(),save);
        repositr.save(univercity);
        return "universty save";
    }
    @PutMapping(value = "/EditOne/{id}")
    public String editUnivercity(@PathVariable int id, @RequestBody unversitydto unversitydto) {
        Optional<Univercity> univercityOptional = repositr.findById(id);
        if (!univercityOptional.isPresent())
            return "universty not found";
        Univercity univercity = univercityOptional.get();
        univercity.setName(unversitydto.getName());
        Address univercityAddress = univercity.getAddress();
        univercityAddress.setStreet(unversitydto.getStreet());
        univercityAddress.setDistrict(unversitydto.getDistrict());
        univercityAddress.setRegion(unversitydto.getRegion());
        adresrepository.save(univercityAddress);
        repositr.save(univercity);
        return "universty edited";
    }

    @PutMapping(value = "/DeleteOne/{id}")
    public String deleteUnivercity(@PathVariable int id) {
        repositr.deleteById(id);
        return "universty deleted";
    }
}
