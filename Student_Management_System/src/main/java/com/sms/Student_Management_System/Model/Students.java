package com.sms.Student_Management_System.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {

    @Id
    private int studentId;

    private String name;

    private int age;

    private String batch;

    private List<Address> address;

    @CreatedDate
    private Date createdAt;

    @Override
    public String toString() {
        return "Students{" +
                "studentId= " + studentId +
                ", name= '" + name + '\'' +
                ", age= " + age +
                ", batch= '" + batch + '\'' +
                ", address= " + address +
                ", createdAt= " + createdAt +
                '}';
    }
}
