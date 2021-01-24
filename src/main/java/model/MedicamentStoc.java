package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class MedicamentStoc extends Medicament{
    //am folosit aceasta clasa pentru a adauga la Clasa Medicament si stocul si punctul de lucru
    private int stoc;
    private String numePunctLucru;
}
