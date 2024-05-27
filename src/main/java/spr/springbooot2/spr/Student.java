package spr.springbooot2.spr;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @OneToOne(optional = false)
    private Address address;
    @ManyToOne(optional = false)
    private Guruh guruh;
    @ManyToMany()
    private List<Fanlar> fanlar;


}
