package pl.coderstrust.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderstrust.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
