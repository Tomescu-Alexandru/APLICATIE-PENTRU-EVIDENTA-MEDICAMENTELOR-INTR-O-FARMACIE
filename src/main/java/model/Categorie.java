package model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Categorie {
    //am folosit aceasta clasa pentru a putea salva datele Categoriei
    private int idCategorie;
    private String numeCategorie;
    private String descriere;
}
