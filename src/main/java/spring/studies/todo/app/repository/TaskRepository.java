package spring.studies.todo.app.repository;

import org.springframework.data.repository.CrudRepository;
import spring.studies.todo.app.model.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

//    @Override                                       //:#{} -> It is called Spring Expression Language (Spell)
//    @Query("select t from Task t where t.user.id=:#{principal.id}")
//    List<Task> findAll();                                 //Evaluation Context Extention was configured in TemplateConfig
//                                                               // to be possible to expose the authentication data

    List<Task> findAllByUser_Username(String name);
}
