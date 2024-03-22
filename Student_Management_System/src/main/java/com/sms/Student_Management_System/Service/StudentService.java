package com.sms.Student_Management_System.Service;

import com.sms.Student_Management_System.Model.Students;
import org.springframework.stereotype.Service;

public interface StudentService {

    public Students updateStudentById(int id,Students student);

}
