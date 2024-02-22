package io.github.wesleyosantos91;

import de.huxhorn.sulky.ulid.ULID;
import io.github.wesleyosantos91.domain.entity.PersonEntity;
import io.github.wesleyosantos91.domain.request.PersonRequest;
import io.github.wesleyosantos91.repository.PersonRespository;
import io.github.wesleyosantos91.service.PersonService;
import io.github.wesleyosantos91.utils.ULIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PersonService service;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PersonEntity entity = new PersonEntity();
		entity.setName("Wesley Oliveira Santos");
		entity.setCpf("03669252100");
		entity.setEmail("wesleyosantos91@gmail.com");
		entity.setDateOfBirth(LocalDate.of(1991, 6, 12));

		try {
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, true);
			service.save(entity, false);
			service.save(entity, false);
		} catch (Exception e) {

		}


	}
}
