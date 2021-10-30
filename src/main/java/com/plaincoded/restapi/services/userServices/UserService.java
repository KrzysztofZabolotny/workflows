package com.plaincoded.restapi.services.userServices;

import com.plaincoded.restapi.components.mail.MessageSender;
import com.plaincoded.restapi.components.utilities.InputValidators;
import com.plaincoded.restapi.components.utilities.PasswordChangeRequest;
import com.plaincoded.restapi.components.utilities.ResetEmailRequest;
import com.plaincoded.restapi.entities.userEntities.CustomUUID;
import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.exceptions.IncorrectEmailException;
import com.plaincoded.restapi.exceptions.IncorrectPasswordException;
import com.plaincoded.restapi.exceptions.NoPasswordException;
import com.plaincoded.restapi.exceptions.NoUsernameException;
import com.plaincoded.restapi.interfaces.user.CustomUUIDIService;
import com.plaincoded.restapi.interfaces.user.IUserRepository;
import com.plaincoded.restapi.interfaces.user.IUserService;
import com.plaincoded.restapi.services.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;
    private final InputValidators inputValidators;
    private final PasswordConfig passwordConfig;


    @Autowired
    public UserService(IUserRepository repository, InputValidators inputValidators, PasswordConfig passwordConfig) {
        this.repository = repository;
        this.inputValidators = inputValidators;
        this.passwordConfig = passwordConfig;
    }


    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findOneById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findOneByUsernameAndPassword(String username, String password) {
        Example<User> userExample = Example.of(new User(username, password));
        return repository.findOne(userExample).orElse(null);
    }


    public User findOneActiveByUsernameAndPassword(String username, String password) {

        User user = new User(username, password);
        user.setActive(true);
        Example<User> example = Example.of(user);
        return repository.findOne(example).orElse(null);
    }

    @Override
    public User save(User newUser) {
        try {
            validatedNewUser(newUser);
        }catch (Exception e){

        }
        if (!userWithUsernameExist(newUser)) {
            newUser.setPassword(passwordConfig.encode(newUser.getPassword()));

            return repository.save(newUser);
        } else throw new IllegalArgumentException("The user already exists");
    }

    @Override
    public User updateUser(User newUser, Long id){
        try{
            validatedNewUser(newUser);
        }catch (Exception e){
        }
        newUser.setId(id);
        newUser.setPassword(passwordConfig.encode(newUser.getPassword()));
        newUser.setEmail(newUser.getEmail());
        return repository.save(newUser);
    }


    @Override
    public void delete(Long id) {

        if (userWithIdExist(id)) {
            repository.deleteById(id);
        } else throw new IllegalArgumentException("The user with id "+id+" does not exist");
    }

    private void validatedNewUser(User newUser) throws IncorrectEmailException, IncorrectPasswordException, NoUsernameException, NoPasswordException {

        if (!inputValidators.hasUsername(newUser)) throw new NoUsernameException();
        if (!inputValidators.hasPassword(newUser)) throw new NoPasswordException();
        if (!inputValidators.isValidEmail(newUser.getEmail())) throw new IncorrectEmailException();
        if (!inputValidators.isValidPassword(newUser.getPassword())) throw new IncorrectPasswordException();
    }

    private boolean userWithUsernameExist(User user) {

        Optional<User> optionalUser = Optional.ofNullable(repository.findByUsername(user.getUsername()));

        return optionalUser.isPresent();

    }
    private boolean userWithIdExist(Long id) {

        Optional<User> optionalUser = repository.findById(id);

        return optionalUser.isPresent();

    }

    @Override
    public ResponseEntity<User> sendPasswordResetEmail(ResetEmailRequest email, IUserService service, CustomUUIDIService uuidService){//sends an email to the user with the reset email link
        User user = service.findByEmail(email.getEmail());

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CustomUUID confirmationToken = new CustomUUID(user);//creates a custom UUID based on the user

        uuidService.save(confirmationToken);//saves the custom UUID to database

        Runnable runnable = () -> new MessageSender().sendMessage(user.getEmail(),"Password change confirmation","To change your password, click this page http://localhost:8080/reset-password/confirm/"+confirmationToken.getUUID());
        new Thread(runnable).start();//sends email in a thread(to prevent the user from waiting until it i sent)

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @Override
    public ResponseEntity<User> getUserResponseEntity(PasswordChangeRequest passwordChangeRequest, String uuid, IUserService service, CustomUUIDIService uuidService ){//updates user password, after checking if the UUID is correct
        CustomUUID customUUID = uuidService.findOneByConfirmationToken(uuid);
        User user = service.findOneById(customUUID.getUser().getId());
        user.setPassword(passwordChangeRequest.getPassword());
        service.updateUser(user, user.getId());
        return ResponseEntity.ok().build();
    }




}
