package com.sms.Student_Management_System.Controller;

import com.sms.Student_Management_System.Model.Students;
import com.sms.Student_Management_System.Repository.StudentRepository;
import com.sms.Student_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<Students> addStudent(@RequestBody Students students){

        Students st = studentRepository.save(students);

        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @GetMapping("getStudentById/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable int id){

        Optional<Students> s = studentRepository.findById(id);

        return new ResponseEntity<>(s.get(),HttpStatus.OK);

    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Students>> getAllStudents(){

        List<Students> st = studentRepository.findAll();

        return new ResponseEntity<>(st,HttpStatus.OK);
    }


    @PutMapping("/updateStudentById/{id}")
    public ResponseEntity<Students> updateStudentById(@PathVariable int id,@RequestBody Students students){

        Students st = studentService.updateStudentById(id,students);

        return new ResponseEntity<>(st,HttpStatus.OK);
    }


    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id){

        Optional<Students> s = studentRepository.findById(id);

        if(s.isEmpty()){
            return new ResponseEntity<>("Student not found with id : "+id,HttpStatus.OK);
        }

        studentRepository.delete(s.get());

        return new ResponseEntity<>("Student deleted successfully",HttpStatus.OK);
    }


}
