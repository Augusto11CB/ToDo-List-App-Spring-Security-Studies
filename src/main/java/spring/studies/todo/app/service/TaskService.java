package spring.studies.todo.app.service;

import spring.studies.todo.app.model.Task;

public interface TaskService {

    Iterable<Task> findAll();
    Task findOne(Long id);
    void toggleComplete(Long id);
    void save(Task task);

}
