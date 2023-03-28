package com.softuni;

import com.softuni.model.Course;
import com.softuni.model.Order;
import com.softuni.model.Student;
import com.softuni.repositories.CourseRepository;
import com.softuni.repositories.OrderRepository;
import com.softuni.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public init(StudentRepository studentRepository, CourseRepository courseRepository, OrderRepository orderRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Student ivan = new Student();
        ivan.setName("Ivan");
        ivan.setAge(21);

        ivan = studentRepository.save(ivan);

        Course springData = new Course();
        springData.setEnabled(true);
        springData.setName("Spring Data");
        springData.setPrice(100.00);

        springData = courseRepository.save(springData);

        Course springBatch = new Course();
        springBatch.setEnabled(false);
        springBatch.setName("Spring Batch");
        springBatch.setPrice(150.00);

        springBatch = courseRepository.save(springBatch);

        Order ivanSpringData = new Order();
        ivanSpringData.setCourse(springData);
        ivanSpringData.setStudent(ivan);
        orderRepository.save(ivanSpringData);
    }
}
