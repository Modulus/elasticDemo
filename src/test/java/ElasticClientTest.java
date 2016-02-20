import com.equaks.elastic.ElasticClient;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ElasticClientTest {

    private TransportClient client;
    @Before
    public void setUp(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("elastic.properties");
        Properties properties = new Properties();


        try {
            properties.load(inputStream);
            client = ElasticClient.createClient(properties);
        } catch (IOException e) {
            assertFalse(true);
        }
    }

    @Test
    public void verifySetup(){
        assertNotNull(client);
    }


}
