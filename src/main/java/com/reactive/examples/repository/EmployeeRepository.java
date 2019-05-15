package com.reactive.examples.repository;

import com.reactive.examples.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * @author Petar Beleganski
 */
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

    Flux<Employee> findByName(String name);
}
