package com.equaks.elastic;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Modulus on 20.02.2016.
 */
public class ElasticClient {
    public static TransportClient createClient(Properties properties) throws UnknownHostException {
       Object hostsObject = properties.get("hosts");
        if(hostsObject instanceof String){
            String hostString = (String)hostsObject;
            String[] hosts = hostString.split(",");
            if(hosts != null){
                TransportClient client = TransportClient.builder().build();
                for(String host : hosts){
                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
                }
            }
        }
        return null;
    }
}
