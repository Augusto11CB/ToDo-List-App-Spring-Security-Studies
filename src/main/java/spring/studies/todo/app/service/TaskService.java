package spring.studies.todo.app.service;

import spring.studies.todo.app.model.Task;

import java.security.Principal;

public interface TaskService {

    Iterable<Task> findAll(Principal principal);
    Task findOne(Long id);
    void toggleComplete(Long id);
    void save(Task task);

}
