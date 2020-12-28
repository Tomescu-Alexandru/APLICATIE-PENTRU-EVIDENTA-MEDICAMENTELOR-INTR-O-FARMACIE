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

    private int idMedicament;
    private String numeMedicament;
    private Date valabitilate;
    private int idCategorie;
    private String numeProducator;
    private int gramaj;
}
