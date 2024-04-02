package com.sms.Student_Management_System.Controller;

import com.sms.Student_Management_System.Config.AppConstants;
import com.sms.Student_Management_System.Exception.StudentException;
import com.sms.Student_Management_System.FeignUtils;
import com.sms.Student_Management_System.Model.Notifications;
import com.sms.Student_Management_System.Model.Students;
import com.sms.Student_Management_System.Repository.StudentRepository;
import com.sms.Student_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public StudentService studentService;

    @Autowired
    private KafkaTemplate<String, Students> kafkaTemplate;

    @Autowired
    private FeignUtils feignUtils;

    @Value("${user.greet}")
    public String greet;

    @PostMapping("/addStudent")
    public ResponseEntity<Students> addStudent(@RequestBody Students students) throws StudentException {

        Students st = studentService.saveStudent(students);

        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @PostMapping("/f-sendNotification")
    public ResponseEntity<Notifications> createNotification(@RequestBody Students students){

        return feignUtils.createNotification(students);

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

        Optional<Students> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isEmpty()){
            return new ResponseEntity<>("Student not found with id : " + id, HttpStatus.NOT_FOUND);
        }

        Students student = optionalStudent.get();

        kafkaTemplate.send(AppConstants.STUDENT_TOPIC_NAME, student);

        return new ResponseEntity<>("Student marked as deleted with id: " + id, HttpStatus.OK);


    }


//    @GetMapping("/getAllNotifications")
//    public ResponseEntity<List<Notifications>> getAllNotifications(){
//
//        List<Notifications> notifications = studentService.getAllNotifications();
//
//        return new ResponseEntity<>(notifications,HttpStatus.OK);
//    }

    @GetMapping("/f-getAllNotifications")
    public ResponseEntity<List<Notifications>> fgetAllNotifications(){

        return feignUtils.getAllNotifications();

    }

    // restTemplate
//    @GetMapping("/getAllNotificationsPagewise")
//    public NotificationsPagewise getAllNotificationsPagewise(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int numberOfData){
//
//        NotificationsPagewise notifications = studentService.getAllNotificationsPagewise(pageNumber,numberOfData);
//
//        return notifications;
//
//    }

    // feignClient
    @GetMapping("/f-getAllNotificationsPagewise")
    public List<Notifications> fgetAllNotificationsPagewise(@RequestParam int pageNumber, @RequestParam int numberOfData) {
        List<Notifications> allNotificationsPagewise =  feignUtils.getAllNotificationsPagewise(pageNumber, numberOfData);
        return allNotificationsPagewise;
    }


//    ResponseEntity<CustomPageImpl<Object>> getAuthProfileHistory(
//            @PathVariable("tenant") String tenant,
//            @RequestHeader("Cookie") String token,
//            @PageableDefault(sort="updatedDate", direction = Sort.Direction.DESC, value = 10)Pageable pageable
//    );


    @GetMapping("/getAllUniqueCities")
    public List<String> getAllUniqueCities(){

        return studentService.getAllUniqueCities();

    }

    @GetMapping("/getName/{name}")
    public String getName(@PathVariable String name){

        return name+" : "+greet;
    }

}
