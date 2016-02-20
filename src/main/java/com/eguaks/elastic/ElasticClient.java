package com.eguaks.elastic;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ElasticClient {
    public static TransportClient createClient(Properties properties) throws UnknownHostException {
        Object hostsObject = properties.get("hosts");
        String[] hosts = extractHosts(hostsObject);

        if (hosts != null) {
            TransportClient client = TransportClient.builder().build();
            for (String host : hosts) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
            }
            return client;
        }

        return null;
    }

    static String[] extractHosts(Object object) {
        if (object instanceof String) {
            String hostString = (String) object;
            return hostString.split(",");
        }
        return null;
    }
}
