package com.example.config;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.model.option.Color;
import com.example.repository.option.ColorsRepository;

// only when -Dspring.profiles.active=local
@Profile(value = { "local", "default" })
@Configuration()
public class RepositoryInitialization {

	@Bean
	public CommandLineRunner initializeDb(ColorsRepository repository) {
		return (args) -> {
			// repository.deleteAll();
			// Insert data
			for (int i = 1; i <= 10; i++) {
				Color c = new Color();
				c.setShortName("clr" + i);
				c.setLongName("Color" + i);
				c.setActive(true);
				repository.save(c);
			}
		};
	}
}
