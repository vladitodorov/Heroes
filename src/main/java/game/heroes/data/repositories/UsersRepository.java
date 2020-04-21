package game.heroes.data.repositories;

import game.heroes.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
