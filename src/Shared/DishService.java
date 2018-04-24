package Shared;

import org.json.simple.JSONObject;

import java.rmi.Remote;

public interface DishService extends Remote {
    JSONObject getDishesByCategoryName(String categoryName) throws Exception;

    JSONObject readAll() throws Exception;
    JSONObject read(JSONObject parameters) throws Exception;

    JSONObject save(JSONObject data) throws Exception;
    JSONObject saveAll(JSONObject data) throws Exception;

    JSONObject delete(JSONObject data) throws Exception;
    JSONObject deleteAll(JSONObject data) throws Exception;
}