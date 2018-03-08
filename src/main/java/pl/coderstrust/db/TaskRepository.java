package pl.coderstrust.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderstrust.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
