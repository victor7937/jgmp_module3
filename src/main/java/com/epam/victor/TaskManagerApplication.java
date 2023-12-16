package com.epam.victor;

import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import com.epam.victor.service.SubTaskService;
import com.epam.victor.service.TaskService;
import com.epam.victor.service.mongo.SubTaskServiceMongo;
import com.epam.victor.service.mongo.TaskServiceMongo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		MongoAutoConfiguration.class
})
public class TaskManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(TaskService taskService, SubTaskService subTaskService){
		return args -> {
			TaskInputDto task = new TaskInputDto();
			task.setName("Task111111");
			task.setCategory("Motivation");
			task.setDeadline(Instant.parse("2023-12-16T00:00:00Z"));
			task.setDescription("Try to wake up1121212");

			List<SubTaskInputDto> subTasks = List.of(
					new SubTaskInputDto("Set alarm1422","Set alarm to 5 min later"),
					new SubTaskInputDto("Set alarm22452","Set alarm to 5 min later again"),
					new SubTaskInputDto("Not to wake up1451","Disable alarm"));

			taskService.create(task, subTasks);
			//System.out.println(subTaskService.getAllByTaskCategory("IT"));
//			subTaskService.create(
//					new SubTaskInputDto("Stay here", "Stay in office for work during the day"),
//					"6578142b4e0bec0eaa09f1cc");

		};
	}

}
