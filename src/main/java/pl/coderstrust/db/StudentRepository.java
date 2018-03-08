package pl.coderstrust.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderstrust.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
