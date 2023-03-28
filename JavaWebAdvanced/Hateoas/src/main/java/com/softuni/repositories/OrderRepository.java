package com.softuni.repositories;

import com.softuni.model.Order;
import com.softuni.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.student.id =: studentId ")
    public List<Order> findAllByStudentId(Long studentId);
}
