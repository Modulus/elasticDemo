package com.eguaks.elastic.converters;

import com.eguaks.elastic.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ConverterTest {

    private Converter<User> converter;
    private User user;

    @Before
    public void setUp(){
        converter = new Converter<>();
        user = new User();
        user.setId("18901121112");
        user.setCreated(LocalDate.now().minusYears(10));
        user.setLastLogIn(LocalDate.now());
        user.setUserName("AwesomeDude");
    }

    @Test
    public void convertToJson_HasValidUserObject_CreatesJsonString() throws JsonProcessingException {
        String json = converter.convertToJson(user);
        assertNotNull(json);
        assertTrue(json.contains(user.getId()));
        assertTrue(json.contains(String.valueOf(user.getCreated().getDayOfMonth())));
        assertTrue(json.contains(String.valueOf(user.getCreated().getMonthValue())));
        assertTrue(json.contains(String.valueOf(user.getCreated().getYear())));
        assertTrue(json.contains(String.valueOf(user.getLastLogIn().getDayOfMonth())));
        assertTrue(json.contains(String.valueOf(user.getLastLogIn().getMonthValue())));
        assertTrue(json.contains(String.valueOf(user.getLastLogIn().getYear())));
        assertTrue(json.contains(user.getUserName()));
    }

    @Test
    //http://stackoverflow.com/questions/28802544/java-8-localdate-jackson-format
    public void convertFromJson_HasValidJsonObject_CreatesCorrectUserObject() throws IOException {
        String json = converter.convertToJson(user);
        User convertedUser = converter.convertFromJson(json, User.class);

        assertNotNull(convertedUser);
        assertEquals(user, convertedUser);
    }
}
