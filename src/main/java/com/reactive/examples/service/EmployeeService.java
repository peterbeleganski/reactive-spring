package com.reactive.examples.service;

import com.reactive.examples.model.Employee;
import com.reactive.examples.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Petar Beleganski
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> create(Employee employee) {
        return employeeRepository.save(employee)/*.flatMap(Mono::just)*/;
    }

    public Mono<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> update(Employee e) {
        return employeeRepository.save(e);
    }

    public Mono<Void> delete(String id) {
        return employeeRepository.deleteById(id);
    }

}
