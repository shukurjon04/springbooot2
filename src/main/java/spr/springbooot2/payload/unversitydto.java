package spr.springbooot2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class unversitydto {
    private String name;
    private String region;
    private String district;
    private String street;
}
