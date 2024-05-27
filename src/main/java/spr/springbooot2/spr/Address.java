package spr.springbooot2.spr;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( nullable = false)
    private String region;
    @Column( nullable = false)
    private String district;
    @Column( nullable = false)
    private String street;

    public Address(String region, String district, String street) {
        this.region = region;
        this.district = district;
        this.street = street;
    }
}
