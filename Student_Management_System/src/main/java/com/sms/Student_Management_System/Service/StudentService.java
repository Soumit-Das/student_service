package com.sms.Student_Management_System.Service;

import com.sms.Student_Management_System.Exception.StudentException;
import com.sms.Student_Management_System.Model.Notifications;
import com.sms.Student_Management_System.Model.NotificationsPagewise;
import com.sms.Student_Management_System.Model.Students;

import java.util.List;

public interface StudentService {

     Students updateStudentById(int id,Students student);

     Students saveStudent(Students students) throws StudentException;

//    void sendNotification(Students students);

//    List<Notifications> getAllNotifications();
     void sendNotification(Students students);
//    public NotificationsPagewise getAllNotificationsPagewise(int pageNumber, int numberOfData);

//    NotificationsPagewise<Notifications> getAllNotificationsPagewiseSorted(int pageNumber, int numberOfData, String sortingField, String sortingDirection);

    List<String> getAllUniqueCities();

}
