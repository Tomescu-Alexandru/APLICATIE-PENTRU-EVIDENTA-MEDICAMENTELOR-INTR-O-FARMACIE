package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class AngajatPunctLucru extends Angajat{
    //am folosit aceasta clasa pentru a putea adauga la clasa Angajat si detaliile referitoare la Punctul de lucru
    private String numePunctLucru;
    private String telefon;
    private String adresa;
}
