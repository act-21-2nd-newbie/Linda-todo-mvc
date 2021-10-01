package guide.springboot.sample.tasks;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> selectAll();

    Optional<Task> selectSpecific(TaskIdentifier id);

    String insert(TaskAttributes attributes);

    Task edit(TaskIdentifier id, TaskAttributes attributes);

    Task update(TaskIdentifier id, TaskAttributes attributes);

    void delete(TaskIdentifier id);

}
