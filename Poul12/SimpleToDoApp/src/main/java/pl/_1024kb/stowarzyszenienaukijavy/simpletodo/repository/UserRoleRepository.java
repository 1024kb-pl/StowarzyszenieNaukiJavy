package pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>
{
    UserRole findByRole(String role);

    @Override
    void delete(UserRole entity);
}
