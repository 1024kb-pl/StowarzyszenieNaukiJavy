package pl.kostrzej.simpleToDoApp.components.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByLogin(String login);
    Optional<User> findByLogin(String login);
}
