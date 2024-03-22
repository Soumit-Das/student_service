package com.sms.Student_Management_System.Service;

import com.sms.Student_Management_System.Model.Students;
import com.sms.Student_Management_System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentService{

    @Autowired
    public StudentRepository studentRepository;

    @Override
    public Students updateStudentById(int id, Students student) {

        Optional<Students> s = studentRepository.findById(id);

        Students st = s.get();

        st.setName(student.getName());
        st.setAddress(student.getAddress());
        st.setStandard(student.getStandard());
        st.setAge(student.getAge());

        return studentRepository.save(st);
    }

}
