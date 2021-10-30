package com.plaincoded.restapi.interfaces.user;

import com.plaincoded.restapi.entities.userEntities.CustomUUID;
import com.plaincoded.restapi.entities.userEntities.User;

public interface CustomUUIDIService {

    CustomUUID findOneById(String UUID);
    CustomUUID findOneByConfirmationToken(String UUID);
    CustomUUID save(CustomUUID uuid);
    void createUUIDAndSendConfirmationEmail(User newUser);
    User confirmUUIDAndRegister(String uuid);
    void delete(String uuid);
}
