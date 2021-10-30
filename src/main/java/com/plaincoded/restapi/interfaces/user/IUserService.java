
package com.plaincoded.restapi.interfaces.user;

import com.plaincoded.restapi.components.utilities.PasswordChangeRequest;
import com.plaincoded.restapi.components.utilities.ResetEmailRequest;
import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.exceptions.IncorrectEmailException;
import com.plaincoded.restapi.exceptions.IncorrectPasswordException;
import com.plaincoded.restapi.exceptions.NoPasswordException;
import com.plaincoded.restapi.exceptions.NoUsernameException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    List<User> findAll();
    User findOneById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    User findOneByUsernameAndPassword(String username, String password);
    User findOneActiveByUsernameAndPassword(String username, String password);
    User save(User newUser);
    User updateUser(User user, Long id);
    ResponseEntity<User> sendPasswordResetEmail(ResetEmailRequest email, IUserService service, CustomUUIDIService uuidService);
    ResponseEntity<User> getUserResponseEntity(PasswordChangeRequest passwordChangeRequest, String uuid, IUserService service, CustomUUIDIService uuidService) throws IncorrectEmailException, IncorrectPasswordException, NoPasswordException, NoUsernameException;
    void delete(Long id);


}
