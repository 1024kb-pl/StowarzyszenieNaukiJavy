package pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findUserByUsername(String username) throws NotFoundDesiredDataRuntimeException;
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
}
