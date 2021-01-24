package model;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Medicament {
    //am folosit aceasta clasa pentru a putea salva datele Medicamentelor
    private int idMedicament;
    private String numeMedicament;
    private Date valabitilate;
    private int idCategorie;
    private String numeProducator;
    private int gramaj;
}
