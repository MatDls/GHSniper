package pl.coderstrust.student;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.db.StudentRepository;
import pl.coderstrust.model.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void save() {
    Student student = new Student(1L, "Jack Strong", "jack@Github", null);
    assertTrue(this.studentRepository.findAll().isEmpty());
		System.out.println("hahah");
		this.studentRepository.save(student);
    assertTrue(this.studentRepository.findAll().contains(student));
	}
}
