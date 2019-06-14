package pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{
    @Transactional
    void deleteAllByUser(User user);
    List<Task> findAllByUser(User user);
    Task getTaskById(Long id);
}
