package com.softuni.model;

public class OrderDto {
    private  long id;
    private long studentId;
    private long courseId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public static OrderDto asDto(Order order){
        OrderDto orderDto=new OrderDto();
        orderDto.setCourseId(order.getCourse().getId());
        orderDto.setStudentId(order.getStudent().getId());
        orderDto.setId(order.getId());
        return orderDto;
    }
}
