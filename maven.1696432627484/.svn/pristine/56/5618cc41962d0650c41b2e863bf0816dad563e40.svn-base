package com.fpo.web.utils;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Functions {


    public static HashMap<String, Object> onSuccess(Object data){
        HashMap<String, Object> eJsonObject = new HashMap<>();
        eJsonObject.put("status", true);
        eJsonObject.put("data", data);
        return  eJsonObject;
    }
    public static HashMap<String, Object> onError(Exception e){
        e.printStackTrace();
        HashMap<String, Object> eJsonObject = new HashMap<>();
        eJsonObject.put("status", false);
        eJsonObject.put("errorMessage", e.getMessage());
        log.error("Error: {}", e.getMessage());
        return  eJsonObject;
    }

    public static JSONObject onGsonArraySuccess(JsonArray data) throws Exception{
        JSONObject eJsonObject = new JSONObject();
        // parse data
        JSONParser parser = new JSONParser();
        JSONArray parsedData = (JSONArray) parser.parse(data.toString());
        eJsonObject.put("status", true);
        eJsonObject.put("data", parsedData);
        return  eJsonObject;
    }

    public static JSONObject onGsonObjectSuccess(JsonObject data) throws Exception{
        JSONObject eJsonObject = new JSONObject();
        // parse data
        JSONParser parser = new JSONParser();
        JSONObject parsedData = (JSONObject) parser.parse(data.toString());
        eJsonObject.put("status", true);
        eJsonObject.put("data", parsedData);
        return  eJsonObject;
    }

    //Calculate the distance between two Date in days
    public static int daysBetween(java.util.Date one, java.util.Date two) {
        return (int)( (one.getTime() - two.getTime()) / (1000 * 60 * 60 * 24));
    }




}
