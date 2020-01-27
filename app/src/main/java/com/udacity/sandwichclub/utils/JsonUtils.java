package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        //parse data corresponding to json sandwich
        Sandwich sandwich = null;
        List<String> listIngredients = new ArrayList<String>();
        List<String> listNames = new ArrayList<String>();
        try {
            JSONObject jsonData = new JSONObject(json);
            JSONObject jsonName = jsonData.getJSONObject("name");
            String mainName = jsonName.getString("mainName");
            String origin = jsonData.getString("placeOfOrigin");
            String description = jsonData.getString("description");
            String image = jsonData.getString("image");

            JSONArray otherNames = jsonName.getJSONArray("alsoKnownAs");
            for (int i = 0; i < otherNames.length(); i++) {
                listNames.add(otherNames.get(i).toString());
            }

            JSONArray jsaIngredients = jsonData.getJSONArray("ingredients");
            for (int i = 0; i < jsaIngredients.length(); i++) {
                listIngredients.add(jsaIngredients.get(i).toString());
            }

            sandwich = new Sandwich(mainName, listNames, origin, description, image, listIngredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;

    }
}
