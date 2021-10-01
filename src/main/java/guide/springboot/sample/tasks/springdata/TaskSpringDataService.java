package guide.springboot.sample.tasks.springdata;

import guide.springboot.sample.tasks.Task;
import guide.springboot.sample.tasks.TaskAttributes;
import guide.springboot.sample.tasks.TaskIdentifier;
import guide.springboot.sample.tasks.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskSpringDataService implements TaskService {
    private final TaskSpringDataRepository taskSpringDataRepository;

    TaskSpringDataService(TaskSpringDataRepository taskSpringDataRepository){
        this.taskSpringDataRepository = taskSpringDataRepository;
    }



    @Override
    public List<Task> selectAll(){
        return taskSpringDataRepository.findAll();
    }

    @Override
    public Optional<Task> selectSpecific(TaskIdentifier identifier){
        return taskSpringDataRepository.findById(identifier.getId());
    }

    @Override
    public String insert(TaskAttributes attributes) {
        var t = new Task(attributes.getDetails(), "Active");
        var v = taskSpringDataRepository.save(t);
        return v.getIdentifier();
    }

    @Override
    public Task edit(TaskIdentifier id, TaskAttributes attributes){
        var task = taskSpringDataRepository.getById(id.getId());
        var status = attributes.getStatus();
        task.setStatus(status);

        var save = taskSpringDataRepository.save(task);
        return save;

    }

    @Override
    public Task update(TaskIdentifier id, TaskAttributes attributes){
        var task = taskSpringDataRepository.getById(id.getId());
        var details = attributes.getDetails();
        var status = attributes.getStatus();
        task.setDetails(details);
        task.setStatus(status);

        return taskSpringDataRepository.save(task);
    }

    @Override
    public void delete(TaskIdentifier identifier){
        var task = taskSpringDataRepository.getById(identifier.getId());
        taskSpringDataRepository.delete(task);
    }
}
