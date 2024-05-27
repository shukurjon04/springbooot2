package spr.springbooot2.spr;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Attachmentcontent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column()
    private byte[] file;
    @OneToOne
    private Attechment attechment;

    public Attachmentcontent(byte[] file, Attechment attechment) {
        this.file = file;
        this.attechment = attechment;
    }
}