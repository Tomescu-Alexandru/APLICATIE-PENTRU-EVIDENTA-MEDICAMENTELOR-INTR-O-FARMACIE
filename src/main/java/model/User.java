package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class User {
        private String username;
        private String parola;
        private int idAngajat;
        private String rol;
}
