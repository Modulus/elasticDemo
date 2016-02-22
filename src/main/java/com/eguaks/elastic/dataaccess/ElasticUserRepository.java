package com.eguaks.elastic.dataaccess;

import com.eguaks.elastic.ElasticClient;
import com.eguaks.elastic.User;
import com.eguaks.elastic.converters.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by Modulus on 21.02.2016.
 */
public class ElasticUserRepository {

    private Client client;
    private final static String INDEX_NAME = "users";
    private final static String TYPE_NAME = "user";
    private Converter<User> converter;

    public ElasticUserRepository(Client client) {
        this.client = client;
        this.converter = new Converter<>();
    }

    public ElasticUserRepository(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("elastic.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            this.client = ElasticClient.createClient(properties);
            this.converter = new Converter<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ElasticUserRepository(Properties properties){
        try {
            this.client = ElasticClient.createClient(properties);
            this.converter = new Converter<>();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public boolean save(User user){
        String json = null;
        try {
            json = converter.convertToJson(user);
            IndexResponse response = client.prepareIndex(INDEX_NAME, TYPE_NAME).setSource(json).get();
            return response.isCreated();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> find(String id, String name){
        List<User> users = new ArrayList<>();
        try {
            //You can emit the sizes here

            MultiSearchResponse responses = findMultiSearch(id, name);
            if(responses.getResponses() !=  null && responses.getResponses().length > 0){
                for(MultiSearchResponse.Item item : responses.getResponses()){
                    SearchResponse response = item.getResponse();
                    if(response.getHits().getTotalHits() > 0){
                        for(SearchHit hit : response.getHits()){
                            String json = hit.getSourceAsString();
                            User user = converter.convertFromJson(json, User.class);
                            users.add(user);
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            return users;
        }

    }

    private MultiSearchResponse findMultiSearch(String id, String name) throws InterruptedException, ExecutionException {
        SearchRequestBuilder srb1 = client.prepareSearch(INDEX_NAME).setQuery(QueryBuilders.termQuery("id", id)).setSize(100);
        SearchRequestBuilder srb2 = client.prepareSearch(INDEX_NAME).setQuery(QueryBuilders.termQuery("userName", name)).setSize(100);

        return client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .execute().get();
    }

    public boolean delete(String id){
        try {
            MultiSearchResponse responses = findMultiSearch(id, null);
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }
        return false;
    }
}
