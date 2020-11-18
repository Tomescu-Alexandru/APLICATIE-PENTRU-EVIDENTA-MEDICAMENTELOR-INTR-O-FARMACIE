package model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Angajat {

    private String nume;
    private String prenume;
    private String cnp;
    private String adresa;
    private String sex;
    private Date dataNasterii;
    private int salariu;
    private int idPunctLucru;

}
