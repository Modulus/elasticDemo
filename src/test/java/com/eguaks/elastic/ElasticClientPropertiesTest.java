package com.eguaks.elastic;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ElasticClientPropertiesTest {

    private String[] hosts;
    private Properties properties;

    @Before
    public void setUp() throws IOException {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("elastic2.properties");
        properties.load(inputStream);
    }

    @Test
    public void extractHosts_HasValidProperties_ReturnsCorrectHosts(){
        hosts = ElasticClient.extractHosts(properties.getProperty("hosts"));
        assertNotNull(hosts);
        assertEquals(3, hosts.length);
        List<String> hostList = Arrays.asList(hosts);

        assertTrue(hostList.contains("192.168.99.100"));
        assertTrue(hostList.contains("192.168.99.101"));
        assertTrue(hostList.contains("192.168.99.102"));
    }


}
