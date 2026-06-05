package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.component.DeptService;
import com.eduhub.eduhub_backend.component.Student;
import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/courses")//to avoid ambiguity
public class CourseController {
    //    @Autowired
    CourseService courseService;
    //@Autowired
    DeptService deptService;
    public CourseController(CourseService courseService, DeptService deptService){
        this.courseService =courseService;
        this.deptService=deptService;
    }



//    @GetMapping("get-course")
//
//    public String getCourse(){
//        return courseService.getCourse();
//    }
    @GetMapping("get-dept")
    public String getDept(){
        return deptService.getDept();
    }
    @GetMapping("course")
    public ResponseEntity<Course> getCourse(){
        Course course=new Course("u23456","maths",4);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    static List<Course> courseList=new ArrayList<>();
    static{
        courseList.add(new Course("u24cs45","physics",4) );
        courseList.add(new Course("u24ai45","agentic ai",5) );
        courseList.add(new Course("u24ml45","aiml",3) );
        courseList.add(new Course("u24fs45","fullstack",4) );

        courseList.add(new Course("u24cs45","computer",4) );
    }
    @GetMapping("gc")
    public ResponseEntity<List<Course>> getCources(){
        return new ResponseEntity<>(courseList,HttpStatus.OK);
    }

    @GetMapping("/gc/{courseCode}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseCode){
        return courseList.stream().filter(c ->c.getCourseCode().equalsIgnoreCase(courseCode) ).findFirst().map(ResponseEntity::ok).orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
    }


    @GetMapping("/course/{cc}/{sn}/{c}")
    public ResponseEntity<Course>coursePathvariable(@PathVariable("cc") String CourseCode,
                                                    @PathVariable("sn") String SubjectName,
                                                    @PathVariable("c") int credits){
        Course course=new Course(CourseCode,SubjectName,credits);
        return new  ResponseEntity<>(course,HttpStatus.OK);
    }
    @GetMapping("cquery")
    public ResponseEntity<Course>courseRequestVariable(@RequestParam String cc,
                                                       @RequestParam String sn,
                                                       @RequestParam int c){
        Course course=new Course(cc,sn,c);
        return ResponseEntity.ok(course);
    }

    @PostMapping("course/create")
    public ResponseEntity<Course>createcourse(@RequestBody Course course){
        courseList.add(course);
        return ResponseEntity.ok(course);
    }


    @PutMapping("course/update/{cc}")
    public ResponseEntity updatecourse(@PathVariable("cc") String coursecode,
                                        @RequestBody Course updateCourse){
        Course course=courseList.stream().filter(c ->c.getCourseCode().equalsIgnoreCase(coursecode) ).findFirst().orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",coursecode));
        course.setCourseCode(updateCourse.getCourseCode());
        course.setCredits(updateCourse.getCredits());
        return ResponseEntity.accepted().body(course);
    }

    @DeleteMapping("course/delete/{cc}")
    public ResponseEntity deletecourse(@PathVariable("cc") String coursecode){
        Course course=courseList.stream().filter(c ->c.getCourseCode().equalsIgnoreCase(coursecode) ).findFirst().orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",coursecode));
        courseList.remove(course);
        return ResponseEntity.accepted().body("data removed successfully");
    }

    @PutMapping("/query/{code}")
    public String queryCourse(@PathVariable String code){
        if(code.startsWith("*")){
            throw new IllegalArgumentException("It is having a special character");
        }else if(code.startsWith("6")){
            throw new RuntimeException();
        }

        return code;
    }
}