package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.User;
import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    static List<User> userList=new ArrayList<>();
    static{
        userList.add(new User(1,"jeni","1234"));
        userList.add(new User(2,"keni","1234"));
        userList.add(new User(3,"teni","1234"));
        userList.add(new User(4,"lavi","1234"));
        userList.add(new User(5,"karthi","1234"));

            }
            @GetMapping("gu")
            public ResponseEntity<List<User>> getUsers(){
                 return new ResponseEntity<>(userList, HttpStatus.OK);
            }

            @GetMapping("/{id}")
            public ResponseEntity<User> getuser(@PathVariable("id") String id){
                if(id.matches(".*[^a-zA-Z0-9].*")){
                    throw new IllegalArgumentException("UserId having a special character");
                }
                int userId=Integer.parseInt(id);
                return userList.stream().filter( u->u.getUserId()==userId).findFirst().map(ResponseEntity::ok).orElseThrow(()->new ResourceNotFoundException("User","UserId",String.valueOf(userId)));

            }
            //http://localhost:8080/user/uquery?id=1
            @GetMapping("uquery")
            public ResponseEntity<User> userRequestVariable(@RequestParam String id){
            if(id.matches(".*[^a-zA-Z0-9].*")){
                  throw new IllegalArgumentException("UserId having a special character");
            }
            int userId=Integer.parseInt(id);
            return userList.stream().filter( u->u.getUserId()==userId).findFirst().map(ResponseEntity::ok).orElseThrow(()->new ResourceNotFoundException("User","UserId",String.valueOf(userId)));

           }

            @PostMapping("/create")
            public ResponseEntity<User> createUser(@RequestBody User user){
            userList.add(user);
            return ResponseEntity.ok(user);
            }

            @PutMapping("update/{ui}")
            public ResponseEntity<User> updateUser(@PathVariable("ui") int id,
                                                   @RequestBody User updateuser){
             User user=userList.stream().filter( u->u.getUserId()==id).findFirst().orElseThrow(()->new ResourceNotFoundException("User","UserId",String.valueOf(id)));
             user.setUserName(updateuser.getUserName());
             user.setPassword(updateuser.getPassword());
             user.setUserId(updateuser.getUserId());
             return ResponseEntity.accepted().body(user);
            }

            @DeleteMapping("delete/{id}")
            public ResponseEntity deleteUser(@PathVariable("id") int id){
                User user=userList.stream().filter( u->u.getUserId()==id).findFirst().orElseThrow(()->new ResourceNotFoundException("User","UserId",String.valueOf(id)));
                userList.remove(user);
                return ResponseEntity.accepted().body("data removed successfully");
            }


}
