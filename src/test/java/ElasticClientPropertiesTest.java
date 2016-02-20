import com.equaks.elastic.ElasticClient;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ElasticClientPropertiesTest {

    private TransportClient client;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("elastic2.properties");
        properties.load(inputStream);
        client = ElasticClient.createClient(properties);
    }

    @Test
    public void verifySetUp(){
        assertNotNull(client);
    }


}
