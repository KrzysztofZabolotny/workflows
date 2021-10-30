
package com.plaincoded.restapi.controllers.userControllers;

import com.plaincoded.restapi.components.utilities.PasswordChangeRequest;
import com.plaincoded.restapi.components.utilities.ResetEmailRequest;
import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.exceptions.IncorrectEmailException;
import com.plaincoded.restapi.exceptions.IncorrectPasswordException;
import com.plaincoded.restapi.exceptions.NoPasswordException;
import com.plaincoded.restapi.exceptions.NoUsernameException;
import com.plaincoded.restapi.interfaces.user.CustomUUIDIService;
import com.plaincoded.restapi.interfaces.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private final IUserService service;
    private final CustomUUIDIService uuidService;

    @Autowired
    public RegistrationController(IUserService service, CustomUUIDIService uuidService) {
        this.service = service;
        this.uuidService = uuidService;
    }

    @PostMapping("/register")
    ResponseEntity<User> registerUser(@RequestBody User newUser) {

        uuidService.createUUIDAndSendConfirmationEmail(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/register/confirm/{uuid}")
    ResponseEntity<User> confirmRegistration(@PathVariable String uuid) {

        User user = uuidService.confirmUUIDAndRegister(uuid);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(user, user.getId()));
        }catch (Exception e){

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/reset-password")
    ResponseEntity<User> resetPassword(@RequestBody ResetEmailRequest email){

        return service.sendPasswordResetEmail(email,service,uuidService);

    }
    @PostMapping("/reset-password/confirm/{uuid}")
    ResponseEntity<User> confirmPasswordChange(@RequestBody PasswordChangeRequest passwordChangeRequest, @PathVariable String uuid) throws IncorrectEmailException, IncorrectPasswordException, NoPasswordException, NoUsernameException {

        return service.getUserResponseEntity(passwordChangeRequest, uuid, service, uuidService);
    }


}
