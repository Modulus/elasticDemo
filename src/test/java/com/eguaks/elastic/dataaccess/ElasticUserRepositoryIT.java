package com.eguaks.elastic.dataaccess;

import com.eguaks.elastic.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Modulus on 21.02.2016.
 */
public class ElasticUserRepositoryIT {
    private ElasticUserRepository repo;

    @Before
    public void setUp(){
        repo = new ElasticUserRepository();
    }

    @Test
    public void save_HasValidUser_UserIsStoredInTheIndex(){
        User user = new User();
        user.setId("18901121112");
        user.setCreated(LocalDate.now().minusYears(10));
        user.setLastLogIn(LocalDate.now());
        user.setUserName("AwesomeDude");
        assertTrue(repo.save(user));

        List<User> foundUser = repo.find(user.getId(), user.getUserName());
        assertNotNull(foundUser);
    }

    @Test
    public void findUser(){
        User user = new User();
        user.setId("18901121112");
        user.setCreated(LocalDate.now().minusYears(10));
        user.setLastLogIn(LocalDate.now());
        user.setUserName("AwesomeDude");
        List<User> foundUser = repo.find(user.getId(), null);
        assertNotNull(foundUser);
    }
}
