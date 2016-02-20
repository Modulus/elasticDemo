package com.eguaks.elastic;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ElasticClientIT {

    private TransportClient client;
    private Properties properties;
    private final static String EXPECTED_HOST = "192.168.99.100";

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("elastic.properties");
        properties = new Properties();
        properties.load(inputStream);
    }

    @Test
    public void createClient_DockerContainerIsRunning_HasClientInstance() throws UnknownHostException {
        client = ElasticClient.createClient(properties);
        assertNotNull(client);


        assertNotNull(client.nodeName());
        assertNotNull(client.settings());

        assertEquals(1, client.listedNodes().size());
        String hostName = client.listedNodes().get(0).getHostName();
        String hostAddress = client.listedNodes().get(0).getHostAddress();
        InetSocketTransportAddress inetSocketTransportAddress = (InetSocketTransportAddress) client.listedNodes().get(0).getAddress();
        InetSocketTransportAddress expectedInetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(EXPECTED_HOST), 9300);
        assertEquals(EXPECTED_HOST, hostName);
        assertEquals(EXPECTED_HOST, hostAddress);
        assertEquals(expectedInetSocketTransportAddress, inetSocketTransportAddress);

    }


}
