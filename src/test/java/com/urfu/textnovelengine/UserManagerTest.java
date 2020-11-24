package com.urfu.textnovelengine;

import com.urfu.chadnovelengine.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserManagerTest {

    @Test
    void testAddGetUserMechanic() {
        UserManager userManager = new UserManager();
        Assertions.assertFalse(userManager.isUserRegistered(123));
        userManager.addUser(123);
        Assertions.assertTrue(userManager.isUserRegistered(123));
        Assertions.assertNotNull(userManager.getUser(123));
    }
}
