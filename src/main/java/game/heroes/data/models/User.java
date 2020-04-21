package game.heroes.data.models;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
