package com.geekpaul.reactivemvc;

import com.geekpaul.reactivemvc.mongo.Person;
import com.geekpaul.reactivemvc.mongo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ReactiveMvcSampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReactiveMvcSampleApplication.class, args);
	}

}

@Component
class LodaData {
	@Autowired
	private PersonRepository personRepo;

	// @PostConstruct
	public void load() {
		this.personRepo
				.insert(generatePersons())
				.subscribe();
	}

	private Flux<Person> generatePersons() {
		Flux<Person> flux = Flux.just(
				new Person(1, "Arindam", "Paul", 30),
				new Person(2, "John", "Doe", 32),
				new Person(3, "Mighty", "Knoob", 45),
				new Person(4, "Jack", "Ryan", 15)
		);
		return flux;
	}
}

@RestController
@RequestMapping("/person")
class PersonController {
	@Autowired
	private PersonRepository personRepo;

	@GetMapping("/{id}")
	public Mono<Person> getPerson(@PathVariable Long id) {
		return personRepo.findById(id);
	}

	@GetMapping("/")
	public Flux<Person> getAllPerson() {
		return personRepo.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addPerson(@RequestBody Person person) {
		// ...
	}
}