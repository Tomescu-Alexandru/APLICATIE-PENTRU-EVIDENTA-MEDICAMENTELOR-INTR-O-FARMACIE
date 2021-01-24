package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class User extends AngajatPunctLucru{
        //am folosit aceasta clasa pentru a adauga la clasa Angajat detaliile despre utilizator
        private String username;
        private String parola;
        private String rol;
}
