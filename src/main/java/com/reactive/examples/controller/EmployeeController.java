package com.reactive.examples.controller;

import com.reactive.examples.model.Employee;
import com.reactive.examples.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Petar Beleganski
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Mono<Employee> create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") String id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(e, status);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Flux<Employee> findAll() {
        Flux<Employee> employees = employeeService.findAll();
        return employees;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        employeeService.delete(id).subscribe();
    }
}
