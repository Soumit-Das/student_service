package com.sms.Student_Management_System.Repository;

import com.sms.Student_Management_System.Model.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Students,Integer> {



}
