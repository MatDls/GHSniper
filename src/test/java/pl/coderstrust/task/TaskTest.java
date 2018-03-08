package pl.coderstrust.task;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.db.TaskRepository;
import pl.coderstrust.model.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskTest {

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void shouldSaveTask() {
		Task task = new Task("task1");
		assertThat(task.getId(), is(nullValue()));
		this.taskRepository.save(task);
		assertThat(task.getId(), is(not(nullValue())));
	}

}
