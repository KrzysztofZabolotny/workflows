package com.plaincoded.restapi.interfaces.user;

import com.plaincoded.restapi.entities.userEntities.CustomUUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUUIDIRepository extends JpaRepository<CustomUUID,String> {
    CustomUUID findByUUID(String UUID);
}
