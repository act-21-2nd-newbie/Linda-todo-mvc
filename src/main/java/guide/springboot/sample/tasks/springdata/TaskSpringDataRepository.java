package guide.springboot.sample.tasks.springdata;

import guide.springboot.sample.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

interface TaskSpringDataRepository extends JpaRepository<Task, String> {
}
