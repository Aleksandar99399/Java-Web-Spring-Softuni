package com.softuni.controllers;

import com.softuni.model.Course;
import com.softuni.model.Order;
import com.softuni.model.OrderDto;
import com.softuni.model.Student;
import com.softuni.repositories.CourseRepository;
import com.softuni.repositories.OrderRepository;
import com.softuni.repositories.StudentRepository;
import net.bytebuddy.asm.Advice.Unused;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final StudentRepository studentRepository;

    private final OrderRepository orderRepository;

    private final CourseRepository courseRepository;

    public OrdersController(StudentRepository studentRepository,
                            OrderRepository orderRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderDto>> createOrder(
            @RequestBody OrderDto orderDTO) {
        //TODO: validation
        Student student = studentRepository.getOne(orderDTO.getId());
        Course course = courseRepository.getOne(orderDTO.getCourseId());

        Order newOrder = new Order();
        newOrder.setStudent(student);
        newOrder.setCourse(course);

        newOrder = this.orderRepository.save(newOrder);

        //todo build order links

        return ResponseEntity.
                ok(EntityModel.of(OrderDto.asDto(newOrder)));
    }
}