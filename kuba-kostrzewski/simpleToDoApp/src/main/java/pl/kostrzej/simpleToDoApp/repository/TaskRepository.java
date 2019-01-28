package pl.kostrzej.simpleToDoApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrzej.simpleToDoApp.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
