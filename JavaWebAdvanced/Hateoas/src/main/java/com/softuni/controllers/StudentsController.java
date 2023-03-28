package com.softuni.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.softuni.model.Course;
import com.softuni.model.OrderDto;
import com.softuni.model.Student;
import com.softuni.repositories.OrderRepository;
import com.softuni.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public StudentsController(StudentRepository studentRepository, OrderRepository orderRepository) {
        this.studentRepository = studentRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Student>>> getAllStudents(){
        List<EntityModel<Student>> allStudents=
        this.studentRepository
                .findAll()
                .stream()
                .map(s->EntityModel.of(s,getStudentsLinks(s)))
                .collect(toList());

        return ResponseEntity.ok(
                CollectionModel.of(allStudents,
                linkTo(methodOn(StudentsController.class)
                        .getAllStudents()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Student>> getStudent(@PathVariable("id") Long id){
        Optional<Student> courseOpt = this.studentRepository.findById(id);

        return courseOpt.map(
                c->EntityModel.of(c,getStudentsLinks(c))
        ).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    private Link[] getStudentsLinks(Student student){
        Link self=linkTo(methodOn(StudentsController.class).getStudent(student.getId()))
                .withSelfRel();
        Link orders=linkTo(methodOn(StudentsController.class).getAllOrdersByStudentsId(student.getId()))
                .withRel("orders");
        return List.of(self,orders).toArray(new Link[0]);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> getAllOrdersByStudentsId(
            @PathVariable("id") Long studentId
    ){
        List<EntityModel<OrderDto>>orders=
                orderRepository.findAllByStudentId(studentId)
                .stream()
                .map(OrderDto::asDto)
                .map(dto->EntityModel.of(dto))
                .collect(toList());

        return ResponseEntity.ok(
                CollectionModel.of(orders,
                        linkTo(methodOn(StudentsController.class).getAllOrdersByStudentsId(studentId)).withSelfRel())
        );
    }
}
