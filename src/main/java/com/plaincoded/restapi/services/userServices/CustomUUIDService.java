package com.plaincoded.restapi.services.userServices;

import com.plaincoded.restapi.components.mail.MessageSender;
import com.plaincoded.restapi.entities.userEntities.CustomUUID;
import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.interfaces.user.CustomUUIDIRepository;
import com.plaincoded.restapi.interfaces.user.CustomUUIDIService;
import com.plaincoded.restapi.interfaces.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUUIDService implements CustomUUIDIService {

    private final CustomUUIDIRepository repository;
    private final IUserService service;



    @Autowired
    public CustomUUIDService(CustomUUIDIRepository repository, IUserService service) {
        this.repository = repository;

        this.service = service;
    }

    @Override
    public CustomUUID findOneById(String UUID) {
        return null;
    }

    @Override
    public CustomUUID findOneByConfirmationToken(String UUID) {
        return repository.findByUUID(UUID);
    }

    @Override
    public CustomUUID save(CustomUUID uuid) {
        return repository.save(uuid);
    }

    @Override
    public void delete(String uuid) {

    }

    public void createUUIDAndSendConfirmationEmail(User newUser) {

        CustomUUID confirmationToken = new CustomUUID(newUser);

        newUser.setRegistrationDate(System.currentTimeMillis());

        try {

            service.save(newUser);

        }catch (Exception e){

        }
        this.save(confirmationToken);

        sendRegistrationEmail(newUser, confirmationToken);

    }

    public void sendRegistrationEmail(User newUser, CustomUUID confirmationToken){
        new MessageSender().sendMessage(newUser.getEmail(),"Registration confirmation","To confirm your registration, click this page http://localhost:8080/register/confirm/"+confirmationToken.getUUID());

    }

    public User confirmUUIDAndRegister(String uuid){//extract method from RegistrationController
        CustomUUID myUUID = this.findOneByConfirmationToken(uuid);

        User user = service.findByUsername(myUUID.getUser().getUsername());
        user.setActive(true);

        return user;
    }
}
