
package com.plaincoded.restapi.services;

import com.plaincoded.restapi.entities.userEntities.User;
import com.plaincoded.restapi.exceptions.IncorrectEmailException;
import com.plaincoded.restapi.exceptions.IncorrectPasswordException;
import com.plaincoded.restapi.exceptions.NoPasswordException;
import com.plaincoded.restapi.exceptions.NoUsernameException;
import com.plaincoded.restapi.interfaces.user.IUserService;
import com.plaincoded.restapi.services.security.PasswordConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    IUserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;

    private PasswordConfig passwordConfig;


    private final String PASSWORD_WITH_CORRECT_STRUCTURE = "F&t9_f4K";
    private final String EMAIL_WITH_CORRECT_STRUCTURE = "user@test.com";


    @Test
    @DisplayName("Adding new User with correct password structure")
    public void testForAddingNewUser() throws Exception {

        final User user = new User("testUser", PASSWORD_WITH_CORRECT_STRUCTURE);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);
        userService.save(user);

        final var found = this.userService.findOneByUsernameAndPassword(user.getUsername(), user.getPassword());

        assertEquals(user.getUsername(), found.getUsername());
        assertEquals(user.getPassword(), found.getPassword());
    }


    @Test
    @DisplayName("Adding new User with incorrect password structure")
    public void testForExceptionHandlingWhileAddingNewUserWithPasswordValidation() {

        assertThrows(IncorrectPasswordException.class, () ->
        {
            final User user = new User("testUser", "wrongFormatPassword");
            user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);
            userService.save(user);
        });
    }

    @Test
    @DisplayName("Adding new User with incorrect email structure")
    public void testForExceptionHandlingWhileAddingNewUserWithEmailValidation() {

        assertThrows(IncorrectEmailException.class, () ->
        {
            final User user = new User("testUser", PASSWORD_WITH_CORRECT_STRUCTURE);
            user.setEmail("notCorrectEmail");
            userService.save(user);
        });
    }


    @Test
    @DisplayName("Deletion")
    public void testForUserDeletion() throws Exception {
        User user = new User("userForDeletion", PASSWORD_WITH_CORRECT_STRUCTURE);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);
        userService.save(user);
        int initCount = this.userService.findAll().size();
        this.userService.delete(user.getId());
        int finalCount = this.userService.findAll().size();
        assertEquals(initCount - 1, finalCount);
    }

    @Test
    @DisplayName("Get by user/password")
    public void testForUserAuth() throws Exception {

        User user = new User("newUser", PASSWORD_WITH_CORRECT_STRUCTURE);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);
        this.userService.save(user);
        final User auth = this.userService.findOneByUsernameAndPassword(user.getUsername(), user.getPassword());
        assertEquals(user.getId(), auth.getId());

    }

    @Test
    @DisplayName("Get by active user/pass")
    public void testForActiveUserAuth() throws Exception {
        User user = new User("newInactiveUser", PASSWORD_WITH_CORRECT_STRUCTURE);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);
        user.setActive(false);
        user = this.userService.save(user);
        final User auth = this.userService.findOneActiveByUsernameAndPassword(user.getUsername(), user.getPassword());
        assertNull(auth);
    }


    @Test
    @DisplayName("Update")
    public void testForUpdate() throws Exception {

        passwordConfig = new PasswordConfig();
        final User user = new User("UserForUpdate", PASSWORD_WITH_CORRECT_STRUCTURE);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);
        this.userService.save(user);
        User found = this.userService.findOneByUsernameAndPassword(user.getUsername(), user.getPassword());
        assertNotNull(found.getId(), "Found real user");
        this.userService.updateUser(found, user.getId());
        final User asserted = this.userService.findOneByUsernameAndPassword(user.getUsername(), found.getPassword());
        assertNotNull(asserted, "Updated user found");
        assertSame(user.getId(), asserted.getId(), "User ID is persisted");
    }

    @Test
    @DisplayName("Test for null username")
    public void testForNullUsername() {
        final User user = new User();
        user.setUsername(null);
        user.setPassword(PASSWORD_WITH_CORRECT_STRUCTURE);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);

        assertThrows(NoUsernameException.class, () -> {
            userService.save(user);
        });

    }

    @Test
    @DisplayName("Test for null password")
    public void testForNullPassword() {
        final User user = new User();
        user.setUsername("testUser");
        user.setPassword(null);
        user.setEmail(EMAIL_WITH_CORRECT_STRUCTURE);

        assertThrows(NoPasswordException.class, () -> {
            userService.save(user);
        });

    }

}
