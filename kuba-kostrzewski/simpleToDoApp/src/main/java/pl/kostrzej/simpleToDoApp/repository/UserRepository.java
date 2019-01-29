package pl.kostrzej.simpleToDoApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrzej.simpleToDoApp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByLogin(String login);
    User findByLogin(String login);
}
