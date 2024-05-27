package spr.springbooot2.spr;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Attechment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String contenttype;
    @Column(nullable = false)
    private Long size;

    private String facename;

    public Attechment(String name, String contenttype, Long size) {
        this.name = name;
        this.contenttype = contenttype;
        this.size = size;
    }

    public Attechment(String name, String contenttype, Long size, String facename) {
        this.name = name;
        this.facename = facename;
        this.contenttype = contenttype;
        this.size = size;
    }
}
