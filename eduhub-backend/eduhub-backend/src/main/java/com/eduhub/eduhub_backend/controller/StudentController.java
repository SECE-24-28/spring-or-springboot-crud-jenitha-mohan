package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student =new Student(1,"jeni","M");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>>getStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"Ram","Kumar"));
        studentList.add(new Student(2,"lavi","cutiee"));
        studentList.add(new Student(3,"jeni","kuttaa"));
        studentList.add(new Student(4,"karthi","psycho"));

        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    //http://localhost:8080/1/JENI/M
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student>studentPathvariable(@PathVariable("id") int studentId,
                                                      @PathVariable("first-name") String firstName,
                                                      @PathVariable("last-name") String lastName){
        Student student =new Student(studentId,firstName,lastName);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
//http://localhost:8080/query?studentId=1&firstName=lavi&lastName=cutieeee
    @GetMapping("query")
    public ResponseEntity<Student>studentRequestVaraible(@RequestParam int studentId,
                                                         @RequestParam String firstName,
                                                         @RequestParam String lastName)
    {
        Student student=new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }
    @PostMapping("create")
    public ResponseEntity<Student>createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);

    }
    @PutMapping("update")
    public ResponseEntity updateStudent(){
        return ResponseEntity.badRequest().body("not found");
    }
    @PutMapping("{id}/update")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId,
                                        @RequestBody Student student){
        return ResponseEntity.accepted().body(student);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity deleteStudent(@PathVariable("id") int studentId){
        return ResponseEntity.accepted().body("data removed successfully");
    }
}
