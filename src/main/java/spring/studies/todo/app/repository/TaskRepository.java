package spring.studies.todo.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.studies.todo.app.model.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    
    @Override
    @Query("select t from Task t where t.user.id=:#{principal.id}")
    List<Task> findAll();
}
