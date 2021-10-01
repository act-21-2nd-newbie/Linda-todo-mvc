package guide.springboot.sample.controller;

import guide.springboot.sample.tasks.Task;
import guide.springboot.sample.tasks.TaskAttributes;
import guide.springboot.sample.tasks.TaskIdentifier;
import guide.springboot.sample.tasks.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Validated
class TaskController {
    private final TaskService taskService;
    TaskController(TaskService taskService){this.taskService = taskService;}
    @GetMapping
    public List<Task> viewAll(){
        return taskService.selectAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> viewSpecific(@PathVariable("id") String id) {
        TaskIdentifier identifier = new TaskIdentifier(id);
        return taskService.selectSpecific(identifier);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String insert(@RequestBody TaskAttributes taskAttributes){
        String id = taskService.insert(taskAttributes);
        return id;
    }

    @PatchMapping("/{id}")
    public Task edit(@PathVariable("id") String id, @RequestBody TaskAttributes taskAttributes){
        TaskIdentifier identifier = new TaskIdentifier(id);
        return taskService.edit(identifier, taskAttributes);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        TaskIdentifier identifier = new TaskIdentifier(id);
        taskService.delete(identifier);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable("id") String id, @RequestBody TaskAttributes taskAttributes){
        TaskIdentifier identifier = new TaskIdentifier(id);
        return taskService.update(identifier, taskAttributes);
    }
}
