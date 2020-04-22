package tech.kuiperbelt.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tech.kuiperbelt.lib.ems.EmsAutoConfiguration;
import tech.kuiperbelt.lib.ems.EmsRepositoryImplement;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = EmsRepositoryImplement.class)
@EntityScan(basePackageClasses = {EmsDemoApplication.class, EmsAutoConfiguration.class})
public class EmsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsDemoApplication.class, args);
	}

}
