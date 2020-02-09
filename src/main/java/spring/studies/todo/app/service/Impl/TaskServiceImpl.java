package spring.studies.todo.app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.studies.todo.app.model.Task;
import spring.studies.todo.app.repository.TaskRepository;
import spring.studies.todo.app.service.TaskService;

import java.security.Principal;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Iterable<Task> findAll(Principal principal) {
        return taskRepository.findAllByUser_Username(principal.getName());
    }

    @Override
    public Task findOne(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void toggleComplete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        task.setComplete(!task.isComplete());
        taskRepository.save(task);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }
}
