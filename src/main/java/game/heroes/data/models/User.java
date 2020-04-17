package game.heroes.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToOne(mappedBy = "user")
    private Hero hero;
}
