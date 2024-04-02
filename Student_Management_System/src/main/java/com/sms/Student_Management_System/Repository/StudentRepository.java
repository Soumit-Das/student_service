package com.sms.Student_Management_System.Repository;

import com.sms.Student_Management_System.Model.Notifications;
import com.sms.Student_Management_System.Model.Students;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Students,Integer>{

    @Aggregation({
            "{ '$match' : { 'address.city' : { '$ne' : null } } }",
            "{ '$unwind' : '$address' }",
            "{ '$group' : { '_id' : '$address.city' }}",
            "{ '$project' : { '_id' : 0, 'city' : '$_id' }}"
    })
    List<String> distinctCities();

}
