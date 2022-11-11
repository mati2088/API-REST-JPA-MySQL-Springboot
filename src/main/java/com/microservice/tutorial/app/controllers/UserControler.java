package com.microservice.tutorial.app.controllers;

import com.microservice.tutorial.app.entity.User;
import com.microservice.tutorial.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserControler {

    @Autowired//Inyeccion de dependecias
    private UserService userService;

    //Create new User

    @PostMapping
    public ResponseEntity<?>create(@RequestBody User user){
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

    }
    //read an user
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable  long id){
        Optional<User>oUser=userService.findById(id);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }
    //Update an User

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable long id){
        Optional<User> user= userService.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        user.get().setName(userDetails.getName());
        user.get().setSurname(userDetails.getSurname());
        user.get().setEmail(userDetails.getEmail());
        user.get().setEnable(userDetails.getEnable());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));

    }

    //delete an user
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        if(!userService.findById(id).isPresent())
        {

            return ResponseEntity.notFound().build();
        }

           userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    //read all users
    @GetMapping
    public List<User>readAll(){
        List<User>users= StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }


}
