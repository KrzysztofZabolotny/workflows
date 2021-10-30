
package com.plaincoded.restapi.controllers.userControllers;


import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.interfaces.user.IUserService;
import com.plaincoded.restapi.services.jwt.JwtUtil;
import com.plaincoded.restapi.services.jwt.UsernameAndPasswordAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final IUserService service;

    @Autowired
    public UserController(IUserService _service) {
        this.service = _service;
    }

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/user")
    ResponseEntity<User> newUser(@RequestBody User newUser){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newUser));
    }

    @RequestMapping("/user")
    List<User> users() {

        return service.findAll();
    }

    @GetMapping("/user/{id}")
    ResponseEntity<User> getOneUser(@PathVariable Long id) {

        User user = service.findOneById(id);
        if (user instanceof User) return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        User foundUser = service.findOneById(id);
        if (foundUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(service.updateUser(newUser, id));
    }


    @DeleteMapping("/user/{id}")
    ResponseEntity<User> deleteOneUser(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest){

        return jwtUtil.getAuthenticationResponseResponseEntity(authenticationRequest);
    }
}
